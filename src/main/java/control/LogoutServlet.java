package control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet che gestisce il logout rimuovendo l'utente dalla sessione
 */

@WebServlet(name = "LogoutServlet", urlPatterns = "/Logout")
public class LogoutServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("utente");
        response.sendRedirect(".");
    }
}