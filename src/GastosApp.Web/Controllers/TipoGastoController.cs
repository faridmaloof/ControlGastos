using GastosApp.Application.Interfaces;
using GastosApp.Domain.Entities;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace GastosApp.Web.Controllers;


[Authorize]
public class TipoGastoController(ITipoGastoService tipoGastoService,
 ILogger<TipoGastoController> logger) : Controller
{
    private readonly ILogger<TipoGastoController> _logger = logger;
    private readonly ITipoGastoService _tipoGastoService = tipoGastoService;

   
    public async Task<IActionResult> Index()
    {
        var tiposGasto = await _tipoGastoService.ObtenerTodosAsync();
        return View(tiposGasto);
    }

   
    public async Task<IActionResult> Details(int? id)
    {
        if (id == null)
            return NotFound();

        var tipoGasto = await _tipoGastoService.ObtenerPorIdAsync(id.Value);
        if (tipoGasto == null)
            return NotFound();

        return View(tipoGasto);
    }

   
    public IActionResult Create()
    {
        _logger.LogDebug("INGRESO GET CREATE");
        return View(new TipoGasto());
    }

   
    [HttpPost]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> Create([Bind("Nombre,Codigo")] TipoGasto tipoGastoInput)
    {
        _logger.LogDebug("INGRESO POST CREATE");

        if (string.IsNullOrWhiteSpace(tipoGastoInput.Codigo))
            ModelState.AddModelError("Codigo", "El código del tipo de gasto es obligatorio.");

        if (string.IsNullOrWhiteSpace(tipoGastoInput.Nombre))
            ModelState.AddModelError("Nombre", "El nombre del tipo de gasto es obligatorio.");

        if (ModelState.IsValid)
            try
            {
                _logger.LogDebug("INGRESO a CREATE el registro con nombre: {Nombre}", tipoGastoInput.Nombre);
                await _tipoGastoService.CrearAsync(tipoGastoInput.Codigo, tipoGastoInput.Nombre);
                TempData["SuccessMessage"] = "Tipo de Gasto creado exitosamente.";
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
               
                ModelState.AddModelError(string.Empty, "Ocurrió un error inesperado al crear el tipo de gasto: " + ex.Message);
            }
        else
        {
           
            _logger.LogWarning("Modelo inválido al intentar crear Tipo de Gasto: {Errors}", ModelState.Values.SelectMany(v => v.Errors).Select(e => e.ErrorMessage));
        }
        return View(tipoGastoInput);
    }

   
    public async Task<IActionResult> Edit(int? id)
    {
        if (id == null)
        {
            return NotFound();
        }

        var tipoGasto = await _tipoGastoService.ObtenerPorIdAsync(id.Value);
        if (tipoGasto == null)
        {
            return NotFound();
        }
        return View(tipoGasto);
    }

   
    [HttpPost]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> Edit(int id, [Bind("Id,Nombre,Codigo")] TipoGasto tipoGasto)
    {
        if (id != tipoGasto.Id)
        {
            return NotFound();
        }

       
       
        ModelState.Remove("Codigo");

        if (string.IsNullOrWhiteSpace(tipoGasto.Nombre))
        {
            ModelState.AddModelError("Nombre", "El nombre del tipo de gasto es obligatorio.");
        }

        if (ModelState.IsValid)
        {
            try
            {
               
               
                var tipoGastoExistente = await _tipoGastoService.ObtenerPorIdAsync(id);
                if (tipoGastoExistente == null)
                {
                    return NotFound();
                }
                tipoGastoExistente.Nombre = tipoGasto.Nombre;

                await _tipoGastoService.ActualizarAsync(tipoGastoExistente);
                TempData["SuccessMessage"] = "Tipo de Gasto actualizado exitosamente.";
                return RedirectToAction(nameof(Index));
            }
            catch (DbUpdateConcurrencyException)
            {
                if (await _tipoGastoService.ObtenerPorIdAsync(tipoGasto.Id) == null)
                {
                    return NotFound();
                }
                else
                {
                    ModelState.AddModelError(string.Empty, "El registro fue modificado por otro usuario. Recargue la página e intente de nuevo.");
                }
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
       
       
       
        if (string.IsNullOrEmpty(tipoGasto.Codigo) && tipoGasto.Id > 0)
        {
            var original = await _tipoGastoService.ObtenerPorIdAsync(tipoGasto.Id);
            if (original != null) tipoGasto.Codigo = original.Codigo;
        }
        return View(tipoGasto);
    }

   
    public async Task<IActionResult> Delete(int? id)
    {
        if (id == null)
        {
            return NotFound();
        }

        var tipoGasto = await _tipoGastoService.ObtenerPorIdAsync(id.Value);
        if (tipoGasto == null)
        {
            return NotFound();
        }

        return View(tipoGasto);
    }

   
    [HttpPost, ActionName("Delete")]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> DeleteConfirmed(int id)
    {
        var tipoGasto = await _tipoGastoService.ObtenerPorIdAsync(id);
        if (tipoGasto == null)
        {
            TempData["ErrorMessage"] = "El Tipo de Gasto que intenta eliminar ya no existe.";
            return RedirectToAction(nameof(Index));
        }

        try
        {
            await _tipoGastoService.EliminarAsync(id);
            TempData["SuccessMessage"] = "Tipo de Gasto eliminado exitosamente.";
            return RedirectToAction(nameof(Index));
        }
        catch (InvalidOperationException ex)
        {
            TempData["ErrorMessage"] = ex.Message;
        }
        catch (Exception ex)
        {
           
            TempData["ErrorMessage"] = "Ocurrió un error al intentar eliminar el tipo de gasto: " + ex.Message;
        }
       
       
        return View("Delete", tipoGasto);
    }
}