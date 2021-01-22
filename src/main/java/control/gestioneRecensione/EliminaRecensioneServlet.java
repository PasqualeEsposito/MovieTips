package control.gestioneRecensione;

import control.MyServletException;
import model.recensione.RecensioneDAO;
import model.utente.Utente;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, MyServletException {
        String usernameUtente = request.getParameter("usernameUtente");
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null || !usernameUtente.equals(utente.getUsername())) {
            throw new MyServletException("Utente non autorizzato");
        }
        int idRecensione = Integer.parseInt(request.getParameter("idRecensione"));
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        recensioneDAO.doDeleteByIdRecensione(idRecensione);
        response.sendRedirect("./Profilo?username=" + utente.getUsername());
    }
}