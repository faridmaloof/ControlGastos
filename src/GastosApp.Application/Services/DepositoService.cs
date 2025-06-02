using GastosApp.Application.Interfaces;
using GastosApp.Domain.Entities;
using GastosApp.Infrastructure.Persistence;
using Microsoft.EntityFrameworkCore;

namespace GastosApp.Application.Services;

public class DepositoService : IDepositoService
{
    private readonly GastosAppDbContext _context;

    public DepositoService(GastosAppDbContext context)
    {
        _context = context;
    }

    public async Task<Deposito> RegistrarDepositoAsync(string usuarioId, DateTime fecha, int fondoMonetarioId, decimal monto)
    {
        if (monto <= 0)
        {
            throw new ArgumentException("El monto del depósito debe ser positivo.");
        }
        if (await _context.FondosMonetarios.FindAsync(fondoMonetarioId) == null)
        {
            throw new KeyNotFoundException("El fondo monetario especificado no existe.");
        }


        var nuevoDeposito = new Deposito
        {
            UsuarioId = usuarioId,
            Fecha = fecha,
            FondoMonetarioId = fondoMonetarioId,
            Monto = monto
        };

        _context.Depositos.Add(nuevoDeposito);
        await _context.SaveChangesAsync();
        return nuevoDeposito;
    }

    public async Task<IEnumerable<Deposito>> ObtenerDepositosPorUsuarioAsync(string usuarioId, DateTime fechaInicio, DateTime fechaFin)
    {
        var fechaFinAjustada = fechaFin.Date.AddDays(1).AddTicks(-1);

        return await _context.Depositos
                             .Include(d => d.FondoMonetario)
                             .Where(d => d.UsuarioId == usuarioId &&
                                         d.Fecha >= fechaInicio.Date &&
                                         d.Fecha <= fechaFinAjustada)
                             .OrderByDescending(d => d.Fecha)
                             .ToListAsync();
    }

    public async Task<Deposito> ObtenerDepositoPorIdAsync(int id, string usuarioId)
    {
        return await _context.Depositos
            .Include(d => d.FondoMonetario)
            .FirstOrDefaultAsync(d => d.Id == id && d.UsuarioId == usuarioId);
    }

    public async Task EliminarDepositoAsync(int id, string usuarioId)
    {
        var deposito = await _context.Depositos.FirstOrDefaultAsync(d => d.Id == id && d.UsuarioId == usuarioId);
        if (deposito == null)
        {
            throw new KeyNotFoundException("Depósito no encontrado o no pertenece al usuario.");
        }
        _context.Depositos.Remove(deposito);
        await _context.SaveChangesAsync();
    }
}