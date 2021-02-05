package control.gestioneRecensione;

import control.MyServletException;
import model.gestioneRecensione.RecensioneDAO;
import model.gestioneUtente.Utente;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet che permette a un un filmino di eliminare una sua recensione
 */
@WebServlet(name = "EliminaRecensioneServlet", urlPatterns = "/EliminaRecensione")
public class EliminaRecensioneServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws IOException
     * @throws MyServletException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, MyServletException {
        int idRecensione;
        String usernameUtente = request.getParameter("usernameUtente");
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        try {
            idRecensione = Integer.parseInt(request.getParameter("idRecensione"));
        } catch (NumberFormatException e) {
            throw new MyServletException("Dati non validi");
        }
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        if (recensioneDAO.deleteReview(idRecensione, utente, usernameUtente) == false) {
            throw new MyServletException("Utente non autorizzato");
        }
        response.sendRedirect("./Profilo?username=" + utente.getUsername());
    }
}