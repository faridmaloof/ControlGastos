using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using GastosApp.Domain.Enums;

namespace GastosApp.Domain.Entities;

public class RegistroGasto
{
    [Key]
    [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
    public int Id { get; set; }

    [Required]
    public DateTime Fecha { get; set; } = DateTime.UtcNow;

    [Required]
    public int FondoMonetarioId { get; set; }
    public virtual FondoMonetario FondoMonetario { get; set; } = null!;

    [MaxLength(500)]
    public string Observaciones { get; set; }= string.Empty;

    [MaxLength(150)]
    public string NombreComercio { get; set; }= string.Empty;

    [Required]
    public TipoDocumento TipoDocumento { get; set; }

    [Required]
    public string UsuarioId { get; set; } = null!;
    public virtual Usuario Usuario { get; set; } = null!;

    public virtual ICollection<RegistroGastoDetalle> Detalles { get; set; }= new HashSet<RegistroGastoDetalle>();
}
