/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpeta.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
public class CopiarDiagramaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String ruta = request.getRealPath("/");
        String nomArchivo = (String) request.getParameter("celda");
        //int numArchivo = Integer.parseInt(nomArchivo);
        String identificador = "";
        for (int i = 0; i < 2; i++) {
            identificador = identificador + (int) (Math.random() * 9) + 1;
        }

        try {
            File origen = new File(ruta + nomArchivo + ".xml");

            File destino = new File(ruta + identificador + ".xml");
            if (!destino.exists()) {
                destino.createNewFile();
                out.println(destino);

            }
            InputStream entrada = new FileInputStream(origen);
            OutputStream salida = new FileOutputStream(destino);
            byte[] buf = new byte[1024];
            int len;

            while ((len = entrada.read(buf)) > 0) {
                salida.write(buf, 0, len);
            }
            entrada.close();
            salida.close();

            SAXBuilder builder = new SAXBuilder();
            File xmlMenu = new File(ruta + "PackageDiagramsXML.xml");
            File Copia = new File(ruta + identificador + ".xml");

            Document docMenu, docCopia;
            docMenu = builder.build(xmlMenu);
            docCopia = builder.build(Copia);

            Element rootMenu = docMenu.getRootElement();
            Element rootCopia = docCopia.getRootElement();

            Element UMLPackageDiagram = rootCopia.getChild("UMLPackageDiagram");
            UMLPackageDiagram.getAttribute("id").setValue(identificador);
            String atributoUML = UMLPackageDiagram.getAttributeValue("name");

            UMLPackageDiagram.getAttribute("name").setValue(atributoUML+"-copia");
            atributoUML = UMLPackageDiagram.getAttributeValue("name");
            
            Element diagrama = new Element("diagrama");
            rootMenu.addContent(diagrama);
            diagrama.setAttribute("idDiagrama", identificador);
            Element id = new Element("id").setText(identificador);
            Element nombre = new Element("nombre").setText(atributoUML);
            diagrama.addContent(id);
            diagrama.addContent(nombre);

            XMLOutputter xmlOutput = new XMLOutputter();
            XMLOutputter xmlOutput2 = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            FileWriter writer = new FileWriter(ruta + "PackageDiagramsXML.xml");
            FileWriter writer2 = new FileWriter(ruta + identificador + ".xml");
            xmlOutput.output(docMenu, writer);
            xmlOutput2.output(docCopia, writer2);
            writer.flush();
            writer2.flush();
            writer.close();
            writer2.close();
            response.sendRedirect("DPServlet");
        } catch (IOException ex) {
            out.println(ex.getMessage());
        } catch (JDOMException ex) {
            out.println(ex.getMessage());
        }
    }
}
