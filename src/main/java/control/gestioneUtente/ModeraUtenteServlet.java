package control.gestioneUtente;

import control.MyServletException;
import model.utente.Utente;
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
     * @throws MyServletException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, MyServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null || !utente.isModeratore()) {
            throw new MyServletException("Utente non autorizzato");
        }
        String username =  request.getParameter("username");
        if (username.equals(utente.getUsername())) {
            throw new MyServletException("Operazione non autorizzata");
        }
        UtenteDAO utenteDAO = new UtenteDAO();
        utenteDAO.doUpdateUtente(username, "100000");
        response.sendRedirect(".");
    }
}
