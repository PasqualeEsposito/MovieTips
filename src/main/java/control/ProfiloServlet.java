package control;

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

@WebServlet(name = "ProfiloServlet", urlPatterns = "/Profilo")
public class ProfiloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        UtenteDAO utenteDAO = new UtenteDAO();
        Utente profilo = utenteDAO.doRetrieveByUsername(username);
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        ArrayList<Recensione> recensioni = recensioneDAO.doRetrieveByUsername(username);
        request.setAttribute("profilo", profilo);
        request.setAttribute("recensioni", recensioni);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/profiloUtente.jsp");
        requestDispatcher.forward(request, response);
    }

}
