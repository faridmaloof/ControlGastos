using System.Security.Claims;
using GastosApp.Application.Interfaces;
using GastosApp.Domain.Entities;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace GastosApp.Web.Controllers;

[Authorize]
public class PresupuestoController(IPresupuestoService presupuestoService,
                                   ITipoGastoService tipoGastoService,
                                   UserManager<Usuario> userManager,
                                    ILogger<TipoGastoController> logger) : Controller
{
    private readonly IPresupuestoService _presupuestoService = presupuestoService;
    private readonly ITipoGastoService _tipoGastoService = tipoGastoService;
    private readonly UserManager<Usuario> _userManager = userManager;
    private readonly ILogger<TipoGastoController> _logger = logger;

    private string ObtenerUsuarioIdActual()
    {
        return User.FindFirstValue(ClaimTypes.NameIdentifier);
    }

    public async Task<IActionResult> Index(int? anio, int? mes)
    {
        var usuarioId = ObtenerUsuarioIdActual();
        if (string.IsNullOrEmpty(usuarioId)) return Challenge();

        int anioActual = anio ?? DateTime.Now.Year;
        int mesActual = mes ?? DateTime.Now.Month;

        ViewBag.AnioSeleccionado = anioActual;
        ViewBag.MesSeleccionado = mesActual;
        ViewBag.Anios = Enumerable.Range(DateTime.Now.Year - 5, 10).Select(a => new SelectListItem { Value = a.ToString(), Text = a.ToString() });
        ViewBag.Meses = Enumerable.Range(1, 12).Select(m => new SelectListItem { Value = m.ToString(), Text = new DateTime(2000, m, 1).ToString("MMMM") });

        var presupuestos = await _presupuestoService.ObtenerPresupuestosPorUsuarioAsync(usuarioId, anioActual, mesActual);
        return View(presupuestos);
    }

    private async Task CargarTiposGastoViewBag(int? tipoGastoIdSeleccionado = null)
    {
        var tiposGasto = await _tipoGastoService.ObtenerTodosAsync();
        ViewBag.TiposGasto = new SelectList(tiposGasto.OrderBy(t => t.Nombre), "Id", "Nombre", tipoGastoIdSeleccionado);
    }


    public async Task<IActionResult> CrearOEditar(int? id, int? anio, int? mes)
    {
        await CargarTiposGastoViewBag();
        ViewBag.AnioActual = anio ?? DateTime.Now.Year;
        ViewBag.MesActual = mes ?? DateTime.Now.Month;

        if (id == null)
        {

            var modeloPresupuesto = new PresupuestoMensual
            {
                Anio = anio ?? DateTime.Now.Year,
                Mes = mes ?? DateTime.Now.Month
            };
            return View(modeloPresupuesto);
        }
        else
        {
            var usuarioId = ObtenerUsuarioIdActual();
            var presupuesto = await _presupuestoService.ObtenerPorIdAsync(id.Value);
            if (presupuesto == null || presupuesto.UsuarioId != usuarioId)
                return NotFound();
            await CargarTiposGastoViewBag(presupuesto.TipoGastoId);
            return View(presupuesto);
        }
    }

    [HttpPost]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> CrearOEditar(int? id, [Bind("Id,Anio,Mes,TipoGastoId,MontoPresupuestado")] PresupuestoMensual presupuestoInput)
    {
        _logger.LogDebug("INGRESO POST CrearOEditar");
        var usuarioId = ObtenerUsuarioIdActual();
        if (string.IsNullOrEmpty(usuarioId)) return Challenge();

        if (id.HasValue && id.Value != presupuestoInput.Id)
            return BadRequest();

        if (!id.HasValue && presupuestoInput.Id != 0)
            return BadRequest("No se puede especificar un ID al crear un nuevo presupuesto.");


        presupuestoInput.UsuarioId = usuarioId;


        if (presupuestoInput.TipoGastoId == 0)
            ModelState.AddModelError("TipoGastoId", "Debe seleccionar un Tipo de Gasto.");


        if (ModelState.ContainsKey(nameof(PresupuestoMensual.Usuario)))
            ModelState.Remove(nameof(PresupuestoMensual.Usuario));
        if (ModelState.ContainsKey(nameof(PresupuestoMensual.UsuarioId)))
            ModelState.Remove(nameof(PresupuestoMensual.UsuarioId));
        if (ModelState.ContainsKey(nameof(PresupuestoMensual.TipoGasto)))
            ModelState.Remove(nameof(PresupuestoMensual.TipoGasto));

        if (ModelState.IsValid)
        {
            try
            {
                await _presupuestoService.GuardarPresupuestoAsync(
                    usuarioId,
                    presupuestoInput.Anio,
                    presupuestoInput.Mes,
                    presupuestoInput.TipoGastoId,
                    presupuestoInput.MontoPresupuestado);

                TempData["SuccessMessage"] = "Presupuesto guardado exitosamente.";
                return RedirectToAction(nameof(Index), new { anio = presupuestoInput.Anio, mes = presupuestoInput.Mes });
            }
            catch (ArgumentException ex)
            {
                ModelState.AddModelError(string.Empty, ex.Message);
            }
            catch (Exception ex)
            {
                ModelState.AddModelError(string.Empty, "Ocurrió un error al guardar el presupuesto: " + ex.Message);
            }
        }
        else
        {
            foreach (var entry in ModelState)
            {
                var fieldName = entry.Key;
                var errors = entry.Value.Errors;

                foreach (var error in errors)
                {
                    ModelState.AddModelError(string.Empty, "Error en el campo '" + fieldName + "': " + error.ErrorMessage + "");
                }
            }
        }

        await CargarTiposGastoViewBag(presupuestoInput.TipoGastoId);

        ViewBag.AnioActual = presupuestoInput.Anio;
        ViewBag.MesActual = presupuestoInput.Mes;
        return View(presupuestoInput);
    }

    public async Task<IActionResult> Delete(int? id)
    {
        if (id == null) return NotFound();

        var usuarioId = ObtenerUsuarioIdActual();
        var presupuesto = await _presupuestoService.ObtenerPorIdAsync(id.Value);

        if (presupuesto == null || presupuesto.UsuarioId != usuarioId)
        {
            return NotFound();
        }
        return View(presupuesto);
    }

    [HttpPost, ActionName("Delete")]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> DeleteConfirmed(int id)
    {
        var usuarioId = ObtenerUsuarioIdActual();
        var presupuesto = await _presupuestoService.ObtenerPorIdAsync(id);
        if (presupuesto == null || presupuesto.UsuarioId != usuarioId)
        {
            TempData["ErrorMessage"] = "No se encontró el presupuesto o no tiene permisos para eliminarlo.";
            return RedirectToAction(nameof(Index));
        }

        try
        {
            await _presupuestoService.EliminarPresupuestoAsync(id, usuarioId);
            TempData["SuccessMessage"] = "Presupuesto eliminado exitosamente.";
            return RedirectToAction(nameof(Index), new { anio = presupuesto.Anio, mes = presupuesto.Mes });
        }
        catch (KeyNotFoundException ex)
        {
            TempData["ErrorMessage"] = ex.Message;
        }
        catch (Exception)
        {
            TempData["ErrorMessage"] = "Ocurrió un error al eliminar el presupuesto.";
        }
        return RedirectToAction(nameof(Delete), new { id = id });
    }
}
