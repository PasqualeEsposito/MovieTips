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
 * Servlet che permette a un filmino di aggiungere la recensione relativa a un film all’interno del database
 */
@WebServlet(name = "AggiungiValutazioneServlet", urlPatterns = "/AggiungiValutazione")
public class AggiungiValutazioneServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws IOException
     * @throws MyServletException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, MyServletException {
        request.setCharacterEncoding("UTF-8");
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null || !utente.isFilmino()) {
            throw new MyServletException("Utente non autorizzato");
        }
        int valutazione = Integer.parseInt(request.getParameter("valutazione"));
        String testo = request.getParameter("testo");
        int idFilm = Integer.parseInt(request.getParameter("idFilm"));
        if (valutazione < 1 || valutazione > 5) {
            request.setAttribute("errorTest", "RV1_FAIL");
        } else {
            if (testo.length() > 255) {
                request.setAttribute("errorTest", "LT_FAIL");
            } else {
                RecensioneDAO recensioneDAO = new RecensioneDAO();
                recensioneDAO.doSave(valutazione, testo, utente.getUsername(), idFilm);
                request.setAttribute("errorTest", "OK");
                response.sendRedirect("./Film?id=" + idFilm);
                return;
            }
        }
        throw new MyServletException("Valutazione non valida");
    }
}