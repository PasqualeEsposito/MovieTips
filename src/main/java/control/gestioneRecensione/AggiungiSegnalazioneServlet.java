package control.gestioneRecensione;

import control.MyServletException;
import model.recensione.RecensioneDAO;

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
        RecensioneDAO service = new RecensioneDAO();
        service.doUpdateSegnalazioneTrue(idRecensione);
        int idFilm = Integer.parseInt(request.getParameter("idFilm"));
        response.sendRedirect("./Film?id=" + idFilm);
    }
}
