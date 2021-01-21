package control.gestioneUtente;

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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Utente utente;
        if (email.length() < 8 || email.length() > 255) {
            request.setAttribute("errorTest", "LE_FAIL");
        } else {
            if (!email.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")) {
                request.setAttribute("errorTest", "FE_FAIL");
            } else {
                utente = utenteDAO.doRetrieveByEmail(email);
                if (utente == null) {
                    request.setAttribute("errorTest", "EE_FAIL");
                } else {
                    if (password.length() < 8 || password.length() > 255) {
                        request.setAttribute("errorTest", "LP_FAIL");
                    } else {
                        if (password.toUpperCase().equals(password) || password.toLowerCase().equals(password) || !password.matches(".*[0-9].*")) {
                            request.setAttribute("errorTest", "FP_FAIL");
                        } else {
                            utente = utenteDAO.doRetrieveByEmailPassword(email, password);
                            if (utente == null) {
                                request.setAttribute("errorTest", "CP_FAIL");
                            } else {
                                request.setAttribute("errorTest", "OK");
                                request.getSession().setAttribute("utente", utente);
                                response.sendRedirect(".");
                            }
                        }
                    }
                }
            }
        }
    }
}