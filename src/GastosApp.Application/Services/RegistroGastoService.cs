using GastosApp.Application.Dtos;
using GastosApp.Application.Interfaces;
using GastosApp.Domain.Entities;
using GastosApp.Infrastructure.Persistence;
using Microsoft.EntityFrameworkCore;

namespace GastosApp.Application.Services;

public class RegistroGastoService(GastosAppDbContext context) : IRegistroGastoService
{
    private readonly GastosAppDbContext _context = context;

    public async Task<(RegistroGasto GastoCreado, List<AlertaSobregiro> Alertas)> RegistrarGastoAsync(string usuarioId, RegistroGastoInput gastoInput)
    {
        if (gastoInput == null || gastoInput.Detalles == null || !gastoInput.Detalles.Any())
            throw new ArgumentException("El gasto debe tener al menos un detalle.");
        if (await _context.FondosMonetarios.FindAsync(gastoInput.FondoMonetarioId) == null)
            throw new KeyNotFoundException("El fondo monetario especificado no existe.");


        var alertasSobregiro = new List<AlertaSobregiro>();

       
        using var transaction = await _context.Database.BeginTransactionAsync();
        try
        {
            var nuevoGastoEncabezado = new RegistroGasto
            {
                UsuarioId = usuarioId,
                Fecha = gastoInput.Fecha,
                FondoMonetarioId = gastoInput.FondoMonetarioId,
                Observaciones = gastoInput.Observaciones,
                NombreComercio = gastoInput.NombreComercio,
                TipoDocumento = gastoInput.TipoDocumento,
                Detalles = []
            };
            _context.RegistrosGastos.Add(nuevoGastoEncabezado);

            foreach (var detalleInput in gastoInput.Detalles)
            {
                if (detalleInput.Monto <= 0)
                    throw new ArgumentException($"El monto para el tipo de gasto ID {detalleInput.TipoGastoId} debe ser positivo.");
                var tipoGasto = await _context.TiposGastos.FindAsync(detalleInput.TipoGastoId) ?? throw new KeyNotFoundException($"El tipo de gasto con ID {detalleInput.TipoGastoId} no existe.");
                var presupuesto = await _context.PresupuestosMensuales
                        .FirstOrDefaultAsync(p => p.UsuarioId == usuarioId &&
                                                  p.Anio == gastoInput.Fecha.Year &&
                                                  p.Mes == gastoInput.Fecha.Month &&
                                                  p.TipoGastoId == detalleInput.TipoGastoId);

                decimal montoPresupuestado = presupuesto?.MontoPresupuestado ?? 0;

                var gastosDelMesParaEsteTipo = await _context.RegistrosGastosDetalles
                    .Include(rgd => rgd.RegistroGasto)
                    .Where(rgd => rgd.RegistroGasto.UsuarioId == usuarioId &&
                                  rgd.RegistroGasto.Fecha.Year == gastoInput.Fecha.Year &&
                                  rgd.RegistroGasto.Fecha.Month == gastoInput.Fecha.Month &&
                                  rgd.TipoGastoId == detalleInput.TipoGastoId)
                    .SumAsync(rgd => rgd.Monto);

                if ((gastosDelMesParaEsteTipo + detalleInput.Monto) > montoPresupuestado && montoPresupuestado > 0)
                {
                    alertasSobregiro.Add(new AlertaSobregiro(
                        tipoGasto.Nombre,
                        tipoGasto.Id,
                        montoPresupuestado,
                        detalleInput.Monto,
                        gastosDelMesParaEsteTipo
                    ));
                }

                var nuevoDetalle = new RegistroGastoDetalle
                {
                    RegistroGasto = nuevoGastoEncabezado,
                    TipoGastoId = detalleInput.TipoGastoId,
                    Monto = detalleInput.Monto
                };
                nuevoGastoEncabezado.Detalles.Add(nuevoDetalle);
            }

            await _context.SaveChangesAsync();
            await transaction.CommitAsync();
            return (nuevoGastoEncabezado, alertasSobregiro);
        }
        catch (Exception ex)
        {
            await transaction.RollbackAsync();
            Console.WriteLine($"Error al registrar gasto: {ex.Message} {ex.StackTrace}");
            throw;
        }
    }


    public async Task<IEnumerable<RegistroGasto>> ObtenerGastosPorUsuarioAsync(string usuarioId, DateTime fechaInicio, DateTime fechaFin)
    {
        var fechaFinAjustada = fechaFin.Date.AddDays(1).AddTicks(-1);
        return await _context.RegistrosGastos
                             .Include(rg => rg.FondoMonetario)
                             .Include(rg => rg.Detalles)
                                .ThenInclude(d => d.TipoGasto)
                             .Where(rg => rg.UsuarioId == usuarioId &&
                                           rg.Fecha >= fechaInicio.Date &&
                                           rg.Fecha <= fechaFinAjustada)
                             .OrderByDescending(rg => rg.Fecha)
                             .ToListAsync();
    }

    public async Task<RegistroGasto> ObtenerGastoPorIdAsync(int id, string usuarioId)
    {
        return await _context.RegistrosGastos
                             .Include(rg => rg.FondoMonetario)
                             .Include(rg => rg.Detalles)
                                .ThenInclude(d => d.TipoGasto)
                             .FirstOrDefaultAsync(rg => rg.Id == id && rg.UsuarioId == usuarioId);
    }

    public async Task EliminarGastoAsync(int id, string usuarioId)
    {
        var gasto = await _context.RegistrosGastos
                            .FirstOrDefaultAsync(rg => rg.Id == id && rg.UsuarioId == usuarioId) ?? throw new KeyNotFoundException("Registro de gasto no encontrado o no pertenece al usuario.");
        _context.RegistrosGastos.Remove(gasto);
        await _context.SaveChangesAsync();
    }
}