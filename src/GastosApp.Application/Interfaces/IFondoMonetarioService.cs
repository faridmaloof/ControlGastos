using GastosApp.Domain.Entities;
using GastosApp.Domain.Enums;

namespace GastosApp.Application.Interfaces;

public interface IFondoMonetarioService
{
        Task<IEnumerable<FondoMonetario>> ObtenerTodosAsync();
        Task<FondoMonetario> ObtenerPorIdAsync(int id);
        Task<FondoMonetario> CrearAsync(string nombre, TipoFondo tipo);
        Task ActualizarAsync(FondoMonetario fondoMonetario);
        Task EliminarAsync(int id);
}
