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
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String usuario, pass;
            usuario = request.getParameter("IDuser");
            pass = request.getParameter("pass");
            //usuario provisional
            if (usuario.equals("admin") && pass.equals("admin") && session.getAttribute("usuario") == null) {
                //si coincide usuario y password y además no hay sesión iniciada
                session.setAttribute("usuario", usuario);
                //redirijo a página con información de login exitoso
                response.sendRedirect("menuUsuario.html");
            } else {
                //lógica para login inválido
                out.print("ID o PASSWORD INCORRECTOS");
                request.getRequestDispatcher("index.html").include(request, response);
            }
            out.close();
        }
    }
}
