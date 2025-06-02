using GastosApp.Application.Interfaces;
using GastosApp.Domain.Entities;
using GastosApp.Domain.Enums;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;

namespace GastosApp.Web.Controllers;

[Authorize]
public class FondoMonetarioController : Controller
{
    private readonly IFondoMonetarioService _fondoMonetarioService;
    public FondoMonetarioController(IFondoMonetarioService fondoMonetarioService)
    {
        _fondoMonetarioService = fondoMonetarioService;
    }
    private void CargarTiposFondoViewBag(TipoFondo? tipoSeleccionado = null)
    {
        ViewBag.TiposFondo = new SelectList(
            Enum.GetValues<TipoFondo>().Select(e => new SelectListItem
            {
                Value = ((int)e).ToString(),
                Text = e.ToString()
            }).ToList(),
            "Value",
            "Text",
            tipoSeleccionado.HasValue ? ((int)tipoSeleccionado.Value).ToString() : null
        );
    }

    public async Task<IActionResult> Index()
    {
        var fondos = await _fondoMonetarioService.ObtenerTodosAsync();
        return View(fondos);
    }

    public async Task<IActionResult> Details(int? id)
    {
        if (id == null) return NotFound();
        var fondo = await _fondoMonetarioService.ObtenerPorIdAsync(id.Value);
        if (fondo == null) return NotFound();
        return View(fondo);
    }

    public IActionResult Create()
    {
        CargarTiposFondoViewBag();
        return View(new FondoMonetario());
    }

    [HttpPost]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> Create([Bind("Nombre,Tipo")] FondoMonetario fondoMonetarioInput)
    {

        if (string.IsNullOrWhiteSpace(fondoMonetarioInput.Nombre))
        {
            ModelState.AddModelError("Nombre", "El nombre del fondo monetario es obligatorio.");
        }




        if (ModelState.IsValid)
        {
            try
            {
                await _fondoMonetarioService.CrearAsync(fondoMonetarioInput.Nombre, fondoMonetarioInput.Tipo);
                TempData["SuccessMessage"] = "Fondo Monetario creado exitosamente.";
                return RedirectToAction(nameof(Index));
            }
            catch (InvalidOperationException ex)
            {
                ModelState.AddModelError(string.Empty, ex.Message);
            }
            catch (ArgumentException ex)
            {
                ModelState.AddModelError("Nombre", ex.Message);
            }
            catch (Exception ex)
            {

                ModelState.AddModelError(string.Empty, "Ocurrió un error inesperado al crear el fondo: " + ex.Message);
            }
        }
        CargarTiposFondoViewBag(fondoMonetarioInput.Tipo);
        return View(fondoMonetarioInput);
    }

    public async Task<IActionResult> Edit(int? id)
    {
        if (id == null) return NotFound();
        var fondo = await _fondoMonetarioService.ObtenerPorIdAsync(id.Value);
        if (fondo == null) return NotFound();

        CargarTiposFondoViewBag(fondo.Tipo);
        return View(fondo);
    }

    [HttpPost]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> Edit(int id, [Bind("Id,Nombre,Tipo")] FondoMonetario fondoMonetario)
    {
        if (id != fondoMonetario.Id) return NotFound();
        if (string.IsNullOrWhiteSpace(fondoMonetario.Nombre))
        {
            ModelState.AddModelError("Nombre", "El nombre del fondo monetario es obligatorio.");
        }
        if (ModelState.IsValid)
        {
            try
            {
                await _fondoMonetarioService.ActualizarAsync(fondoMonetario);
                TempData["SuccessMessage"] = "Fondo Monetario actualizado exitosamente.";
                return RedirectToAction(nameof(Index));
            }
            catch (DbUpdateConcurrencyException)
            {
                if (await _fondoMonetarioService.ObtenerPorIdAsync(fondoMonetario.Id) == null) return NotFound();
                ModelState.AddModelError(string.Empty, "El registro fue modificado por otro usuario. Recargue la página e intente de nuevo.");
            }
            catch (InvalidOperationException ex)
            {
                ModelState.AddModelError(string.Empty, ex.Message);
            }
            catch (ArgumentException ex)
            {
                ModelState.AddModelError("Nombre", ex.Message);
            }
            catch (Exception ex)
            {

                ModelState.AddModelError(string.Empty, "Ocurrió un error inesperado al actualizar: " + ex.Message);
            }
        }
        CargarTiposFondoViewBag(fondoMonetario.Tipo);
        return View(fondoMonetario);
    }

    public async Task<IActionResult> Delete(int? id)
    {
        if (id == null) return NotFound();
        var fondo = await _fondoMonetarioService.ObtenerPorIdAsync(id.Value);
        if (fondo == null) return NotFound();
        return View(fondo);
    }

    [HttpPost, ActionName("Delete")]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> DeleteConfirmed(int id)
    {
        var fondo = await _fondoMonetarioService.ObtenerPorIdAsync(id);
        if (fondo == null)
        {
            TempData["ErrorMessage"] = "El Fondo Monetario que intenta eliminar ya no existe.";
            return RedirectToAction(nameof(Index));
        }
        try
        {
            await _fondoMonetarioService.EliminarAsync(id);
            TempData["SuccessMessage"] = "Fondo Monetario eliminado exitosamente.";
            return RedirectToAction(nameof(Index));
        }
        catch (InvalidOperationException ex)
        {
            TempData["ErrorMessage"] = ex.Message;
        }
        catch (Exception ex)
        {

            TempData["ErrorMessage"] = "Ocurrió un error al intentar eliminar el fondo monetario: " + ex.Message;
        }


        return View("Delete", fondo);
    }
}