package carpeta.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author Ale
 */
public class LoginServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        String usuario, pass;
        usuario = request.getParameter("IDuser");
        pass = request.getParameter("pass");
        String ruta = request.getRealPath("/xmls/");
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(ruta + "usuarios.xml");
        PrintWriter out = response.getWriter();
        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("usuario");
            boolean bandera = false;

            for (int i = 0; i < list.size(); i++) {
                Element node = (Element) list.get(i);
                if (usuario.equals(node.getChildText("id")) && pass.equals(node.getChildText("password")) && session.getAttribute("usuario") == null) {
                    session.setAttribute("usuario", usuario);
                    response.sendRedirect("menuUsuario.html");
                    bandera = true;
                    break;
                }
            }
            if (bandera == false) {
                out.print("ID o PASSWORD INCORRECTOS");
                request.getRequestDispatcher("index.html").include(request, response);
            }
            out.close();
        } catch (IOException io) {
            out.println(io.getMessage());

        } catch (JDOMException jdomex) {
            out.println(jdomex.getMessage());
        }
    }
}
