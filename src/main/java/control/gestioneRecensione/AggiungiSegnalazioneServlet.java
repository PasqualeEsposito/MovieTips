package control.gestioneRecensione;

import control.MyServletException;
import model.segnalazione.Segnalazione;
import model.segnalazione.SegnalazioneDAO;
import model.utente.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet che permette a un utente di di segnalare la recensione di un altro utente
 */
@WebServlet(name = "AggiungiSegnalazioneServlet", urlPatterns = "/Segnala")
public class AggiungiSegnalazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MyServletException.checkAccount(request);
        int idRecensione = Integer.parseInt(request.getParameter("idRecensione"));
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        Segnalazione s = new Segnalazione(idRecensione, utente.getUsername());
        SegnalazioneDAO segnalazioneDAO = new SegnalazioneDAO();
        segnalazioneDAO.doSave(s);
        int idFilm = Integer.parseInt(request.getParameter("idFilm"));
        response.sendRedirect("./Film?id=" + idFilm);
    }
}
