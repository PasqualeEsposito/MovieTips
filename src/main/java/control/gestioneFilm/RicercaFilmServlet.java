package control.gestioneFilm;

import control.MyServletException;
import model.gestioneFilm.Film;
import model.gestioneFilm.FilmDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet che gestisce la corretta visualizzazione della pagina dei risultati della ricerca
 */
@WebServlet(name = "RicercaFilmServlet", urlPatterns = "/Ricerca")
public class RicercaFilmServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        FilmDAO filmDAO = new FilmDAO();
        String inputRicerca = request.getParameter("inputRicerca");
        List<Film> films = filmDAO.searchFilms(inputRicerca);
        String errore = "";
        if (films == null) {
            errore = "Errore: lunghezza ricerca";
            request.setAttribute("errorTest", errore);
        } else {
            errore = "Ok: ricerca effettuata";
            request.setAttribute("errorTest", errore);
            request.setAttribute("inputRicerca", inputRicerca);
            request.setAttribute("films", films);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/ricerca.jsp");
            requestDispatcher.forward(request, response);
            return;
        }
        throw new MyServletException(errore);
    }
}