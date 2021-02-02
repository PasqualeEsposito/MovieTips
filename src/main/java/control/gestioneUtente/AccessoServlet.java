package control.gestioneUtente;

import control.MyServletException;
import model.gestioneUtente.Utente;
import model.gestioneUtente.UtenteDAO;

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
@WebServlet(name = "AccessoServlet", urlPatterns = "/Accesso")
public class AccessoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UtenteDAO utenteDAO = new UtenteDAO();

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente != null) {
            throw new MyServletException("Utente non autorizzato");
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/login.jsp");
        requestDispatcher.forward(request, response);
    }

    /**
     * @param request
     * @param response
     * @throws IOException
     * @throws MyServletException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, MyServletException {
        request.setCharacterEncoding("UTF-8");
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente != null) {
            throw new MyServletException("Utente non autorizzato");
        }
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        if (mail.length() < 8 || mail.length() > 255) {
            request.setAttribute("errorTest", "LE_FAIL");
        } else {
            if (!mail.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")) {
                request.setAttribute("errorTest", "FE_FAIL");
            } else {
                utente = utenteDAO.doRetrieveByMail(mail);
                if (utente.isBanned()) {
                    throw new MyServletException("Utente bannato");
                }
                if (utente == null) {
                    request.setAttribute("errorTest", "EE_FAIL");
                } else {
                    if (password.length() < 8 || password.length() > 255) {
                        request.setAttribute("errorTest", "LP_FAIL");
                    } else {
                        if (password.toUpperCase().equals(password) || password.toLowerCase().equals(password) || !password.matches(".*[0-9].*")) {
                            request.setAttribute("errorTest", "FP_FAIL");
                        } else {
                            utente = utenteDAO.doRetrieveByMailPassword(mail, password);
                            if (utente == null) {
                                request.setAttribute("errorTest", "CP_FAIL");
                            } else {
                                request.setAttribute("errorTest", "OK");
                                request.getSession().setAttribute("utente", utente);
                                response.sendRedirect(".");
                                return;
                            }
                        }
                    }
                }
            }
        }
        throw new MyServletException("Username e/o password non validi");
    }
}