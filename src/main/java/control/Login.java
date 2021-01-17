package control;

import model.recensione.RecensioneDAO;
import model.utente.Utente;
import model.utente.UtenteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        Utente utente;
        UtenteDAO serviceUtente = new UtenteDAO();
        RecensioneDAO serviceRecensione = new RecensioneDAO();
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            if (email.length() == 0) {
                request.setAttribute("errorTest", "Il login non va a buon fine poiché il campo email è vuoto");
                session.setAttribute("errorType", "email");
                session.setAttribute("error", "Campo vuoto");
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/login.jsp"));

            } else {
                if (password.length() == 0) {
                    request.setAttribute("errorTest", "Il login non va a buon fine poiché il campo password è vuoto");
                    session.setAttribute("errorType", "password");
                    session.setAttribute("error", "Campo vuoto");
                    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/login.jsp"));
                } else {
                    utente = serviceUtente.doRetrieveByEmail(email);
                    if (utente.getEmail().equals("")) { //Controllo se l'email non è presente nel db
                        request.setAttribute("errorTest", "Il login non va a buon fine poiché l'email non è presente nel db");
                        session.setAttribute("errorType", "email");
                        session.setAttribute("error", "Email non presente nel database");
                        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/login.jsp"));
                    } else {
                        //Problema in questo passaggio
                        MessageDigest digest = MessageDigest.getInstance("SHA-1");
                        digest.reset();
                        digest.update(password.getBytes(StandardCharsets.UTF_8));
                        String curr = String.format("%040x", new BigInteger(1, digest.digest()));
                        String user = utente.getPassword(); //Password dell'utente
                        if (!user.equals(curr)) {
                            request.setAttribute("errorTest", "Il login non va a buon fine poiché l'email e la password non combaciano");
                            session.setAttribute("errorType", "password");
                            session.setAttribute("error", "Email o password errate");
                            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/login.jsp"));
                        } else {
                            request.setAttribute("errorTest", "Il login viene effettuato correttamente");
                            session.setAttribute("recensione", serviceRecensione.doRetrieveById_utente(utente.getId_utente()));
                            session.setAttribute("utente", utente);
                            session.setAttribute("errorType", null);
                            session.setAttribute("error", null);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}