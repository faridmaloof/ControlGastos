using Microsoft.AspNetCore.Identity;

namespace GastosApp.Domain.Entities;

public class Usuario : IdentityUser
{
    public virtual ICollection<PresupuestoMensual> PresupuestosMensuales { get; set; } = new HashSet<PresupuestoMensual>();
    public virtual ICollection<RegistroGasto> RegistrosGastos { get; set; } = new HashSet<RegistroGasto>();
    public virtual ICollection<Deposito> Depositos { get; set; } = new HashSet<Deposito>();
}