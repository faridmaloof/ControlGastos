using GastosApp.Domain.Entities;

namespace GastosApp.Application.Interfaces;

public interface IDepositoService
{
    Task<Deposito> RegistrarDepositoAsync(string usuarioId, DateTime fecha, int fondoMonetarioId, decimal monto);
    Task<IEnumerable<Deposito>> ObtenerDepositosPorUsuarioAsync(string usuarioId, DateTime fechaInicio, DateTime fechaFin);
    Task<Deposito> ObtenerDepositoPorIdAsync(int id, string usuarioId);
    Task EliminarDepositoAsync(int id, string usuarioId);
}
