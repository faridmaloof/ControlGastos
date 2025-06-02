using System.Security.Claims;
using GastosApp.Application.Dtos;
using GastosApp.Application.Interfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace GastosApp.Web.Controllers;

[Authorize]
public class ConsultaController(IConsultaService consultaService) : Controller
{
    private readonly IConsultaService _consultaService = consultaService;

    private string ObtenerUsuarioIdActual() => User.FindFirstValue(ClaimTypes.NameIdentifier);

    public async Task<IActionResult> Movimientos(DateTime? fechaInicio, DateTime? fechaFin)
    {
        var usuarioId = ObtenerUsuarioIdActual();
        DateTime inicio = fechaInicio ?? DateTime.Today.AddMonths(-1);
        DateTime fin = fechaFin ?? DateTime.Today;

        ViewBag.FechaInicio = inicio.ToString("yyyy-MM-dd");
        ViewBag.FechaFin = fin.ToString("yyyy-MM-dd");

        if (inicio > fin)
            ModelState.AddModelError(string.Empty, "La fecha de inicio no puede ser posterior a la fecha de fin.");

        var movimientos = await _consultaService.ObtenerMovimientosPorRangoFechasAsync(usuarioId, inicio, fin);
        return View(movimientos);
    }

    public async Task<IActionResult> GraficoPresupuesto(DateTime? fechaInicio, DateTime? fechaFin)
    {
        var usuarioId = ObtenerUsuarioIdActual();
        DateTime inicio = fechaInicio ?? new DateTime(DateTime.Today.Year, DateTime.Today.Month, 1);
        DateTime fin = fechaFin ?? new DateTime(DateTime.Today.Year, DateTime.Today.Month, DateTime.DaysInMonth(DateTime.Today.Year, DateTime.Today.Month));

        ViewBag.FechaInicio = inicio.ToString("yyyy-MM-dd");
        ViewBag.FechaFin = fin.ToString("yyyy-MM-dd");

        if (inicio > fin)
            ModelState.AddModelError(string.Empty, "La fecha de inicio no puede ser posterior a la fecha de fin.");

        var datosGrafico = new DatosGraficoPresupuestoDto();
        if (ModelState.IsValid)
            datosGrafico = await _consultaService.ObtenerDatosGraficoPresupuestoAsync(usuarioId, inicio, fin);

        return View(datosGrafico);
    }
}
