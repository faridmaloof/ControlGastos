using GastosApp.Application.Interfaces;
using GastosApp.Domain.Entities;
using GastosApp.Infrastructure.Persistence;
using Microsoft.EntityFrameworkCore;

namespace GastosApp.Application.Services;

public class PresupuestoService(GastosAppDbContext context) : IPresupuestoService
{
    private readonly GastosAppDbContext _context = context;

    public async Task<IEnumerable<PresupuestoMensual>> ObtenerPresupuestosPorUsuarioAsync(string usuarioId, int anio, int mes)
    {
        return await _context.PresupuestosMensuales
                             .Include(p => p.TipoGasto)
                             .Where(p => p.UsuarioId == usuarioId && p.Anio == anio && p.Mes == mes)
                             .OrderBy(p => p.TipoGasto.Nombre)
                             .ToListAsync();
    }

    public async Task<PresupuestoMensual> ObtenerPorIdAsync(int id)
    {
        return await _context.PresupuestosMensuales
                             .Include(p => p.TipoGasto)
                             .FirstOrDefaultAsync(p => p.Id == id);
    }

    public async Task<PresupuestoMensual> GuardarPresupuestoAsync(string usuarioId, int anio, int mes, int tipoGastoId, decimal monto)
    {
        if (monto < 0)
        {
            throw new ArgumentException("El monto presupuestado no puede ser negativo.");
        }

        var presupuestoExistente = await _context.PresupuestosMensuales
            .FirstOrDefaultAsync(p => p.UsuarioId == usuarioId &&
                                      p.Anio == anio &&
                                      p.Mes == mes &&
                                      p.TipoGastoId == tipoGastoId);

        if (presupuestoExistente != null)
        {
           
            presupuestoExistente.MontoPresupuestado = monto;
            _context.Entry(presupuestoExistente).State = EntityState.Modified;
        }
        else
        {
           
            presupuestoExistente = new PresupuestoMensual
            {
                UsuarioId = usuarioId,
                Anio = anio,
                Mes = mes,
                TipoGastoId = tipoGastoId,
                MontoPresupuestado = monto
            };
            _context.PresupuestosMensuales.Add(presupuestoExistente);
        }

        await _context.SaveChangesAsync();
        return presupuestoExistente;
    }

    public async Task EliminarPresupuestoAsync(int id, string usuarioId)
    {
        var presupuesto = await _context.PresupuestosMensuales
                                    .FirstOrDefaultAsync(p => p.Id == id && p.UsuarioId == usuarioId) ?? throw new KeyNotFoundException("Presupuesto no encontrado o no pertenece al usuario.");
        _context.PresupuestosMensuales.Remove(presupuesto);
        await _context.SaveChangesAsync();
    }
}