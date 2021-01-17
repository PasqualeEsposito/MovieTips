package control;

import model.utente.Utente;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class ExceptionServlet extends ServletException {
    private static final long serialVersionUID = -8976023136478643816L;

    public ExceptionServlet() {
        super();
    }

    public ExceptionServlet(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public ExceptionServlet(String message) {
        super(message);
    }

    public ExceptionServlet(Throwable rootCause) {
        super(rootCause);
    }

    public static void checkFilmino(HttpServletRequest request) throws MyServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null || !utente.isFilmino()) {
            throw new MyServletException("Utente non autorizzato");
        }
    }

    public static void checkModeratore(HttpServletRequest request) throws MyServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null || !utente.isModeratore()) {
            throw new MyServletException("Utente non autorizzato");
        }
    }

    public static void checkArticolista(HttpServletRequest request) throws MyServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null || !utente.isArticolista()) {
            throw new MyServletException("Utente non autorizzato");
        }
    }

    public static void checkAmministratore(HttpServletRequest request) throws MyServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null || !utente.isAmministratore()) {
            throw new MyServletException("Utente non autorizzato");
        }
    }
}