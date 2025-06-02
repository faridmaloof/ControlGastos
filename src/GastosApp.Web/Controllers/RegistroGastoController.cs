using System.Security.Claims;
using GastosApp.Application.Dtos;
using GastosApp.Application.Interfaces;
using GastosApp.Domain.Entities;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace GastosApp.Web.Controllers
{
    [Authorize]
    public class RegistroGastoController(
        IRegistroGastoService registroGastoService,
        IFondoMonetarioService fondoMonetarioService,
        ITipoGastoService tipoGastoService,
        UserManager<Usuario> userManager) : Controller
    {
        private readonly IRegistroGastoService _registroGastoService = registroGastoService;
        private readonly IFondoMonetarioService _fondoMonetarioService = fondoMonetarioService;
        private readonly ITipoGastoService _tipoGastoService = tipoGastoService;
        private readonly UserManager<Usuario> _userManager = userManager;

        private string ObtenerUsuarioIdActual()
            => User.FindFirstValue(ClaimTypes.NameIdentifier);

        private async Task CargarViewBagsParaFormulario(int? fondoId = null, object tipoDocSeleccionado = null)
        {
            ViewBag.FondosMonetarios = new SelectList(await _fondoMonetarioService.ObtenerTodosAsync(), "Id", "Nombre", fondoId);
            ViewBag.TiposGasto = new SelectList(await _tipoGastoService.ObtenerTodosAsync(), "Id", "Nombre");
            ViewBag.TiposDocumento = new SelectList(Enum.GetValues(typeof(GastosApp.Domain.Enums.TipoDocumento))
                                        .Cast<GastosApp.Domain.Enums.TipoDocumento>()
                                        .Select(e => new SelectListItem { Value = ((int)e).ToString(), Text = e.ToString() }),
                                        "Value", "Text", tipoDocSeleccionado);
        }


        public async Task<IActionResult> Index(DateTime? fechaInicio, DateTime? fechaFin)
        {
            var usuarioId = ObtenerUsuarioIdActual();
            DateTime inicio = fechaInicio ?? DateTime.Today.AddMonths(-1);
            DateTime fin = fechaFin ?? DateTime.Today;

            ViewBag.FechaInicio = inicio.ToString("yyyy-MM-dd");
            ViewBag.FechaFin = fin.ToString("yyyy-MM-dd");

            var gastos = await _registroGastoService.ObtenerGastosPorUsuarioAsync(usuarioId, inicio, fin);
            return View(gastos);
        }


        public async Task<IActionResult> Details(int? id)
        {
            if (id == null) return NotFound();
            var usuarioId = ObtenerUsuarioIdActual();
            var gasto = await _registroGastoService.ObtenerGastoPorIdAsync(id.Value, usuarioId);
            if (gasto == null) return NotFound();
            return View(gasto);
        }



        public async Task<IActionResult> Create()
        {
            await CargarViewBagsParaFormulario();
            var model = new RegistroGastoInput();
            model.Detalles.Add(new RegistroGastoDetalleInput());
            return View(model);
        }


        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create(RegistroGastoInput model)
        {
            var usuarioId = ObtenerUsuarioIdActual();


            model.Detalles.RemoveAll(d => d.TipoGastoId == 0 || d.Monto <= 0);

            if (!model.Detalles.Any())
            {
                ModelState.AddModelError("Detalles", "Debe agregar al menos un detalle de gasto válido.");
            }


            if (ModelState.IsValid)
            {
                try
                {
                    var (gastoCreado, alertas) = await _registroGastoService.RegistrarGastoAsync(usuarioId, model);

                    if (alertas.Any())
                    {
                        TempData["WarningMessage"] = "Gasto registrado, pero con las siguientes alertas de sobregiro:";



                        foreach (var alerta in alertas)
                        {
                            TempData["WarningMessage"] += $"<br/>- {alerta.TipoGastoNombre}: Presupuesto ${alerta.MontoPresupuestado}, Gastado ${alerta.TotalGastadoEnMes + alerta.MontoGastoActual} (Sobregiro de ${alerta.MontoSobregirado})";
                        }
                    }
                    else
                    {
                        TempData["SuccessMessage"] = "Gasto registrado exitosamente.";
                    }
                    return RedirectToAction(nameof(Index));
                }
                catch (ArgumentException ex) { ModelState.AddModelError(string.Empty, ex.Message); }
                catch (KeyNotFoundException ex) { ModelState.AddModelError(string.Empty, ex.Message); }
                catch (Exception ex)
                {

                    ModelState.AddModelError(string.Empty, "Ocurrió un error inesperado al registrar el gasto. " + ex.Message);
                }
            }

            await CargarViewBagsParaFormulario(model.FondoMonetarioId, model.TipoDocumento);

            if (!model.Detalles.Any())
            {
                model.Detalles.Add(new RegistroGastoDetalleInput());
            }
            return View(model);
        }


        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null) return NotFound();
            var usuarioId = ObtenerUsuarioIdActual();
            var gasto = await _registroGastoService.ObtenerGastoPorIdAsync(id.Value, usuarioId);
            if (gasto == null) return NotFound();
            return View(gasto);
        }


        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var usuarioId = ObtenerUsuarioIdActual();
            try
            {
                await _registroGastoService.EliminarGastoAsync(id, usuarioId);
                TempData["SuccessMessage"] = "Gasto eliminado exitosamente.";
            }
            catch (KeyNotFoundException)
            {
                TempData["ErrorMessage"] = "Gasto no encontrado o no tiene permisos para eliminarlo.";
            }
            catch (Exception)
            {
                TempData["ErrorMessage"] = "Ocurrió un error al eliminar el gasto.";
            }
            return RedirectToAction(nameof(Index));
        }
    }
}
