namespace GastosApp.Application.Dtos;

public class AlertaSobregiro
{
    public string TipoGastoNombre { get; set; }
    public int TipoGastoId { get; set; }
    public decimal MontoPresupuestado { get; set; }
    public decimal MontoGastoActual { get; set; }
    public decimal TotalGastadoEnMes { get; set; }
    public decimal MontoSobregirado { get; set; }

    public AlertaSobregiro(string tipoGastoNombre, int tipoGastoId, decimal montoPresupuestado, decimal montoGastoActual, decimal totalGastadoEnMes)
    {
        TipoGastoNombre = tipoGastoNombre;
        TipoGastoId = tipoGastoId;
        MontoPresupuestado = montoPresupuestado;
        MontoGastoActual = montoGastoActual;
        TotalGastadoEnMes = totalGastadoEnMes;
        MontoSobregirado = (TotalGastadoEnMes + MontoGastoActual) - MontoPresupuestado;
    }
}
