package control.gestioneUtente;

import control.MyServletException;
import model.gestioneUtente.Utente;
import model.gestioneUtente.UtenteDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, MyServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        UtenteDAO utenteDAO = new UtenteDAO();
        String username = request.getParameter("username");
        String errore;
        switch (utenteDAO.banUser(utente, username)) {
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
                response.sendRedirect(".");
                return;
        }
        //throw new MyServletException(errore);
    }
}