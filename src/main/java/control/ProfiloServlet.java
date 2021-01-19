package control;

import model.recensione.Recensione;
import model.recensione.RecensioneDAO;
import model.utente.Utente;
import model.utente.UtenteDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ProfiloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        UtenteDAO serviceUtente = new UtenteDAO();
        Utente u = serviceUtente.doRetrieveByUsername(username);
        RecensioneDAO serviceRecensione = new RecensioneDAO();
        ArrayList<Recensione> recensioni = serviceRecensione.doRetrieveByUsername(username);
        request.setAttribute("recensioni", recensioni);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
