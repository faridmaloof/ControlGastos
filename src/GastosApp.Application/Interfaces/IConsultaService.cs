using GastosApp.Application.Dtos;

namespace GastosApp.Application.Interfaces;

public interface IConsultaService
{
    Task<IEnumerable<MovimientoDto>> ObtenerMovimientosPorRangoFechasAsync(string usuarioId, DateTime fechaInicio, DateTime fechaFin);

    Task<DatosGraficoPresupuestoDto> ObtenerDatosGraficoPresupuestoAsync(string usuarioId, DateTime fechaInicio, DateTime fechaFin);
}