using System.Security.Claims;
using GastosApp.Application.Interfaces;
using GastosApp.Domain.Entities;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace GastosApp.Web.Controllers;

[Authorize]
public class DepositoController(
    IDepositoService depositoService,
    IFondoMonetarioService fondoMonetarioService,
    UserManager<Usuario> userManager) : Controller
{
    private readonly IDepositoService _depositoService = depositoService;
    private readonly IFondoMonetarioService _fondoMonetarioService = fondoMonetarioService;
    private readonly UserManager<Usuario> _userManager = userManager;

    private string ObtenerUsuarioIdActual()
        => User.FindFirstValue(ClaimTypes.NameIdentifier);

    private async Task CargarFondosViewBag(int? fondoId = null)
    {
        ViewBag.FondosMonetarios = new SelectList(await _fondoMonetarioService.ObtenerTodosAsync(), "Id", "Nombre", fondoId);
    }

    public async Task<IActionResult> Index(DateTime? fechaInicio, DateTime? fechaFin)
    {
        var usuarioId = ObtenerUsuarioIdActual();
        DateTime inicio = fechaInicio ?? DateTime.Today.AddMonths(-1);
        DateTime fin = fechaFin ?? DateTime.Today;

        ViewBag.FechaInicio = inicio.ToString("yyyy-MM-dd");
        ViewBag.FechaFin = fin.ToString("yyyy-MM-dd");

        var depositos = await _depositoService.ObtenerDepositosPorUsuarioAsync(usuarioId, inicio, fin);
        return View(depositos);
    }

    public async Task<IActionResult> Create()
    {
        await CargarFondosViewBag();
        var model = new Deposito { Fecha = DateTime.Today };
        return View(model);
    }

    [HttpPost]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> Create([Bind("Fecha,FondoMonetarioId,Monto")] Deposito depositoInput)
    {
        var usuarioId = ObtenerUsuarioIdActual();

        depositoInput.UsuarioId = usuarioId;

        if (ModelState.ContainsKey(nameof(Deposito.FondoMonetario)))
            ModelState.Remove(nameof(Deposito.FondoMonetario));
        if (ModelState.ContainsKey(nameof(Deposito.Usuario)))
            ModelState.Remove(nameof(Deposito.Usuario));
        if (ModelState.ContainsKey(nameof(Deposito.UsuarioId)))
            ModelState.Remove(nameof(Deposito.UsuarioId));

        if (ModelState.IsValid)
        {
            try
            {
                await _depositoService.RegistrarDepositoAsync(usuarioId, depositoInput.Fecha, depositoInput.FondoMonetarioId, depositoInput.Monto);
                TempData["SuccessMessage"] = "Depósito registrado exitosamente.";
                return RedirectToAction(nameof(Index));
            }
            catch (ArgumentException ex) { ModelState.AddModelError(string.Empty, ex.Message); }
            catch (KeyNotFoundException ex) { ModelState.AddModelError("FondoMonetarioId", ex.Message); }
            catch (Exception ex)
            {
                ModelState.AddModelError(string.Empty, "Ocurrió un error inesperado: " + ex.Message);
            }
        }

        await CargarFondosViewBag(depositoInput.FondoMonetarioId);
        return View(depositoInput);
    }

    public async Task<IActionResult> Delete(int? id)
    {
        if (id == null) return NotFound();
        var usuarioId = ObtenerUsuarioIdActual();
        var deposito = await _depositoService.ObtenerDepositoPorIdAsync(id.Value, usuarioId);
        if (deposito == null) return NotFound();
        return View(deposito);
    }

    [HttpPost, ActionName("Delete")]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> DeleteConfirmed(int id)
    {
        var usuarioId = ObtenerUsuarioIdActual();
        try
        {
            await _depositoService.EliminarDepositoAsync(id, usuarioId);
            TempData["SuccessMessage"] = "Depósito eliminado exitosamente.";
        }
        catch (KeyNotFoundException)
        {
            TempData["ErrorMessage"] = "Depósito no encontrado o no tiene permisos para eliminarlo.";
        }
        catch (Exception)
        {
            TempData["ErrorMessage"] = "Ocurrió un error al eliminar el depósito.";
        }
        return RedirectToAction(nameof(Index));
    }

}
