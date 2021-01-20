package control.gestioneRecensione;

import control.MyServletException;
import model.recensione.Recensione;
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, MyServletException {
        MyServletException.checkFilmino(request);
        request.setCharacterEncoding("UTF-8");
        int valutazione = Integer.parseInt(request.getParameter("valutazione"));
        String testo = request.getParameter("testo");
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        int idFilm = Integer.parseInt(request.getParameter("idFilm"));
        Recensione recensione = new Recensione(valutazione, testo, utente.getUsername(), idFilm);
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        recensioneDAO.doSave(recensione);
        response.sendRedirect("./Film?id=" + idFilm);
    }
}