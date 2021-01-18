package control;

import model.utente.Utente;
import model.utente.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UtenteDAO utenteDAO = new UtenteDAO();

    /**
     * La servlet permette di effettuare il login prendendo i dati dal form di login presente nella view login.jsp,
     * controlla i campi e ritorna errori nel caso in cui i dati in input siano errati. Nel caso in cui i dati sono
     * corretti, l'utente viene inserito in sessione.
     */

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MyServletException.checkAccount(request);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/login.jsp");
        requestDispatcher.forward(request, response);
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Utente utente = null;
        if (email != null && password != null) {
            utente = utenteDAO.doRetrieveByEmailPassword(email, password);
        }
        if (utente == null) {
            throw new MyServletException("Username e/o password non validi");
        }
        request.getSession().setAttribute("utente", utente);
        response.sendRedirect(".");
    }
}