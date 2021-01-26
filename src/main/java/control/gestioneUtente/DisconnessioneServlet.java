package control.gestioneUtente;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet che gestisce il logout rimuovendo l'utente dalla sessione
 */

@WebServlet(name = "DisconnessioneServlet", urlPatterns = "/Disconnessione")
public class DisconnessioneServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("utente");
        response.sendRedirect(".");
    }
}