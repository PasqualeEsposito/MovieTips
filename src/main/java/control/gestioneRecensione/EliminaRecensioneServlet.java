package control.gestioneRecensione;

import control.MyServletException;
import model.recensione.RecensioneDAO;
import model.utente.Utente;

import javax.servlet.ServletException;
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MyServletException.checkFilmino(request);
        int idRecensione = Integer.parseInt(request.getParameter("idRecensione"));
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        recensioneDAO.doDeleteByIdRecensione(idRecensione);
        response.sendRedirect("./Profilo?username=" + utente.getUsername());
    }
}