/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.movimientos;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author farid
 */
@WebServlet(name = "MovimientosList", urlPatterns = {"/MovimientosList"})
public class MovimientosList extends HttpServlet {

    //@EJB
    private DataAccessObjet.Movimientos movimiento = new DataAccessObjet.Movimientos();

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
            String table = """
                           <table class="table align-items-center mb-0">
                            <thead>
                              <tr>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 text-center">Fecha</th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 text-center">Naturaleza</th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 text-center">Cuenta</th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 text-center">Categoria</th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 text-center">Tercero</th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 text-center">Tag</th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 text-center">Valor</th>
                                <th class="text-secondary opacity-7"></th>
                              </tr>
                            </thead>
                           <tbody>""";
            
            String alerts = "";
            String attributes = (String) request.getAttribute("success");
            if (attributes != null) {
                alerts = "<div class=\"alert text-center alert-success\" role=\"alert\">" + attributes + "</div>";
                request.removeAttribute("success");
            }
                
            attributes = (String) request.getAttribute("danger");
            if (attributes != null) {
                alerts += "<div class=\"alert text-center alert-danger\" role=\"alert\">" + attributes + "</div>";
                request.removeAttribute("danger");
            }
            
            List<Entities.Movimiento> elements = movimiento.getAll();
            for(Entities.Movimiento element: elements) {

                table += "<tr>"+
"                      <td><h6 class=\"mb-0 text-sm\">"+new SimpleDateFormat("dd/MM/yyyy").format(element.getFecha())+"</h6></td>\n"+
"                      <td><h6 class=\"mb-0 text-sm\">"+element.getNaturaleza()+"</h6></td>\n"+
"                      <td><h6 class=\"mb-0 text-sm\">"+element.getCuenta().getNombre()+"</h6></td>\n"+
"                      <td><h6 class=\"mb-0 text-sm\">"+(element.getCatergoriaId()==0?"":element.getCatergoria().getNombre())+"</h6></td>\n"+
"                      <td><h6 class=\"mb-0 text-sm\">"+(element.getTerceroId()==0?"":element.getTercero().getNumeroIdentificacion()+ " - " +element.getTercero().getNombre())+"</h6></td>\n"+
"                      <td><h6 class=\"mb-0 text-sm\">"+(element.getTagId()==0?"":element.getTag().getNombre())+"</h6></td>\n"+
"                      <td><h6 class=\"mb-0 text-sm text-end\">"+NumberFormat.getCurrencyInstance().format(element.getValor())+"</h6></td>\n"+
"                      <td class=\"align-middle text-center\">\n" +
"                       <a href=\"MovimientosUpdate?transaccion="+element.getNaturaleza().toLowerCase()+"&element="+element.getId()+"\" class=\"btn btn-info\" data-toggle=\"tooltip\" data-original-title=\"edit\"><i class=\"material-icons opacity-10\">edit</i></a>\n"+
"                       <a href=\"MovimientosDelete?element="+element.getId()+"\" class=\"btn btn-danger\" data-toggle=\"tooltip\" data-original-title=\"edit\"><i class=\"material-icons opacity-10\">delete</i></a>\n"+
"                       </td>" +
"                    </tr>";
            }
            
            
            
            if (elements.isEmpty())
                table += "<tr><td colspan=\"4\" class=\"text-center\"> No se encontraron registros </td></tr>";
            table += """
                                       </tbody>
                                     </table>""";
            /* TODO output your page here. You may use following sample code. */
            out.println(helpers.Templates.ListElements("Movimientos", table, alerts, "MovimientosCreate?transaccion=ingreso"));
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
        processRequest(request, response);
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
