using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace GastosApp.Domain.Entities;

public class RegistroGastoDetalle
{
    [Key]
    [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
    public int Id { get; set; }

    [Required]
    public int RegistroGastoId { get; set; }
    public virtual RegistroGasto RegistroGasto { get; set; } = null!;

    [Required]
    public int TipoGastoId { get; set; }
    public virtual TipoGasto TipoGasto { get; set; } = null!;

    [Required]
    [Column(TypeName = "decimal(18, 2)")] 
    public decimal Monto { get; set; }
}
