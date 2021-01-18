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

@WebServlet(name = "FilmServlet", urlPatterns = "/Film")
public class FilmServlet extends HttpServlet {
    /**
     * La servlet prende in input l'id da visualizzare, e
     */

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

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
        request.setAttribute("film", film);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/film.jsp");
        requestDispatcher.forward(request, response);
    }
}
