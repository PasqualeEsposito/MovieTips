package servlet.whitebox;

import control.MyServletException;
import control.gestioneRecensione.GestioneSegnalazioniServlet;
import control.gestioneUtente.ModeraUtenteServlet;
import model.utente.Utente;
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
public class TC_ModeraUtente {
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private ModeraUtenteServlet servlet;
    private HttpSession session;
    private Utente utente;
    private String username;
    private Utente utenteModeratore;
    @BeforeEach
    void setUp() {
        session = Mockito.mock(HttpSession.class);
        mockedRequest = Mockito.mock(HttpServletRequest.class);
        mockedResponse = Mockito.mock(HttpServletResponse.class);
        servlet = new ModeraUtenteServlet();
        utente = new Utente("frank", "francesco@unisa.it", "Francesco", "Ceriello", "Uomo", "1985-12-10", "001000");
        utenteModeratore = new Utente("moderatore", "moderatore@unisa.it", "Moderatore", "Ceriello", "Uomo", "1985-12-10", "000001");
        username="moderatore";
        Mockito.when(mockedRequest.getSession()).thenReturn(session);
    }

    @Test
    void TC_ModeraUtenteServlet1() {
        Mockito.when(mockedRequest.getSession().getAttribute("utente")).thenReturn(utente);
        String message = "Utente non autorizzato";
        MyServletException exception = assertThrows(MyServletException.class, () ->
                servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }
    @Test
    void TC_ModeraUtenteServlet2() {
        Mockito.when(mockedRequest.getSession().getAttribute("utente")).thenReturn(utenteModeratore);
        Mockito.when(mockedRequest.getParameter("username")).thenReturn(username);
        String message = "Operazione non autorizzata";
        MyServletException exception = assertThrows(MyServletException.class, () ->
                servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }
    @AfterEach
    void tearDown() {
        session = null;
        servlet = null;
        mockedRequest = null;
        mockedResponse = null;
    }
}
