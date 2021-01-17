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
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.regex.Pattern;

@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Utente utente = new Utente();
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
                        MessageDigest md = MessageDigest.getInstance("SHA-256");
                        //Problema in questo passaggio
                        byte curr[] = md.digest(password.getBytes()); //Password inserita hashata in SHA-256
                        byte user[] = utente.getPassword(); //Password dell'utente
                        if (!Arrays.equals(curr, user)) {
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
                            System.out.println("Ok");
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}