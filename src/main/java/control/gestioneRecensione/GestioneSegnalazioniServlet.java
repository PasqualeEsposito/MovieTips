package control.gestioneRecensione;

import control.MyServletException;
import model.recensione.Recensione;
import model.recensione.RecensioneDAO;
import model.utente.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet che gestisce la corretta visualizzazione della pagina riservata al moderatore inserendo tutte le recensioni segnalate
 */
@WebServlet(name = "GestioneSegnalazioniServlet", urlPatterns = "/GestioneSegnalazioni")
public class GestioneSegnalazioniServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null || !utente.isModeratore()) {
            throw new MyServletException("Utente non autorizzato");
        }
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        ArrayList<Recensione> recensioni = recensioneDAO.doRetrieveBySegnalazione();
        request.setAttribute("recensioni", recensioni);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/segnalazioni.jsp");
        requestDispatcher.forward(request, response);
    }
}