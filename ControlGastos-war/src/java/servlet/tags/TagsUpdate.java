/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.tags;

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
@WebServlet(name = "TagsUpdate", urlPatterns = {"/TagsUpdate"})
public class TagsUpdate extends HttpServlet {

    //@EJB
    private DataAccessObjet.Tags dao = new DataAccessObjet.Tags();

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
            Entities.Tag element = dao.getById(Integer.parseInt(request.getParameter("element")));
            String activo="";
            if(element.getActivo())
                activo = " checked";

            String formulario = "<div class=\"input-group input-group-outline mb-3 is-filled\">\n" +
"                            <input type=\"hidden\" class=\"form-control\" name=\"id\" value=\""+element.getId()+"\">\n" +
"                        </div>\n" +
"                        <div class=\"input-group input-group-outline mb-3 is-filled\">\n" +
"                            <label class=\"form-label\">Nombre</label>\n" +
"                            <input type=\"text\" class=\"form-control\" name=\"nombre\" value=\""+element.getNombre()+"\" maxlength=\"50\" required>\n" +
"                        </div>\n" +
"                        <div class=\"form-check form-check-info text-start ps-0 is-filled\">\n" +
"                            <input class=\"form-check-input\" type=\"checkbox\" name=\"activo\""+activo+">\n" +
"                            <label class=\"form-check-label\" for=\"flexCheckDefault\">Activo  </label>\n" +
"                        </div>";
            out.println(helpers.Templates.Form("Tags", formulario, "TagsList"));
        }    }

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
        processUpdate(request, response);
    }
    
    protected void processUpdate(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Entities.Tag element = new Entities.Tag();
        element.setId(Integer.valueOf(request.getParameter("id")));
        element.setNombre(request.getParameter("nombre"));
        element.setActivo((request.getParameter("activo") != null));
        if (dao.update(element))
            request.setAttribute("success", "El registro se ha actualizado con exito.");
        else
            request.setAttribute("danger", "El registro no pudo ser actualizado.");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/TagsList");
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
