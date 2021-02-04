package OLDintegration.test2;

import control.MyServletException;
import control.gestioneUtente.BannaUtenteServlet;
import model.gestioneUtente.Utente;
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
public class TC_BannaUtente {
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private BannaUtenteServlet servlet;
    private HttpSession session;
    private Utente utente;
    private Utente utenteModeratore;

    @BeforeEach
    void setUp() {
        session = Mockito.mock(HttpSession.class);
        mockedRequest = Mockito.mock(HttpServletRequest.class);
        mockedResponse = Mockito.mock(HttpServletResponse.class);
        servlet = new BannaUtenteServlet();
        utente = new Utente("frank", "francesco@unisa.it", "Francesco", "Ceriello", "Uomo", "1985-12-10", "001000");
        utenteModeratore = new Utente("frank", "francesco@unisa.it", "Francesco", "Ceriello", "Uomo", "1985-12-10", "000001");
        Mockito.when(mockedRequest.getSession()).thenReturn(session);
    }

    @Test
    void TC_BannaUtenteServlet1() {
        Mockito.when(mockedRequest.getSession().getAttribute("utente")).thenReturn(utente);
        String message = "Utente non autorizzato";
        MyServletException exception = assertThrows(MyServletException.class, () ->
                servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_BannaUtenteServlet2() {
        Mockito.when(mockedRequest.getSession().getAttribute("utente")).thenReturn(utenteModeratore);
        Mockito.when(mockedRequest.getParameter("username")).thenReturn(utenteModeratore.getUsername());
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