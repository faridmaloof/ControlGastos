using GastosApp.Application.Dtos;
using GastosApp.Application.Interfaces;
using GastosApp.Infrastructure.Persistence;
using Microsoft.EntityFrameworkCore;

namespace GastosApp.Application.Services;

public class ConsultaService(GastosAppDbContext context) : IConsultaService
{
    private readonly GastosAppDbContext _context = context;

    public async Task<IEnumerable<MovimientoDto>> ObtenerMovimientosPorRangoFechasAsync(string usuarioId, DateTime fechaInicio, DateTime fechaFin)
    {
        var fechaFinAjustada = fechaFin.Date.AddDays(1).AddTicks(-1);

        var depositos = await _context.Depositos
            .Where(d => d.UsuarioId == usuarioId && d.Fecha >= fechaInicio.Date && d.Fecha <= fechaFinAjustada)
            .Include(d => d.FondoMonetario)
            .Select(d => new MovimientoDto
            {
                Fecha = d.Fecha,
                TipoMovimiento = "Depósito",
                Descripcion = $"Depósito a {d.FondoMonetario.Nombre}",
                FondoMonetarioNombre = d.FondoMonetario.Nombre,
                Ingreso = d.Monto,
                Egreso = 0,
                ReferenciaId = d.Id
            }).ToListAsync();

        var gastos = await _context.RegistrosGastos
            .Where(rg => rg.UsuarioId == usuarioId && rg.Fecha >= fechaInicio.Date && rg.Fecha <= fechaFinAjustada)
            .Include(rg => rg.FondoMonetario)
            .Include(rg => rg.Detalles)
            .Select(rg => new MovimientoDto
            {
                Fecha = rg.Fecha,
                TipoMovimiento = "Gasto",
                Descripcion = $"Gasto: {(!string.IsNullOrEmpty(rg.NombreComercio) ? rg.NombreComercio : "Varios")}",
                FondoMonetarioNombre = rg.FondoMonetario.Nombre,
                Ingreso = 0,
                Egreso = rg.Detalles.Sum(d => d.Monto),
                ReferenciaId = rg.Id
            }).ToListAsync();

        var movimientos = depositos.Concat(gastos)
                                 .OrderByDescending(m => m.Fecha)
                                 .ThenByDescending(m => m.ReferenciaId)
                                 .ToList();

        return movimientos;
    }

    public async Task<DatosGraficoPresupuestoDto> ObtenerDatosGraficoPresupuestoAsync(string usuarioId, DateTime fechaInicio, DateTime fechaFin)
    {
        var fechaFinAjustada = fechaFin.Date.AddDays(1).AddTicks(-1);
        var resultadoGrafico = new DatosGraficoPresupuestoDto();

        var todosTiposGasto = await _context.TiposGastos
                                        .OrderBy(tg => tg.Nombre)
                                        .ToListAsync();

        foreach (var tipoGasto in todosTiposGasto)
        {
            decimal totalPresupuestado = 0;
            for (DateTime fechaActual = fechaInicio.Date; fechaActual <= fechaFin.Date; fechaActual = fechaActual.AddMonths(1))
            {
                var presupuestoDelMes = await _context.PresupuestosMensuales
                    .Where(p => p.UsuarioId == usuarioId &&
                                p.Anio == fechaActual.Year &&
                                p.Mes == fechaActual.Month &&
                                p.TipoGastoId == tipoGasto.Id)
                    .SumAsync(p => p.MontoPresupuestado);
                totalPresupuestado += presupuestoDelMes;
            }


            var totalEjecutado = await _context.RegistrosGastosDetalles
                .Include(rgd => rgd.RegistroGasto)
                .Where(rgd => rgd.TipoGastoId == tipoGasto.Id &&
                              rgd.RegistroGasto.UsuarioId == usuarioId &&
                              rgd.RegistroGasto.Fecha >= fechaInicio.Date &&
                              rgd.RegistroGasto.Fecha <= fechaFinAjustada)
                .SumAsync(rgd => rgd.Monto);

            if (totalPresupuestado > 0 || totalEjecutado > 0)
            {
                resultadoGrafico.LabelsTiposGasto.Add(tipoGasto.Nombre);
                resultadoGrafico.MontosPresupuestados.Add(totalPresupuestado);
                resultadoGrafico.MontosEjecutados.Add(totalEjecutado);
            }
        }
        return resultadoGrafico;
    }
}