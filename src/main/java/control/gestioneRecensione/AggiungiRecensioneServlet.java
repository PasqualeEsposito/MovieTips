package control.gestioneRecensione;

import model.recensione.RecensioneDAO;
import model.utente.Utente;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet che permette a un filmino di aggiungere la recensione relativa a un film all’interno del database
 */
@WebServlet(name = "AggiungiRecensioneServlet", urlPatterns = "/AggiungiRecensione")
public class AggiungiRecensioneServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {    // Inserire controlli utente
        request.setCharacterEncoding("UTF-8");
        int valutazione = Integer.parseInt(request.getParameter("valutazione"));
        String testo = request.getParameter("testo");
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        int idFilm = Integer.parseInt(request.getParameter("idFilm"));
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        recensioneDAO.doSave(valutazione, testo, utente.getUsername(), idFilm);
        response.sendRedirect("./Film?id=" + idFilm);
    }
}