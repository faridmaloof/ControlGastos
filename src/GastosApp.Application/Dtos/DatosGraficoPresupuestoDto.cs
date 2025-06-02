namespace GastosApp.Application.Dtos;

public class DatosGraficoPresupuestoDto
{
    public List<string> LabelsTiposGasto { get; set; } = [];
    public List<decimal> MontosPresupuestados { get; set; } = [];
    public List<decimal> MontosEjecutados { get; set; } = [];
}
