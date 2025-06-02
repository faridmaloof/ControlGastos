using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using GastosApp.Domain.Enums;

namespace GastosApp.Domain.Entities;

public class FondoMonetario
{
    [Key]
    [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
    public int Id { get; set; }

    [Required(ErrorMessage = "El nombre del fondo monetario es obligatorio.")]
    [MaxLength(100, ErrorMessage = "El nombre no puede exceder los 100 caracteres.")]
    public string Nombre { get; set; } = null!;

    [Required(ErrorMessage = "Debe seleccionar un tipo de fondo.")]
    public TipoFondo Tipo { get; set; } = TipoFondo.Indefinido;

    public virtual ICollection<RegistroGasto> RegistrosGastos { get; set; } = new HashSet<RegistroGasto>();
    public virtual ICollection<Deposito> Depositos { get; set; } = new HashSet<Deposito>();

}
