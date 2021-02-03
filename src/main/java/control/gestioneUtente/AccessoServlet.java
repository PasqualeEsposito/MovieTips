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
            throw new MyServletException("Operazione non autorizzata");
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
            throw new MyServletException("Operazione non autorizzato");
        }
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

             switch (utenteDAO.doRetrieveByMailPassword(mail, password)){
                 case -1:
                     request.setAttribute("errorTest", "LE_FAIL");
                     break;
                 case -2:
                     request.setAttribute("errorTest", "FE_FAIL");
                     break;
                 case -3:
                     throw new MyServletException("Utente bannato");
                 case -4:
                     request.setAttribute("errorTest", "EE_FAIL");
                     break;
                 case -5:
                     request.setAttribute("errorTest", "LP_FAIL");
                     break;
                 case -6:
                     request.setAttribute("errorTest", "FP_FAIL");
                     break;
                 case -7:
                     request.setAttribute("errorTest", "CP_FAIL");
                     break;
                 default:
                         request.setAttribute("errorTest", "OK");
                         request.getSession().setAttribute("utente", utente);
                         response.sendRedirect(".");
                         return;
             }
             throw new MyServletException("Username e/o password non validi");
    }
}
