using GastosApp.Domain.Entities;

namespace GastosApp.Application.Interfaces;

public interface ITipoGastoService
{
    Task<IEnumerable<TipoGasto>> ObtenerTodosAsync();
    Task<TipoGasto> ObtenerPorIdAsync(int id);
    Task<TipoGasto> CrearAsync(string codigo, string nombre);
    Task ActualizarAsync(TipoGasto tipoGasto);
    Task EliminarAsync(int id);
    Task<string> ObtenerSiguienteCodigoAsync();
}
