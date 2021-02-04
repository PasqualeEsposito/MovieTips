package OLDintegration.test2;

import control.MyServletException;
import control.gestioneUtente.ProfiloServlet;
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

    @BeforeEach
    void setUp() {
        mockedRequest = Mockito.mock(HttpServletRequest.class);
        mockedResponse = Mockito.mock(HttpServletResponse.class);
        servlet = new ProfiloServlet();
    }

    @Test
    void TC_Profilo1() {
        Mockito.when(mockedRequest.getAttribute("username")).thenReturn("frank");
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