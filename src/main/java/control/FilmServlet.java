package control;

import model.film.Film;
import model.film.FilmDAO;
import model.recensione.Recensione;
import model.recensione.RecensioneDAO;
import model.segnalazione.Segnalazione;
import model.segnalazione.SegnalazioneDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet che gestisce la corretta visaulizzazione della pagina di un film inserendo il film nella request
 */

@WebServlet(name = "FilmServlet", urlPatterns = "/Film")
public class FilmServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FilmDAO filmDAO = new FilmDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        Film film = filmDAO.doRetrieveById(id);
        if (film == null) {
            throw new MyServletException("Film non trovato");
        }
        RecensioneDAO serviceRecensione = new RecensioneDAO();
        ArrayList<Recensione> recensioni = serviceRecensione.doRetrieveByIdFilm(id);
        SegnalazioneDAO serviceSegnalazione = new SegnalazioneDAO();
        ArrayList<Segnalazione> segnalazioniUtente = new ArrayList<>();



        request.setAttribute("film", film);
        request.setAttribute("recensioni", recensioni);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/film.jsp");
        requestDispatcher.forward(request, response);
    }
}