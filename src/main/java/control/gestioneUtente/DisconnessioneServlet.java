package control.gestioneUtente;

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
 * Servlet che gestisce il logout rimuovendo l'utente dalla sessione
 */

@WebServlet(name = "DisconnessioneServlet", urlPatterns = "/Disconnessione")
public class DisconnessioneServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().removeAttribute("utente");
        FilmDAO filmDAO = new FilmDAO();
        List<Film> films = filmDAO.doRetrieveLastTen();
        request.setAttribute("films", films);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/homePage.jsp");
        requestDispatcher.forward(request, response);
    }
}