package control.gestioneRecensione;

import control.MyServletException;
import model.gestioneRecensione.RecensioneDAO;
import model.gestioneUtente.Utente;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet che permette a un moderatore di ignorare una recensione segnalata
 */
@WebServlet(name = "IgnoraSegnalazioneServlet", urlPatterns = "/IgnoraSegnalazione")
public class IgnoraSegnalazioneServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws IOException
     * @throws MyServletException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, MyServletException {
        int idRecensione;
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        try {
            idRecensione = Integer.parseInt(request.getParameter("idRecensione"));
        } catch (NumberFormatException e) {
            throw new MyServletException("Dati non validi");
        }
        String errore="";
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        switch (recensioneDAO.ignoreReporting(idRecensione, utente)){
            case -1:
                errore = "Errore: accesso non effettuato";
                request.setAttribute("errorTest", errore);
                break;
            case -2:
                errore = "Errore: utente non ricopre il ruolo di moderatore";
                request.setAttribute("errorTest", errore);
                break;
            case -3:
                errore = "Errore: recensione non presente nel database";
                request.setAttribute("errorTest", errore);
                break;
            case 1:
                errore = "Ok: recensione ignorata";
                request.setAttribute("errorTest", errore);
                response.sendRedirect("./GestioneSegnalazioni");
                return;
        }
          //  throw new MyServletException(errore);
    }
}