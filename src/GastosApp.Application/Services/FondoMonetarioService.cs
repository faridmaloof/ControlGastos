using GastosApp.Application.Interfaces;
using GastosApp.Domain.Entities;
using GastosApp.Domain.Enums;
using GastosApp.Infrastructure.Persistence;
using Microsoft.EntityFrameworkCore;

namespace GastosApp.Application.Services;

public class FondoMonetarioService : IFondoMonetarioService
{
    private readonly GastosAppDbContext _context;

    public FondoMonetarioService(GastosAppDbContext context)
    {
        _context = context;
    }

    public async Task<IEnumerable<FondoMonetario>> ObtenerTodosAsync()
    {
        return await _context.FondosMonetarios.OrderBy(fm => fm.Nombre).ToListAsync();
    }

    public async Task<FondoMonetario> ObtenerPorIdAsync(int id)
    {
        return await _context.FondosMonetarios.FindAsync(id);
    }

    public async Task<FondoMonetario> CrearAsync(string nombre, TipoFondo tipo)
    {
        if (string.IsNullOrWhiteSpace(nombre))
        {
            throw new ArgumentException("El nombre del fondo monetario no puede estar vacío.", nameof(nombre));
        }

        if (await _context.FondosMonetarios.AnyAsync(fm => fm.Nombre.ToLower() == nombre.ToLower()))
        {
            throw new InvalidOperationException($"Ya existe un fondo monetario con el nombre '{nombre}'.");
        }

        var nuevoFondo = new FondoMonetario
        {
            Nombre = nombre,
            Tipo = tipo
        };

        _context.FondosMonetarios.Add(nuevoFondo);
        await _context.SaveChangesAsync();
        return nuevoFondo;
    }

    public async Task ActualizarAsync(FondoMonetario fondoMonetario)
    {
        if (fondoMonetario == null)
            throw new ArgumentNullException(nameof(fondoMonetario));

        if (await _context.FondosMonetarios.AnyAsync(fm => fm.Nombre.Equals(fondoMonetario.Nombre, StringComparison.CurrentCultureIgnoreCase) && fm.Id != fondoMonetario.Id))
        {
            throw new InvalidOperationException($"Ya existe otro fondo monetario con el nombre '{fondoMonetario.Nombre}'.");
        }

        _context.Entry(fondoMonetario).State = EntityState.Modified;
        await _context.SaveChangesAsync();
    }

    public async Task EliminarAsync(int id)
    {
        var fondo = await _context.FondosMonetarios.FindAsync(id);
        if (fondo != null)
        {
            bool enUsoEnGastos = await _context.RegistrosGastos.AnyAsync(rg => rg.FondoMonetarioId == id);
            bool enUsoEnDepositos = await _context.Depositos.AnyAsync(d => d.FondoMonetarioId == id);

            if (enUsoEnGastos || enUsoEnDepositos)
                throw new InvalidOperationException("Este fondo monetario está en uso y no puede ser eliminado.");

            _context.FondosMonetarios.Remove(fondo);
            await _context.SaveChangesAsync();
        }
    }
}