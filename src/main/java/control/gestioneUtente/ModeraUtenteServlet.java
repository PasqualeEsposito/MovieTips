package control.gestioneUtente;

import model.utente.UtenteDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet che permette a un moderatore di bannare un utente
 */
@WebServlet(name = "ModeraUtenteServlet", urlPatterns = "/ModeraUtente")
public class ModeraUtenteServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException { // Inserire controlli utente
        String username = request.getParameter("username");
        UtenteDAO utenteDAO = new UtenteDAO();
        utenteDAO.doUpdateUtente(username, "100000");
        response.sendRedirect(".");
    }
}
