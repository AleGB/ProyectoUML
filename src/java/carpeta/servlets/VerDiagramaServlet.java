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

/**
 *
 * @author Ale
 */
public class VerDiagramaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String nomArchivo = (String) request.getParameter("celda");
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Visor de Diagramas</title>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<link rel='stylesheet' type='text/css' href='css/estilos.css'>");
        out.println("<link type='text/css' rel='stylesheet' href='jsuml2/build/css/UDStyle.css' media='screen'/>");
        out.println("<script type='text/javascript' src='jsuml2/build/UDCore.js'></script>");
        out.println("<script type='text/javascript' src='jsuml2/build//UDModules.js'></script>");
        out.println("</head>");
        out.println("<body onload='cargarDiagrama(" + nomArchivo + ")'>");
        out.println("<div id='diagramaV'>");
        out.println("</div>");
        out.println("<script type='text/javascript' src='js/AccionesTabla.js'></script>");
        out.println("</body>");
        out.println("</html>");
    }
}
