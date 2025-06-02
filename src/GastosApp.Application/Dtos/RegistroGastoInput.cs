using System.ComponentModel.DataAnnotations;
using GastosApp.Domain.Enums;

namespace GastosApp.Application.Dtos;

public class RegistroGastoInput
{
    [Required]
    public DateTime Fecha { get; set; } = DateTime.UtcNow;

    [Required]
    public int FondoMonetarioId { get; set; }

    public string Observaciones { get; set; } = string.Empty;
    public string NombreComercio { get; set; }= string.Empty;

    [Required]
    public TipoDocumento TipoDocumento { get; set; }

    [Required]
    [MinLength(1, ErrorMessage = "Debe agregar al menos un detalle de gasto.")]
    public List<RegistroGastoDetalleInput> Detalles { get; set; } = [];
}
