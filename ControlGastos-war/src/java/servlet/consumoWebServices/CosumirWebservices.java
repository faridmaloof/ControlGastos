/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.consumoWebServices;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import readers.WeatherForecast;

/**
 *
 * @author farid
 */
@WebServlet(name = "CosumirWebservices", urlPatterns = {"/CosumirWebservices"})
public class CosumirWebservices extends HttpServlet {

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
            Client client = ClientBuilder.newClient(); //creamos un nuevo cliente...
            client.register(WeatherForecast.class); //... registramos nuestro interprete JSON -> Bean
            WebTarget target = client.target("http://localhost:5142/WeatherForecast");
            //LOGGER.info("Solicitamos al Servicio REST el listado");
            try {
                List<Entities.WeatherForecast> elements = target.request().get(List.class);
                            String table = """
                           <table class="table align-items-center mb-0">
                            <thead>
                              <tr>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 text-center">Fecha</th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 text-center">Temperatura °C</th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 text-center ">Temperatura °F</th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 text-center ">Resumen</th>
                                <th class="text-secondary opacity-7"></th>
                              </tr>
                            </thead>
                           <tbody>""";
            
                table = elements.stream().map(element -> "<tr>\n"+
                        "<td><h6 class=\"mb-0 text-sm text-center\">"+new SimpleDateFormat("dd-MM-yyyy").format(element.getDate())+"</h6></td>\n" +
                        "<td><h6 class=\"mb-0 text-sm text-center\">"+element.getTemperatureC()+"</h6></td>\n" +
                        "<td><h6 class=\"mb-0 text-sm text-center\">"+element.getTemperatureF()+"</h6></td>\n" +
                        "<td><h6 class=\"mb-0 text-sm\">"+element.getSummary()+"</h6></td>\n" +
                        "</tr>").reduce(table, String::concat);

                if (elements.isEmpty())
                    table += "<tr><td colspan=\"4\" class=\"text-center\"> No se encontraron registros </td></tr>";
                table += """
                                           </tbody>
                                         </table>""";

                out.println(helpers.Templates.ListElements("Consumo Webservices", table));
            } catch(Exception ex) {
                String result = "<h1 class=\"text-center\">Se intento la conexion con el servicio</h1><h2 class=\"text-center\">fallando esta.</h2><h3 class=\"text-center\">Favor verifique el el servicio se encuentre desplegado.</h3>";
                out.println(helpers.Templates.ListElements("Consumo Webservices", result));
            }

            /*
            // Esto es lo que vamos a devolver
            StringBuilder resultado = new StringBuilder();
            // Crear un objeto de tipo URL
            URL url = new URL("http://localhost:5142/WeatherForecast");

            // Abrir la conexión e indicar que será de tipo GET
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            // Búferes para leer
            BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            
            String linea;
            int totalCiclos=0;
            // Mientras el BufferedReader se pueda leer, agregar contenido a resultado
            while ((linea = rd.readLine()) != null) {
                
                WeatherForecast_Get result = new ObjectMapper().readValue(linea, WeatherForecast_Get.class);
                totalCiclos=1;
                resultado.append(linea);
            }
            // Cerrar el BufferedReader
            rd.close();
            // Regresar resultado, pero como cadena, no como StringBuilder
            out.println(resultado.toString()+"\n totalCiclos: "+totalCiclos);*/
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
