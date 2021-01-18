package control;

import model.utente.Utente;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet che gestisce gli errori previsti dal sistema
 */
public class MyServletException extends ServletException {
    private static final long serialVersionUID = -8976023136478643816L;

    public MyServletException() {
        super();
    }

    /**
     * @param message
     */
    public MyServletException(String message) {
        super(message);
    }

    /**
     * Metodo che lancia l'eccezione MyServletException se esiste un attributo "utente" all'interno della sessione
     * @param request
     * @throws MyServletException
     */
    public static void checkSession(HttpServletRequest request) throws MyServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente != null)
            throw new MyServletException(("Utente non autorizzato"));
    }
}