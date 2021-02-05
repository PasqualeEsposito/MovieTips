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
 * Servlet che permette a un utente di di segnalare la recensione di un altro utente
 */
@WebServlet(name = "SegnalaRecensioneServlet", urlPatterns = "/Segnala")
public class SegnalaRecensioneServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws IOException
     * @throws MyServletException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, MyServletException {
        int idRecensione, idFilm;
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        try {
            idRecensione = Integer.parseInt(request.getParameter("idRecensione"));
            idFilm = Integer.parseInt(request.getParameter("idFilm"));
        } catch (NumberFormatException e) {
            throw new MyServletException("Dati non validi");
        }
        String errore = "";
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        switch(recensioneDAO.reportReview(idRecensione, utente)) {
            case -1:
                errore = "Errore: accesso non effettuato";
                request.setAttribute("errorTest", errore);
                break;
            case -2:
                errore = "Errore: e-mail utente non convalidata";
                request.setAttribute("errorTest", errore);
                break;
            case 1:
                errore = "Ok: recensione segnalata";
                request.setAttribute("errorTest", errore);
                response.sendRedirect("./Film?id=" + idFilm);
                return;
        }
        //throw new MyServletException(errore);
    }
}