package control.gestioneFilm;

import model.film.Film;
import model.film.FilmDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet che gestisce la corretta visualizzazione della pagina dei risultati della ricerca inserendo i film che contengono una determinata parola nel titolo all'interno della request
 */

@WebServlet(name = "RicercaServlet", urlPatterns = "/Ricerca")
public class RicercaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FilmDAO filmDao = new FilmDAO();
        request.setCharacterEncoding("UTF-8");
        String inputRicerca = request.getParameter("inputRicerca");
        List<Film> films = filmDao.doRetrieveByWord(inputRicerca);
        request.setAttribute("inputRicerca", inputRicerca);
        request.setAttribute("films", films);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/ricerca.jsp");
        requestDispatcher.forward(request, response);
    }
}