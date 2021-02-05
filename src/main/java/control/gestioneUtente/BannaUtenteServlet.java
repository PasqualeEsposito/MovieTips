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
        switch (utenteDAO.banUser(utente, username)) {
            case -1:
                request.setAttribute("errorTest", "AE_FAIL");
                break;
                //throw new MyServletException("Utente non autorizzato");
            case -2:
                request.setAttribute("errorTest", "RM_FAIL");
                break;
                //throw new MyServletException("Utente non autorizzato");
            case -3:
                request.setAttribute("errorTest", "CR_FAIL");
                break;
                //throw new MyServletException("Operazione non autorizzata");
            case -4:
                request.setAttribute("errorTest", "EU_FAIL");
                break;
                //throw new MyServletException("Operazione non riuscita");
            default:
                request.setAttribute("errorTest", "OK");
                response.sendRedirect(".");
        }
    }
}