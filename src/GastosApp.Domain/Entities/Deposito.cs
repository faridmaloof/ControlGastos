using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace GastosApp.Domain.Entities;

public class Deposito
{
    [Key]
    [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
    public int Id { get; set; }

    [Required]
    public DateTime Fecha { get; set; } = DateTime.UtcNow;

    [Required]
    public int FondoMonetarioId { get; set; }
    public virtual FondoMonetario FondoMonetario { get; set; } = null!;

    [Required]
    [Column(TypeName = "decimal(18, 2)")]
    public decimal Monto { get; set; }

    [Required]
    public string UsuarioId { get; set; } = null!;
    public virtual Usuario Usuario { get; set; } = null!;
}
