/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpeta.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author Ale
 */
public class PackageDiagramServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String nombre = (String) session.getAttribute("usuario");
        String nomArchivo= (String) request.getParameter("celda");
        boolean permiso = Boolean.parseBoolean(request.getParameter("permiso"));

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Editor Diagrama de Paquetes</title>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<link rel='stylesheet' type='text/css' href='css/estilos.css'>");
        out.println("<link type='text/css' rel='stylesheet' href='jsuml2/build/css/UDStyle.css' media='screen'/>");
        out.println("<script type='text/javascript' src='jsuml2/build/UDCore.js'></script>");
        out.println("<script type='text/javascript' src='jsuml2/build//UDModules.js'></script>");
        out.println("<script src='https://kit.fontawesome.com/d09e085a5a.js' crossorigin='anonymous'></script>");
        out.println("</head>");
        out.println("<body onload='load(" + permiso + ", "+nomArchivo+")'");
        out.println("<div class='out'>");
        out.println("<a href='LogoutServlet'>SING OUT</a>");
        out.println("</div>");
        out.println("<div class='usuario'>");
        out.println("<label>BIENVENIDO: " + nomArchivo + "</label>");
        out.println("</div>");
        out.println("<div class='tituloD'>");
        out.println("<h1 id='temporal'>CREACIÓN DE DIAGRAMAS DE PAQUETES (PACKAGE)</h1>");
        out.println("</div>");
        out.println("<div class='menuDPackage'>");
        out.println("<label>ELEMENTOS UML</label>");
        out.println("<button class='button' id='btnPackage'>Package<br><img src='imagenes/package.png' alt='x'/></button>");
        out.println("<button class='button' id='btnPackageContainer'>Package Container<br><img src='imagenes/packageContainer.png' alt='x'/></button>");
        out.println("<button class='button' id='btnDependecy'>Dependecy<br><img src='imagenes/dependecy.png' alt='x'/></button>");
        out.println("<button class='button' id='btnMerge'>Package Merge<br><img src='imagenes/merge.png' alt='x'/></button>");
        out.println("<button class='button' id='btnPublicImport'>Package Public Import<br><img src='imagenes/import.png' alt='x'/></button>");
        out.println("<button class='button' id='btnPrivateImport'>Package Private Import<br><img src='imagenes/acces.png' alt='x'/></button>");
        out.println("<button class='button' id='btnNote'>Note<br><img src='imagenes/note.png' alt='x'/></button>");
        out.println("<button class='button' id='btnLine'>Line<br><img src='imagenes/line.png' alt='x'/></button>");
        out.println("<label>ACCIONES</label>");
        out.println("<button class='button' id='btnSave'><i class='fas fa-save'></i>Guardar</button>");
        out.println("<button class='button'><i class='far fa-comment-alt'></i>Comentario</button>");
        out.println("<button class='button' id='btnBorrar'><i class='fas fa-backspace'></i>Borrar</button>");
        out.println("<form action='DPServlet'>");
        out.println("<button type='submit' class='button' id='btnRegresar'><i class='fas fa-arrow-alt-circle-left'></i>Regresar</button>");
        out.println("</form>");
        out.println("</div>");
        out.println("<div class='contenedorDiagrama'>");
        out.println("<div class='limpia'>");
        out.println("<div class='titDiagrama'>");
        out.println("<ul>");
        out.println("<li id='nameDiagram'></li>");
        out.println("</ul>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div id='usePackageDiagram' class='limpia'>");
        out.println("</div>");
        out.println("</div>");
        out.println("<script type='text/javascript' src='js/funciones.js'></script>");
        out.println("</body>");
        out.println("</html>");
    }
}
