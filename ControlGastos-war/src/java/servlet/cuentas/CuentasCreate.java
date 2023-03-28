/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.cuentas;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author farid
 */
@WebServlet(name = "CuentasCreate", urlPatterns = {"/CuentasCreate"})
public class CuentasCreate extends HttpServlet {

    //@EJB
    private DataAccessObjet.Cuentas dao = new DataAccessObjet.Cuentas();

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
            String formulario = "<div class=\"input-group input-group-outline mb-3\">\n" +
"                            <label class=\"form-label\">Nombre</label>\n" +
"                            <input type=\"text\" class=\"form-control\" name=\"nombre\" maxlength=\"50\" required>\n" +
"                        </div>\n" +
"                        <div class=\"input-group input-group-outline mb-3\">\n" +
"                            <label class=\"form-label\">Saldo</label>\n" +
"                            <input type=\"number\" pattern=\"^[0-9]+\" step=\"0.01\" class=\"form-control\" name=\"saldo\" required>\n" +
"                        </div>\n" +
"                        <div class=\"form-check form-check-info text-start ps-0 is-filled\">\n" +
"                            <input class=\"form-check-input\" type=\"checkbox\" name=\"esVisible\" checked>\n" +
"                            <label class=\"form-check-label\" for=\"flexCheckDefault\">Valor visible en graficos</label>\n" +
"                        </div>\n" +
"                        <div class=\"form-check form-check-info text-start ps-0 is-filled\">\n" +
"                            <input class=\"form-check-input\" type=\"checkbox\" name=\"activo\" checked>\n" +
"                            <label class=\"form-check-label\" for=\"flexCheckDefault\">Activo  </label>\n" +
"                        </div>";
            out.println(helpers.Templates.Form("Cuentas", formulario, "CuentasList"));
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
        Entities.Cuenta element = new Entities.Cuenta();
        element.setNombre(request.getParameter("nombre"));
        element.setEsVisible((request.getParameter("esVisible") != null));
        element.setActivo((request.getParameter("activo") != null));
        element.setSaldo(Double.parseDouble(request.getParameter("saldo")));
        if (dao.add(element))
            request.setAttribute("success", "El registro se inserto con exito.");
        else {
            request.setAttribute("danger", "El registro no pudo ser insertado.");
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CuentasList");
        dispatcher.forward(request, response);
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
