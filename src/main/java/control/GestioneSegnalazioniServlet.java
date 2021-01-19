package control;


import model.segnalazione.Segnalazione;
import model.segnalazione.SegnalazioneDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

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

        request.setAttribute("segnalazioni", segnalazioni);
    }
}
