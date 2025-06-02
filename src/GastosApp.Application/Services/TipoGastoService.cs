using GastosApp.Application.Interfaces;
using GastosApp.Domain.Entities;
using GastosApp.Infrastructure.Persistence;
using Microsoft.EntityFrameworkCore;

namespace GastosApp.Application.Services;

public class TipoGastoService(GastosAppDbContext context) : ITipoGastoService
{
    private readonly GastosAppDbContext _context = context;

    public async Task<IEnumerable<TipoGasto>> ObtenerTodosAsync()
        => await _context.TiposGastos.OrderBy(tg => tg.Nombre).ToListAsync();

    public async Task<TipoGasto?> ObtenerPorIdAsync(int id)
        => await _context.TiposGastos.FindAsync(id);

    public async Task<string> ObtenerSiguienteCodigoAsync()
    {
        var ultimoTipoGasto = await _context.TiposGastos
                                    .OrderByDescending(tg => tg.Id)
                                    .FirstOrDefaultAsync();

        if (ultimoTipoGasto == null || string.IsNullOrEmpty(ultimoTipoGasto.Codigo))
            return "GAS001";
        
        try
        {
            string parteNumericaStr = ultimoTipoGasto.Codigo[3..];
            if (int.TryParse(parteNumericaStr, out int parteNumerica))
                return $"GAS{(parteNumerica + 1).ToString("D3")}";
            else
            {
                var maxId = await _context.TiposGastos.AnyAsync() ? await _context.TiposGastos.MaxAsync(tg => tg.Id) : 0;
                return $"GAS{(maxId + 1).ToString("D3")}";

            }
        }
        catch (Exception)
        {
            var maxId = await _context.TiposGastos.AnyAsync() ? await _context.TiposGastos.MaxAsync(tg => tg.Id) : 0;
            return $"GAS{(maxId + 1).ToString("D3")}";
        }
    }

    public async Task<TipoGasto> CrearAsync(string codigo, string nombre)
    {
        if (string.IsNullOrWhiteSpace(codigo))
            throw new ArgumentException("El código del tipo de gasto no puede estar vacío.", nameof(codigo));

        if (string.IsNullOrWhiteSpace(nombre))
            throw new ArgumentException("El nombre del tipo de gasto no puede estar vacío.", nameof(nombre));

        if (await _context.TiposGastos.AnyAsync(tg => tg.Codigo.ToLower() == codigo.ToLower()))
            throw new InvalidOperationException($"Ya existe un tipo de gasto con el código '{codigo}'.");

        if (await _context.TiposGastos.AnyAsync(tg => tg.Nombre.ToLower() == nombre.ToLower()))
            throw new InvalidOperationException($"Ya existe un tipo de gasto con el nombre '{nombre}'.");

        var nuevoTipoGasto = new TipoGasto
        {
            Nombre = nombre,
            Codigo = codigo
        };

        _context.TiposGastos.Add(nuevoTipoGasto);
        await _context.SaveChangesAsync();
        return nuevoTipoGasto;
    }

    public async Task ActualizarAsync(TipoGasto tipoGasto)
    {
        ArgumentNullException.ThrowIfNull(tipoGasto);

        if (await _context.TiposGastos.AnyAsync(tg => tg.Nombre.ToLower() == tipoGasto.Nombre.ToLower() && tg.Id != tipoGasto.Id))
            throw new InvalidOperationException($"Ya existe otro tipo de gasto con el nombre '{tipoGasto.Nombre}'.");


        _context.Entry(tipoGasto).State = EntityState.Modified;
        _context.Entry(tipoGasto).Property(x => x.Codigo).IsModified = false;

        await _context.SaveChangesAsync();
    }

    public async Task EliminarAsync(int id)
    {
        var tipoGasto = await _context.TiposGastos.FindAsync(id);
        if (tipoGasto != null)
        {
            bool enUsoEnPresupuestos = await _context.PresupuestosMensuales.AnyAsync(p => p.TipoGastoId == id);
            bool enUsoEnGastos = await _context.RegistrosGastosDetalles.AnyAsync(rgd => rgd.TipoGastoId == id);

            if (enUsoEnPresupuestos || enUsoEnGastos)
                throw new InvalidOperationException("Este tipo de gasto está en uso y no puede ser eliminado.");

            _context.TiposGastos.Remove(tipoGasto);
            await _context.SaveChangesAsync();
        }
    }
}