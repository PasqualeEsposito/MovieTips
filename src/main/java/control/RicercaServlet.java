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
 * La servlet prende in input una stringa da ricercare all'interno del database. Nel caso in cui ci sono
 * film con il nome contenente (o uguale) la stringa da ricercare, ritorna la lista di film che soddisfano
 * la query
 */

@WebServlet(name = "RicercaServlet", urlPatterns = "/Ricerca")
public class RicercaServlet extends HttpServlet {

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FilmDAO filmDao = new FilmDAO();
        String inputRicerca = request.getParameter("inputRicerca");
        List<Film> films = filmDao.doRetrieveByWord(inputRicerca);
        request.setAttribute("inputRicerca", inputRicerca);
        request.setAttribute("films", films);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/risultatiRicerca.jsp");
        requestDispatcher.forward(request, response);
    }
}