package control.gestioneRecensione;

import model.recensione.Recensione;
import model.recensione.RecensioneDAO;
import model.segnalazione.Segnalazione;
import model.segnalazione.SegnalazioneDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet che gestisce la corretta visualizzazione della pagina riservata al moderatore inserendo tutte le recensioni segnalate
 */
@WebServlet(name = "GestioneSegnalazioniServlet", urlPatterns = "/GestioneSegnalazioni")
public class GestioneSegnalazioniServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SegnalazioneDAO segnalazioneDAO = new SegnalazioneDAO();
        ArrayList<Segnalazione> segnalazioni;
        segnalazioni = segnalazioneDAO.doRetrieveAll();
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        ArrayList<Recensione> recensioni = new ArrayList<>();
        for (int i = 0; i < segnalazioni.size(); i++) {
            Recensione r = recensioneDAO.doRetrieveByIdRecensione(segnalazioni.get(i).getIdRecensione());
            recensioni.add(r);
        }
        request.setAttribute("segnalazioni", segnalazioni);
        request.setAttribute("recensioni", recensioni);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/segnalazioni.jsp");
        requestDispatcher.forward(request, response);

    }
}