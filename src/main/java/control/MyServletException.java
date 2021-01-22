package control;

import javax.servlet.ServletException;

/**
 * Servlet che gestisce gli errori previsti dal sistema
 */
public class MyServletException extends ServletException {
    private static final long serialVersionUID = -8976023136478643816L;

    /**
     * @param message
     */
    public MyServletException(String message) {
        super(message);
    }
}