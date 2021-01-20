package control.gestioneRecensione;

import model.recensione.RecensioneDAO;
import model.segnalazione.SegnalazioneDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet che permette a un moderatore di eliminare una recensione segnalata
 */
@WebServlet(name = "ModeraRecensioneServlet", urlPatterns = "/ModeraRecensione")
public class ModeraRecensioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idRecensione = Integer.parseInt(request.getParameter("idRecensione"));
        if (Integer.parseInt(request.getParameter("elimina")) == 1) {
            RecensioneDAO serviceRecensione = new RecensioneDAO();
            serviceRecensione.doDeleteByIdRecensione(idRecensione);
        } else {
            SegnalazioneDAO serviceSegnalazione = new SegnalazioneDAO();
            serviceSegnalazione.doDeleteByIdRecensione(idRecensione);
        }
        response.sendRedirect("./GestioneSegnalazioni");
    }
}
