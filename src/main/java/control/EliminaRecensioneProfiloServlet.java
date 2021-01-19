package control;

import model.recensione.RecensioneDAO;
import model.utente.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EliminaRecensioneServlet", urlPatterns = "/EliminaRecensioneProfilo")
public class EliminaRecensioneProfiloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idRecensione = Integer.parseInt(request.getParameter("idRecensione"));
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        RecensioneDAO serviceRecensione = new RecensioneDAO();
        serviceRecensione.doDeleteByIdRecensione(idRecensione);

        response.sendRedirect("./Profilo?username=" + utente.getUsername());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
