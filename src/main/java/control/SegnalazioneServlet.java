package control;

import model.segnalazione.Segnalazione;
import model.segnalazione.SegnalazioneDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SegnalazioneServlet", urlPatterns = "/Segnala")
public class SegnalazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idRecensione = Integer.parseInt(request.getParameter("idRecensione"));
        String usernameUtente = request.getParameter("usernameUtente");
        int idFilm = Integer.parseInt(request.getParameter("idFilm"));
        Segnalazione s = new Segnalazione(idRecensione, usernameUtente);
        SegnalazioneDAO service = new SegnalazioneDAO();
        service.doSave(s);

        response.sendRedirect("/Film?id=" + idFilm);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
