using GastosApp.Application.Interfaces;
using GastosApp.Application.Services;
using GastosApp.Domain.Entities;
using GastosApp.Infrastructure.Persistence;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

var connectionString = builder.Configuration.GetConnectionString("DefaultConnection")
    ?? throw new InvalidOperationException("La cadena de conexión 'DefaultConnection' no fue encontrada.");

builder.Services.AddDbContext<GastosAppDbContext>(options =>
    options.UseSqlServer(connectionString));

builder.Services.AddIdentity<Usuario, IdentityRole>(options =>
    {
        options.Password.RequireDigit = false;
        options.Password.RequiredLength = 4;
        options.Password.RequireNonAlphanumeric = false;
        options.Password.RequireUppercase = false;
        options.Password.RequireLowercase = false;

        options.Password.RequiredUniqueChars = 0;

        options.SignIn.RequireConfirmedAccount = false;
        options.SignIn.RequireConfirmedEmail = false;  
        options.SignIn.RequireConfirmedPhoneNumber = false;
    })
    .AddEntityFrameworkStores<GastosAppDbContext>()
    .AddDefaultTokenProviders()
    .AddDefaultUI();

builder.Services.AddControllersWithViews();
builder.Services.AddRazorPages();

builder.Services.AddAuthorization(options =>
{
    options.AddPolicy("SoloAdmin", policy =>
    {
        policy.RequireAuthenticatedUser();
        policy.RequireUserName("admin");
    });
});

builder.Services.AddScoped<ITipoGastoService, TipoGastoService>();
builder.Services.AddScoped<IFondoMonetarioService, FondoMonetarioService>();
builder.Services.AddScoped<IPresupuestoService, PresupuestoService>();
builder.Services.AddScoped<IDepositoService, DepositoService>();
builder.Services.AddScoped<IRegistroGastoService, RegistroGastoService>();

builder.Services.AddScoped<IDepositoService, DepositoService>();
builder.Services.AddScoped<IRegistroGastoService, RegistroGastoService>();
builder.Services.AddScoped<IConsultaService, ConsultaService>();

builder.Services.ConfigureApplicationCookie(options =>
{
    options.LoginPath = "/Identity/Account/Login";
    options.LogoutPath = "/Identity/Account/Logout";
    options.AccessDeniedPath = "/Identity/Account/AccessDenied";
});


var app = builder.Build();

if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
    app.UseHsts();
}
else
{
    app.UseDeveloperExceptionPage();
    app.UseMigrationsEndPoint();
}

app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();

app.UseAuthentication();
app.UseAuthorization();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}");
app.MapRazorPages();

using (var scope = app.Services.CreateScope())
{
    var services = scope.ServiceProvider;
    try
    {
        var context = services.GetRequiredService<GastosAppDbContext>();
        context.Database.Migrate();

        var userManager = services.GetRequiredService<UserManager<Usuario>>();
        var roleManager = services.GetRequiredService<RoleManager<IdentityRole>>();
        await SeedData.Initialize(userManager, roleManager);
    }
    catch (Exception ex)
    {
        var logger = services.GetRequiredService<ILogger<Program>>();
        logger.LogError(ex, "Ocurrió un error al migrar o sembrar la base de datos.");
    }
}

app.Run();