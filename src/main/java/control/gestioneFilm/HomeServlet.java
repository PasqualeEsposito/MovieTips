package control.gestioneFilm;

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
 * Servlet che gestisce la corretta visualizzazione della homepage
 */
@WebServlet(name = "HomeServlet", urlPatterns = "", loadOnStartup = 1)
public class HomeServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FilmDAO filmDAO = new FilmDAO();
        List<Film> films = filmDAO.doRetrieveAll();
        request.setAttribute("films", films);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/homePage.jsp");
        requestDispatcher.forward(request, response);
    }
}