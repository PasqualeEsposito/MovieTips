package control.gestioneUtente;

import control.MyServletException;
import model.gestioneFilm.Film;
import model.gestioneFilm.FilmDAO;
import model.gestioneUtente.Utente;
import model.gestioneUtente.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet che permette a un moderatore di bannare un utente
 */
@WebServlet(name = "BannaUtenteServlet", urlPatterns = "/BannaUtente")
public class BannaUtenteServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws IOException
     * @throws MyServletException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        UtenteDAO utenteDAO = new UtenteDAO();
        String errore = "";
        switch (utenteDAO.banUser(username, utente)) {
            case -1:
                errore = "Errore: accesso non effettuato";
                request.setAttribute("errorTest", errore);
                break;
            case -2:
                errore = "Errore: utente non ricopre il ruolo di moderatore";
                request.setAttribute("errorTest", errore);
                break;
            case -3:
                errore = "Errore: utente non può autoeliminarsi";
                request.setAttribute("errorTest", errore);
                break;
            case -4:
                errore = "Errore: username non esistente";
                request.setAttribute("errorTest", errore);
                break;
            default:
                errore = "Ok: utente bannato";
                request.setAttribute("errorTest", errore);
                FilmDAO filmDAO = new FilmDAO();
                List<Film> films = filmDAO.doRetrieveLastTen();
                request.setAttribute("films", films);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/homePage.jsp");
                requestDispatcher.forward(request, response);
        }
        throw new MyServletException(errore);
    }
}