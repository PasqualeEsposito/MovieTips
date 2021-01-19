package control;

import model.recensione.Recensione;
import model.recensione.RecensioneDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RecensioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int valutazione = Integer.parseInt(req.getParameter("valutazione"));
        String testo = req.getParameter("testo");
        String usernameUtente = req.getParameter("usernameUtente");
        int idFilm = Integer.parseInt(req.getParameter("idFilm"));

        if(valutazione != 0) {
            Recensione recensione = new Recensione(valutazione, testo, usernameUtente, idFilm);
            RecensioneDAO service = new RecensioneDAO();
            service.doSave(recensione);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
