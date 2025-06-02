using GastosApp.Domain.Entities;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

namespace GastosApp.Infrastructure.Persistence;

public class GastosAppDbContext(DbContextOptions<GastosAppDbContext> options) 
: IdentityDbContext<Usuario, IdentityRole, string>(options)
{
    public DbSet<TipoGasto> TiposGastos { get; set; }
    public DbSet<FondoMonetario> FondosMonetarios { get; set; }
    public DbSet<RegistroGasto> RegistrosGastos { get; set; }
    public DbSet<RegistroGastoDetalle> RegistrosGastosDetalles { get; set; }
    public DbSet<Deposito> Depositos { get; set; }
    public DbSet<PresupuestoMensual> PresupuestosMensuales { get; set; }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        base.OnModelCreating(modelBuilder);
       
        modelBuilder.Entity<RegistroGastoDetalle>()
            .Property(d => d.Monto)
            .HasColumnType("decimal(18,2)");

        modelBuilder.Entity<Deposito>()
            .Property(d => d.Monto)
            .HasColumnType("decimal(18,2)");

        modelBuilder.Entity<PresupuestoMensual>()
            .Property(p => p.MontoPresupuestado)
            .HasColumnType("decimal(18,2)");

       
        modelBuilder.Entity<TipoGasto>()
            .HasIndex(tg => tg.Codigo)
            .IsUnique();

       
        modelBuilder.Entity<RegistroGasto>()
            .HasMany(rg => rg.Detalles)
            .WithOne(d => d.RegistroGasto)
            .HasForeignKey(d => d.RegistroGastoId)
            .OnDelete(DeleteBehavior.Cascade);
    }
}
