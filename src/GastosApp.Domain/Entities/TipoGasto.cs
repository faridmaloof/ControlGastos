using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace GastosApp.Domain.Entities;

public class TipoGasto
{
    [Key]
    [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
    public int Id { get; set; }

    [Required]
    [MaxLength(10)]
    public string Codigo { get; set; } = null!;

    [Required(ErrorMessage = "El nombre es obligatorio.")]
    [MaxLength(100)]
    public string Nombre { get; set; } = null!;

    public virtual ICollection<PresupuestoMensual> PresupuestosMensuales { get; set; } = new HashSet<PresupuestoMensual>();
    public virtual ICollection<RegistroGastoDetalle> RegistrosGastosDetalles { get; set; } = new HashSet<RegistroGastoDetalle>();
}
