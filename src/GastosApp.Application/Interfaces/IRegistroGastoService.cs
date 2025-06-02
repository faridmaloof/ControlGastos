using GastosApp.Application.Dtos;
using GastosApp.Domain.Entities;

namespace GastosApp.Application.Interfaces;

public interface IRegistroGastoService
{
    Task<(RegistroGasto GastoCreado, List<AlertaSobregiro> Alertas)> RegistrarGastoAsync(string usuarioId, RegistroGastoInput gastoInput);

    Task<IEnumerable<RegistroGasto>> ObtenerGastosPorUsuarioAsync(string usuarioId, DateTime fechaInicio, DateTime fechaFin);
    Task<RegistroGasto> ObtenerGastoPorIdAsync(int id, string usuarioId);
    Task EliminarGastoAsync(int id, string usuarioId);
}
