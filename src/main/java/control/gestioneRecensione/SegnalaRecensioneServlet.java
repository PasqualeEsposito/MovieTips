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
 * Servlet che permette a un utente di di segnalare la recensione di un altro utente
 */
@WebServlet(name = "SegnalaRecensioneServlet", urlPatterns = "/Segnala")
public class SegnalaRecensioneServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws IOException
     * @throws MyServletException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, MyServletException {
        int idRecensione, idFilm;
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        try {
            idRecensione = Integer.parseInt(request.getParameter("idRecensione"));
            idFilm = Integer.parseInt(request.getParameter("idFilm"));
        } catch (NumberFormatException e) {
            throw new MyServletException("Dati non validi");
        }
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        if (recensioneDAO.reportReview(idRecensione, utente) == -1) {
            throw new MyServletException("Utente non autorizzato");
        }
        response.sendRedirect("./Film?id=" + idFilm);
    }
}