package control.gestioneRecensione;

import model.recensione.RecensioneDAO;

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
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException { // Inserire controlli utente
        int idRecensione = Integer.parseInt(request.getParameter("idRecensione"));
        int idFilm = Integer.parseInt(request.getParameter("idFilm"));
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        recensioneDAO.doUpdateSegnalazioneTrue(idRecensione);
        response.sendRedirect("./Film?id=" + idFilm);
    }
}