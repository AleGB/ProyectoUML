/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpeta.servlets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Ale
 */
public class EliminarDiagramaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String ruta = request.getRealPath("/");
        String nomArchivo = (String) request.getParameter("celda");
        int numArchivo = Integer.parseInt(nomArchivo);
        File archivo = new File(ruta + nomArchivo + ".xml");
        boolean borrado = archivo.delete();
        try {
            if (borrado) {
                SAXBuilder builder = new SAXBuilder();
                File xmlFile = new File(ruta + "PackageDiagramsXML.xml");
                Document doc;
                doc = builder.build(xmlFile);
                Element root = doc.getRootElement();
                List listaDiagramas = root.getChildren("diagrama");
                for (int i = 0; i < listaDiagramas.size(); i++) {
                    Element node = (Element) listaDiagramas.get(i);
                    if (node.getAttribute("idDiagrama").equals(numArchivo)) {
                        // borrar elemento
                        node.getParentElement().removeChild(ruta);
                    }
                }
                XMLOutputter xmlOutput = new XMLOutputter();
                xmlOutput.setFormat(Format.getPrettyFormat());
                FileWriter writer = new FileWriter(ruta + "PackageDiagramsXML.xml");
                xmlOutput.output(doc, writer);
                writer.flush();
                writer.close();
                System.out.println("El fichero ha sido borrado satisfactoriamente");
                response.sendRedirect("DPServlet");

            } else {
                out.println("holi");
            }
        } catch (JDOMException ex) {
            out.println(ex.getMessage());
        }
    }
}
