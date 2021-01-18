package control;

import model.utente.Utente;
import model.utente.UtenteDAO;

import javax.servlet.RequestDispatcher;
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
import java.util.Arrays;

/**
 * Servlet che gestisce il login inserendo l'utente nella sessione. Lancia l'eccezione MyServletException se i dati inseriti dall'utente non sono validi
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UtenteDAO utenteDAO = new UtenteDAO();

    public LoginServlet() {
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MyServletException.checkSession(request);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/login.jsp");
        requestDispatcher.forward(request, response);
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente;
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            if (email.length() == 0) {
                request.setAttribute("errorTest", "Il login non va a buon fine poiché il campo email è vuoto");
                session.setAttribute("errorType", "email");
                session.setAttribute("error", "Campo vuoto");

            } else {
                if (password.length() == 0) {
                    request.setAttribute("errorTest", "Il login non va a buon fine poiché il campo password è vuoto");
                    session.setAttribute("errorType", "password");
                    session.setAttribute("error", "Campo vuoto");
                } else {
                    utente = utenteDAO.doRetrieveByEmail(email);
                    if (utente.getEmail().equals("")) {//Controllo se l'email non è presente nel db
                        request.setAttribute("errorTest", "Il login non va a buon fine poiché l'email non è presente nel db");
                        session.setAttribute("errorType", "email");
                        session.setAttribute("error", "Email non presente nel database");
                    } else {
                        MessageDigest digest = MessageDigest.getInstance("SHA-1");
                        digest.reset();
                        digest.update(password.getBytes(StandardCharsets.UTF_8));
                        password = String.format("%040x", new BigInteger(1, digest.digest()));
                        String currentPassword = utente.getPassword();
                        if (!password.equals(currentPassword)) {
                            request.setAttribute("errorTest", "Il login non va a buon fine poiché l'email e la password non combaciano");
                            session.setAttribute("errorType", "password");
                            session.setAttribute("error", "Email o password errate");
                        } else {
                            request.setAttribute("errorTest", "Il login viene effettuato correttamente");
                            session.setAttribute("utente", utente);
                            session.setAttribute("errorType", null);
                            session.setAttribute("error", null);
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/homePage.jsp");
                            dispatcher.forward(request, response);
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            throw new MyServletException("Username e/o password non validi");
        }
    }
}