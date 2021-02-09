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
        String errore = "";
        if (film == null) {
            errore = "Errore: film non esistente";
            request.setAttribute("errorTest", errore);
        } else {
            errore = "Ok: pagina film visualizzata";
            request.setAttribute("errorTest", errore);
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            List<Recensione> recensioni = recensioneDAO.getReviewsByFilm(id);
            request.setAttribute("film", film);
            request.setAttribute("recensioni", recensioni);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/film.jsp");
            requestDispatcher.forward(request, response);
            return;
        }
        throw new MyServletException(errore);
    }
}