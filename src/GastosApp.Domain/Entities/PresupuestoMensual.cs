using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace GastosApp.Domain.Entities;

public class PresupuestoMensual
{
    [Key]
    [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
    public int Id { get; set; }

    [Required]
    public int Anio { get; set; }

    [Required]
    [Range(1, 12)]
    public int Mes { get; set; }

    [Required]
    public int TipoGastoId { get; set; }
    public virtual TipoGasto TipoGasto { get; set; } = null!;

    [Required]
    [Column(TypeName = "decimal(18, 2)")]
    public decimal MontoPresupuestado { get; set; }

    [Required]
    public string UsuarioId { get; set; }  = null!;
    public virtual Usuario Usuario { get; set; } = null!;
}
