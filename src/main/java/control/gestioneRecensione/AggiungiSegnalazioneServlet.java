package control.gestioneRecensione;

import control.MyServletException;
import model.recensione.RecensioneDAO;
import model.utente.Utente;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet che permette a un utente di di segnalare la recensione di un altro utente
 */
@WebServlet(name = "AggiungiSegnalazioneServlet", urlPatterns = "/Segnala")
public class AggiungiSegnalazioneServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws IOException
     * @throws MyServletException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, MyServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null || utente.isNotActive()) {
            throw new MyServletException("Utente non autorizzato");
        }
        int idRecensione = Integer.parseInt(request.getParameter("idRecensione"));
        int idFilm = Integer.parseInt(request.getParameter("idFilm"));
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        recensioneDAO.doUpdateSegnalazioneTrue(idRecensione);
        response.sendRedirect("./Film?id=" + idFilm);
    }
}