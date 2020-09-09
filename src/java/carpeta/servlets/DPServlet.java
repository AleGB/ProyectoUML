/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpeta.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ale
 */
public class DPServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String nombre = (String) session.getAttribute("usuario");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Diagrama Paquetes</title>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<link rel='stylesheet' type='text/css' href='css/estilos.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='out'>");
            out.println("<a href='LogoutServlet'>SING OUT</a>");
            out.println("</div>");
            out.println("<div class='usuario'>");
            out.println("<label>Bienvenido: " + nombre + "</label>");
            out.println("</div>");
            out.println("<div class='tituloD'>");
            out.println("<h1>CREACIÓN DE DIAGRAMAS DE PAQUETES (PACKAGE)</h1>");
            out.println("<div class='btnNuevo'>");
            out.println("<input type='submit' value='Crear nuevo Diagrama de Paquetes'>");
            out.println("</div>");
            out.println("<div>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Nombre del Diagrama de Paquetes</th>");
            out.println("<th>Acciones</th>");
            out.println("</tr>");
            for (int i = 0; i < 1; i++) {
                out.println("<tr>");
                out.println("<td>Ejemplo1</td>");
                out.println("<td>");
                out.println("<a href=''>Ver Diagrama</a>");
                out.println("|");
                out.println("<a href=''>Modificar Diagrama</a>");
                out.println("|");
                out.println("<a href=''>Eliminar Diagrama</a>");
                out.println("|");
                out.println("<a href=''>Copiar Diagrama</a>");
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
