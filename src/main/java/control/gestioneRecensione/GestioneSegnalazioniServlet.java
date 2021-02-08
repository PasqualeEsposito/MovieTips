package control.gestioneRecensione;

import control.MyServletException;
import model.gestioneRecensione.Recensione;
import model.gestioneRecensione.RecensioneDAO;
import model.gestioneUtente.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        List<Recensione> recensioni = recensioneDAO.doRetrieveBySegnalazione(utente);
        if (recensioni == null) {
            throw new MyServletException("Utente non autorizzato");
        }
        request.setAttribute("recensioni", recensioni);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/segnalazioni.jsp");
        requestDispatcher.forward(request, response);
    }
}