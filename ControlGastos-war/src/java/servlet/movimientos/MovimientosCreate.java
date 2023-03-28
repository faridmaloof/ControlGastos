/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.movimientos;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author farid
 */
@WebServlet(name = "MovimientosCreate", urlPatterns = {"/MovimientosCreate"})
public class MovimientosCreate extends HttpServlet {

    //@EJB
    private final DataAccessObjet.Movimientos movimiento = new DataAccessObjet.Movimientos();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String formulario = "<ul class=\"nav justify-content-center\">\n" +
"  <li class=\"nav-item\">\n" +
"    <a class=\"nav-link"+("ingreso".equals(request.getParameter("transaccion"))? " disabled":"")+"\" href=\"MovimientosCreate?transaccion=ingreso\">Ingreso</a>\n" +
"  </li>\n" +
"  <li class=\"nav-item\">\n" +
"    <a class=\"nav-link"+("ingreso".equals(request.getParameter("transaccion"))? "":" disabled")+"\" href=\"MovimientosCreate?transaccion=egreso\">Egreso</a>\n" +
"  </li>\n" +
"</ul>"
                    + "<div class=\"input-group input-group-outline mb-3\">\n" +
"                            <input type=\"date\" class=\"form-control\" name=\"fecha\" placeholder=\"Fecha transaccion\" required>\n" +
"                        </div>\n" +
"                        <div class=\"input-group input-group-outline mb-3\">\n" + DataAccessObjet.Cuentas.List() + "</div>\n" +
"                        <div class=\"input-group input-group-outline mb-3\">\n" + DataAccessObjet.Terceros.List() + "</div>\n" +
"                        <div class=\"input-group input-group-outline mb-3\">\n" + DataAccessObjet.Categorias.List() + "</div>\n" +
"                        <div class=\"input-group input-group-outline mb-3\">\n" + DataAccessObjet.Tags.List() + "</div>\n" +
"                        <div class=\"input-group input-group-outline mb-3\">\n" +
"                            <label class=\"form-label\">Valor</label>\n" +
"                            <input type=\"number\" min=\"0\" pattern=\"^[0-9]+\" step=\"0.01\" class=\"form-control\" name=\"valor\" required>\n" +
"                        </div>";
            out.println(helpers.Templates.Form("Movimientos", formulario, "MovimientosList"));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processCreate(request, response);
    }
    
    protected void processCreate(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /*try (PrintWriter out = response.getWriter()) {            
            String resultado = "Naturaleza: " + ("Ingreso".equals(request.getParameter("transaccion"))? "Ingreso":"Egreso") + "\n"
                    + "Fecha: " + new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fecha")).toString() + "\n"
                    + "CuentaId: " + request.getParameter("cuenta") + "\n"
                    + "TerceroId: " + request.getParameter("tercero") + "\n"
                    + "CatergoriaId: " + request.getParameter("categoria") + "\n"
                    + "TagId: " + request.getParameter("tag") + "\n"
                    + "Valor: " + Double.valueOf(request.getParameter("valor"));
            out.println(resultado);
        } catch (ParseException ex) {
            Logger.getLogger(MovimientosCreate.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        try {        
            Entities.Movimiento element = new Entities.Movimiento();
            element.setNaturaleza(("ingreso".equals(request.getParameter("transaccion"))? "Ingreso":"Egreso"));
            element.setFecha(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fecha")));
            element.setCuentaId(request.getParameter("cuenta") == null? 0: Integer.valueOf(request.getParameter("cuenta")));
            element.setTerceroId(request.getParameter("tercero") == null? 0: Integer.valueOf(request.getParameter("tercero")));
            element.setCatergoriaId(request.getParameter("categoria") == null? 0: Integer.valueOf(request.getParameter("categoria")));
            element.setTagId(request.getParameter("tag") == null? 0: Integer.valueOf(request.getParameter("tag")));
            element.setValor(Double.parseDouble(request.getParameter("valor")));
        
            if (movimiento.add(element))
                request.setAttribute("success", "El registro se ha activado con exito.");
            else
                request.setAttribute("danger", "El registro no pudo ser activado.");

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MovimientosList");
            dispatcher.forward(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(MovimientosCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
