package control;

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
import java.util.regex.Pattern;

@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        String risposta;

        if (email.compareTo("") == 0 || !Pattern.matches("[A-Za-z.]+[0-9]*@[A-Za-z.]+", email) || password.compareTo("") == 0)
            risposta = "no";
        else {
            UtenteDAO serviceUtente = new UtenteDAO();
            Utente utente;
            try {
                utente = serviceUtente.doRetrieveByEmailPassword(email, password);
                if (utente == null)
                    risposta = "no";
                else {
                    risposta = "ok";
                    HttpSession ssn = request.getSession(false);
                    if (ssn != null)
                        ssn.setAttribute("utente", utente);
                    request.setAttribute("utente", utente);
                }
            } catch (RuntimeException r) {
                risposta = "no";
            }
        }
        response.setContentType("text/plain");
        out.println(risposta);
    }
}