package servlet.whitebox;

import control.MyServletException;
import control.gestioneRecensione.AggiungiSegnalazioneServlet;
import control.gestioneUtente.ProfiloServlet;
import model.utente.Utente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TC_Profilo {
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private ProfiloServlet servlet;
    private String username;

    @BeforeEach
    void setUp() {
        mockedRequest = Mockito.mock(HttpServletRequest.class);
        mockedResponse = Mockito.mock(HttpServletResponse.class);
        servlet = new ProfiloServlet();
        username = "frank";
    }

    @Test
    void TC_Profilo1() {
        Mockito.when(mockedRequest.getAttribute("username")).thenReturn(username);
        String message = "Siamo spiacenti, la pagina richiesta non è stata trovata";
        MyServletException exception = assertThrows(MyServletException.class, () ->
                servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @AfterEach
    void tearDown() {
        servlet = null;
        mockedRequest = null;
        mockedResponse = null;
    }
}
