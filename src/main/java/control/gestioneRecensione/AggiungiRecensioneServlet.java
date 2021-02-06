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
 * Servlet che permette a un filmino di aggiungere la recensione relativa a un film all’interno del database
 */
@WebServlet(name = "AggiungiRecensioneServlet", urlPatterns = "/AggiungiRecensione")
public class AggiungiRecensioneServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws IOException
     * @throws MyServletException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, MyServletException {
        request.setCharacterEncoding("UTF-8");
        int valutazione, idFilm;
        try {
            valutazione = Integer.parseInt(request.getParameter("valutazione"));
            idFilm = Integer.parseInt(request.getParameter("idFilm"));
        } catch (NumberFormatException e) {
            throw new MyServletException("Dati non validi");
        }
        String testo = request.getParameter("testo");
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        String errore = "";
        switch (recensioneDAO.addReview(valutazione, testo, idFilm, utente)) {
            case -1:
                errore = "Errore: accesso non effettuato";
                request.setAttribute("errorTest", errore);
                break;
            case -2:
                errore = "Errore: utente non ricopre il ruolo di filmino";
                request.setAttribute("errorTest", errore);
                break;
            case -3:
                errore = "Errore: range valutazione";
                request.setAttribute("errorTest", errore);
                break;
            case -4:
                errore = "Errore: lunghezza testo";
                request.setAttribute("errorTest", errore);
                break;
            default:
                errore = "Ok: recensione effettuata";
                request.setAttribute("errorTest", errore);
                response.sendRedirect("./Film?id=" + idFilm);
                return;
        }
        throw new MyServletException(errore);
    }
}