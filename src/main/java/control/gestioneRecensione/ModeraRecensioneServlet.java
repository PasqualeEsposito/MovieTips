package control.gestioneRecensione;

import control.MyServletException;
import model.gestioneRecensione.RecensioneDAO;
import model.gestioneUtente.Utente;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet che permette a un moderatore di eliminare una recensione segnalata
 */
@WebServlet(name = "ModeraRecensioneServlet", urlPatterns = "/ModeraRecensione")
public class ModeraRecensioneServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws IOException
     * @throws MyServletException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, MyServletException {
        int idRecensione, elimina;
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente == null || !utente.isModeratore()) {
            throw new MyServletException("Utente non autorizzato");
        }

        try {
            idRecensione = Integer.parseInt(request.getParameter("idRecensione"));
            elimina = Integer.parseInt(request.getParameter("elimina"));
        } catch (NumberFormatException e) {
            throw new MyServletException("Dati non validi");
        }

        RecensioneDAO recensioneDAO = new RecensioneDAO();
        if (elimina == 1) {
            recensioneDAO.doDeleteByIdRecensione(idRecensione);
        } else if (elimina == 0) {
            recensioneDAO.doUpdateSegnalazioneFalse(idRecensione);
        }
        response.sendRedirect("./GestioneSegnalazioni");
    }
}