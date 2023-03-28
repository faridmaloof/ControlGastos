/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.categorias;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author farid
 */
@WebServlet(name = "CategoriasList", urlPatterns = {"/CategoriasList"})
public class CategoriasList extends HttpServlet {

    //@EJB
    private final DataAccessObjet.Categorias categoria = new DataAccessObjet.Categorias();

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
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 text-center">Nombre</th>
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
            
            List<Entities.Categoria> elements = categoria.getAll();
            for(Entities.Categoria element: elements) {
                String esActivo;
                if (element.getActivo()) {
                    esActivo = "<a href=\"CategoriasUpdate?element="+element.getId()+"\" class=\"btn btn-info\" data-toggle=\"tooltip\" data-original-title=\"edit\">\n" +
"                                                              <i class=\"material-icons opacity-10\">edit</i>\n" +
"                                                            </a>\n" +
"                                                            <a href=\"CategoriasInactivate?element="+element.getId()+"\" class=\"btn btn-danger\" data-toggle=\"tooltip\" data-original-title=\"disabled\">\n" +
"                                                              <i class=\"material-icons opacity-10\">delete</i>\n" +
"                                                            </a>";
                } else {
                    esActivo = "<a href=\"CategoriasActivate?element="+element.getId()+"\" class=\"btn btn-success\" data-toggle=\"tooltip\" data-original-title=\"enabled\">\n" +
"                                                              <i class=\"material-icons opacity-10\">done_outline</i>\n" +
"                                                            </a>";
                }

                table += """
                                             <tr>
                                               <td>                        
                                                 <h6 class="mb-0 text-sm">"""+element.getNombre()+"</h6>\n" +
"                      </td>\n" +
"                      <td class=\"align-middle  text-center\">\n" +
"                        "+esActivo+"\n" +
"                      </td>\n" +
"                    </tr>";
            }
            
            
            
            if (elements.isEmpty())
                table += "<tr><td colspan=\"4\" class=\"text-center\"> No se encontraron registros </td></tr>";
            table += """
                                       </tbody>
                                     </table>""";
            /* TODO output your page here. You may use following sample code. */
            out.println(helpers.Templates.ListElements("Categorias", table, alerts, "CategoriasCreate"));
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
