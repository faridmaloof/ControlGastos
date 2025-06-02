namespace GastosApp.Application.Dtos;

public class PresupuestoEjecucionDto
{
    public string TipoGastoNombre { get; set; } = null!;
    public int TipoGastoId { get; set; }
    public decimal MontoPresupuestado { get; set; }
    public decimal MontoEjecutado { get; set; }
}
