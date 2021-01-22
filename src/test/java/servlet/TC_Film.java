package servlet;

import control.MyServletException;
import control.gestioneFilm.FilmServlet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TC_Film {
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private FilmServlet servlet;

    @BeforeEach
    void setUp() {
        mockedRequest = Mockito.mock(HttpServletRequest.class);
        mockedResponse = Mockito.mock(HttpServletResponse.class);
        servlet = new FilmServlet();
    }

    @Test
    void TC_Film1() {
        Mockito.when(mockedRequest.getParameter("id")).thenReturn("0");
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