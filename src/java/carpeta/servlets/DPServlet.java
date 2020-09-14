/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpeta.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

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
        String ruta = request.getRealPath("/");
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(ruta + "PackageDiagramsXML.xml");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
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
        out.println("<label>BIENVENIDO: " + nombre + "</label>");
        out.println("</div>");
        out.println("<div class='tituloD'>");
        out.println("<h1>CREACIÓN DE DIAGRAMAS DE PAQUETES (PACKAGE)</h1>");
        out.println("</div>");
        out.println("<div class='btnNuevo'>");
        out.println(" <a href='PackageDiagramServlet'>Crear nuevo diagrama de paquetes</a>");
        out.println("</div>");
        out.println("<div>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Nombre del Diagrama de Paquetes</th>");
        out.println("<th>Acciones</th>");
        out.println("</tr>");
        Document document;
        try {
            document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("diagrama");
            for (int i = 0; i < 1; i++) {
                Element node = (Element) list.get(i);
                out.println("<tr>");
                out.println("<td  id='elemento" + i + "'>");
                out.println(node.getChildText("id"));
                out.println("</td>");
                out.println("<td>");
                out.println(node.getChildText("nombre"));
                out.println("</td>");
                out.println("<td>");
                out.println("<a href='PackageDiagramServlet'>Ver Diagrama</a>");
                out.println("|");
                out.println("<a onclick='modificar("+ i +")' href='PackageDiagramServlet'>Modificar Diagrama</a>");
                out.println("|");
                out.println("<a href=''>Eliminar Diagrama</a>");
                out.println("|");
                out.println("<a href=''>Copiar Diagrama</a>");
                out.println("</td>");
                out.println("</tr>");
            }

        } catch (JDOMException ex) {
            ex.printStackTrace();
        }

        out.println("</table>");
        out.println("</div>");
        out.println("<script type='text/javascript' src='js/AccionesTabla.js'></script>");
        out.println("<script type='text/javascript' src='js/funciones.js'></script>");
        out.println("</body>");
        out.println("</html>");
    }
}
