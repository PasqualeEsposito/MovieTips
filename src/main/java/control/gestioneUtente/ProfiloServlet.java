package control.gestioneUtente;

import control.MyServletException;
import model.gestioneRecensione.Recensione;
import model.gestioneRecensione.RecensioneDAO;
import model.gestioneUtente.Utente;
import model.gestioneUtente.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet che gestisce la corretta visualizzazione della pagina profilo di un utente
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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        UtenteDAO utenteDAO = new UtenteDAO();
        Utente profilo = utenteDAO.getUser(username);
        /*if(profilo == null)
            request.setAttribute("errorTest", "Errore: utente non esistente");
        else
            request.setAttribute("errorTest", "Ok: utente esistente");*/
        if (profilo == null) {
            throw new MyServletException("Siamo spiacenti, la pagina richiesta non è stata trovata");
        }
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        List<Recensione> recensioni = recensioneDAO.getReviewsByUser(username);
        request.setAttribute("profilo", profilo);
        request.setAttribute("recensioni", recensioni);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/profilo.jsp");
        requestDispatcher.forward(request, response);
    }
}