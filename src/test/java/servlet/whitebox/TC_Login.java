package servlet.whitebox;

import control.MyServletException;
import control.gestioneUtente.LoginServlet;
import model.utente.Utente;
import model.utente.UtenteDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TC_Login {
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private LoginServlet servlet;
    private HttpSession session;
    private UtenteDAO utenteDAO;
    private Utente utente;

    @BeforeEach
    void setUp() {
        session = Mockito.mock(HttpSession.class);
        mockedRequest = Mockito.mock(HttpServletRequest.class);
        mockedResponse = Mockito.mock(HttpServletResponse.class);
        servlet = new LoginServlet();
        utenteDAO = new UtenteDAO();
        utente = new Utente("frank", "francesco@unisa.it", "Francesco", "Ceriello", "Uomo", "1985-12-10", "001000");
        utenteDAO.doSave("frank", "francesco@unisa.it", "Francesco1!", "Francesco", "Ceriello", "Uomo", "1985-12-10", "100000");
        Mockito.when(mockedRequest.getSession()).thenReturn(session);
    }

    @Test
    void TC_Login1() {
        Mockito.when(mockedRequest.getSession().getAttribute("utente")).thenReturn(utente);
        String message = "Utente non autorizzato";
        MyServletException exception = assertThrows(MyServletException.class, () ->
                servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_Login2() {
        Mockito.when(mockedRequest.getParameter("mail")).thenReturn("francesco@unisa.it");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("Francesco1!");
        String message = "Utente bannato";
        MyServletException exception = assertThrows(MyServletException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @AfterEach
    void tearDown() {
        utenteDAO.doDeleteByUsername("frank");
        servlet = null;
        mockedRequest = null;
        mockedResponse = null;
    }
}