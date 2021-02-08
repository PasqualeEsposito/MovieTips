package control.gestioneFilm;

import control.MyServletException;
import model.gestioneFilm.Film;
import model.gestioneFilm.FilmDAO;
import model.gestioneRecensione.Recensione;
import model.gestioneRecensione.RecensioneDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet che gestisce la corretta visualizzazione della pagina di un film
 */
@WebServlet(name = "FilmServlet", urlPatterns = "/Film")
public class FilmServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            throw new MyServletException("Siamo spiacenti, la pagina richiesta non è stata trovata");
        }
        FilmDAO filmDAO = new FilmDAO();
        Film film = filmDAO.getFilm(id);
        /*if (film == null)
            request.setAttribute("errorTest", "Errore: film non presente nel database");
        else
            request.setAttribute("errorTest", "Ok: film presente nel database");*/
        if (film == null) {
            throw new MyServletException("Siamo spiacenti, la pagina richiesta non è stata trovata");
        }
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        List<Recensione> recensioni = recensioneDAO.getReviewsByFilm(id);
        request.setAttribute("film", film);
        request.setAttribute("recensioni", recensioni);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/film.jsp");
        requestDispatcher.forward(request, response);
    }
}