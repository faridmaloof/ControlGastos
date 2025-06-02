using GastosApp.Domain.Entities;

namespace GastosApp.Application.Interfaces;

public interface IPresupuestoService
{
        Task<IEnumerable<PresupuestoMensual>> ObtenerPresupuestosPorUsuarioAsync(string usuarioId, int anio, int mes);
        Task<PresupuestoMensual> ObtenerPorIdAsync(int id);
        Task<PresupuestoMensual> GuardarPresupuestoAsync(string usuarioId, int anio, int mes, int tipoGastoId, decimal monto);
        Task EliminarPresupuestoAsync(int id, string usuarioId);
}
