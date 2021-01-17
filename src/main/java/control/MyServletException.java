package control;

import model.utente.Utente;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class MyServletException extends ServletException {
    private static final long serialVersionUID = -8976023136478643816L;

    public MyServletException() {
        super();
    }

    public MyServletException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public MyServletException(String message) {
        super(message);
    }

    public MyServletException(Throwable rootCause) {
        super(rootCause);
    }

    public static void checkAccount(HttpServletRequest request) throws MyServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente != null)
            throw new MyServletException(("Utente non autorizzato"));
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
