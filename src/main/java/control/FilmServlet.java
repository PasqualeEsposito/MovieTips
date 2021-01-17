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

@WebServlet(name = "FilmServlet",urlPatterns = "/film")
public class FilmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            FilmDAO filmDAO=new FilmDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            Film film = filmDAO.doRetrieveById(id);
            if (film == null) {
               throw new MyServletException("Film non trovato");//lanciare eccezione per la servlet
            }
            request.setAttribute("film", film);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/film.jsp");
            requestDispatcher.forward(request, response);
    }
}
