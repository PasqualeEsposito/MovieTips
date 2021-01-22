package control.gestioneUtente;

import control.MyServletException;
import model.recensione.Recensione;
import model.recensione.RecensioneDAO;
import model.utente.Utente;
import model.utente.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet che gestisce la corretta visualizzazione della pagina profilo di un utente inserendo i dati dell'utente nella request
 */
@WebServlet(name = "ProfiloServlet", urlPatterns = "/Profilo")
public class ProfiloServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        UtenteDAO utenteDAO = new UtenteDAO();
        Utente profilo = utenteDAO.doRetrieveByUsername(username);
        if (profilo == null) {
            throw new MyServletException("Siamo spiacenti, la pagina richiesta non è stata trovata");
        }
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        ArrayList<Recensione> recensioni = recensioneDAO.doRetrieveByUsername(username);
        request.setAttribute("profilo", profilo);
        request.setAttribute("recensioni", recensioni);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/profilo.jsp");
        requestDispatcher.forward(request, response);
    }
}