namespace GastosApp.Application.Dtos;

public class MovimientoDto
{
    public DateTime Fecha { get; set; }
    public string TipoMovimiento { get; set; } = null!;
    public string Descripcion { get; set; } = null!;
    public string FondoMonetarioNombre { get; set; } = null!;
    public decimal Ingreso { get; set; }
    public decimal Egreso { get; set; }
    public int? ReferenciaId { get; set; }
}
