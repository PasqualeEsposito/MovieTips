package control;

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

@WebServlet(name = "EliminaRecensioneServlet", urlPatterns = "/GestioneSegnalazioni")
public class GestioneSegnalazioniServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SegnalazioneDAO serviceSegnalazione = new SegnalazioneDAO();
        ArrayList<Segnalazione> segnalazioni;
        segnalazioni = serviceSegnalazione.doRetrieveAll();
        RecensioneDAO serviceRecensioni = new RecensioneDAO();
        ArrayList<Recensione> recensioni = new ArrayList<>();

        for(int i = 0; i < segnalazioni.size(); i++) {
            Recensione r = serviceRecensioni.doRetrieveByIdRecensione(segnalazioni.get(i).getIdRecensione());
            recensioni.add(r);
        }

        //sia segnalazioni che recensioni sono in ordine, nel senso che se si prende la segnalazione 1,
        // la recensione 1 avrà i valori complementari a quella segnalazione
        request.setAttribute("segnalazioni", segnalazioni);
        request.setAttribute("recensioni", recensioni);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/segnalazioniRecensioni.jsp");
        requestDispatcher.forward(request, response);

    }
}