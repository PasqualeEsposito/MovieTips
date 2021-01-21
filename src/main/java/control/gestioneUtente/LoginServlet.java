package control.gestioneUtente;

import control.MyServletException;
import model.utente.Utente;
import model.utente.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        request.setCharacterEncoding("UTF-8");
        //INSERIRE CONTROLLO NEL CASO IN CUI IL CLIENTE è BANNATO
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