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
        switch (recensioneDAO.addReview(valutazione, testo, utente, idFilm)) {
            case -1:
                throw new MyServletException("Utente non autorizzato");
            case -2:
                request.setAttribute("errorTest", "RV1_FAIL");
                break;
            case -3:
                request.setAttribute("errorTest", "LT_FAIL");
                break;
            default:
                request.setAttribute("errorTest", "OK");
                response.sendRedirect("./Film?id=" + idFilm);
                return;
        }
        throw new MyServletException("Recensione non valida");
    }
}