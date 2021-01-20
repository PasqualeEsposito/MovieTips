package control;

import model.recensione.Recensione;
import model.recensione.RecensioneDAO;
import model.utente.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RecensioneServlet", urlPatterns = "/Recensione")
public class RecensioneServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int valutazione = Integer.parseInt(request.getParameter("valutazione"));
        String testo = request.getParameter("testo");
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        int idFilm = Integer.parseInt(request.getParameter("idFilm"));
        if (valutazione != 0) {
            Recensione recensione = new Recensione(valutazione, testo, utente.getUsername(), idFilm);
            RecensioneDAO service = new RecensioneDAO();
            service.doSave(recensione);
        }
        response.sendRedirect("./Film?id=" + idFilm);
    }
}