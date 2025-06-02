using GastosApp.Domain.Entities;
using Microsoft.AspNetCore.Identity;

namespace GastosApp.Infrastructure.Persistence;

public static class SeedData
{
    public static async Task Initialize(UserManager<Usuario> userManager, RoleManager<IdentityRole> roleManager)
    {
        string adminUserName = "admin";
        string adminPassword = "admin";

        if (await userManager.FindByNameAsync(adminUserName) == null)
        {
            Usuario adminUser = new()
            {
                UserName = adminUserName,
                Email = "admin@example.com",
                EmailConfirmed = true
            };

            IdentityResult result = await userManager.CreateAsync(adminUser, adminPassword);

            adminUser = new()
            {
                UserName = "root@example.com",
                Email = "root@example.com",
                EmailConfirmed = true
            };

            result = await userManager.CreateAsync(adminUser, adminPassword);

            if (result.Succeeded)
                Console.WriteLine("Usuario 'admin' creado exitosamente.");
            else
            {
                Console.WriteLine("Error creando usuario 'admin':");
                foreach (var error in result.Errors)
                    Console.WriteLine($"- {error.Description}");
            }
        }
        else
            Console.WriteLine("Usuario 'admin' ya existe.");
    }
}