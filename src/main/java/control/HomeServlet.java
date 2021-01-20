package control;

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
 * Servlet che gestisce la corretta visualizzazione della home page inserendo i film nella request
 */
@WebServlet(name = "HomeServlet", urlPatterns = "", loadOnStartup = 1)
public class HomeServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FilmDAO filmDao = new FilmDAO();
        List<Film> films = filmDao.doRetrieveAll(0, 10);
        request.setAttribute("films", films);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/homePage.jsp");
        requestDispatcher.forward(request, response);
    }
}