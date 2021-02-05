package integration;

import control.gestioneFilm.RicercaFilmServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test_RicercaFilmServlet extends Mockito {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private RicercaFilmServlet servlet;

    @BeforeEach
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new RicercaFilmServlet();
    }

    @Test
    public void testRicercaFilm1() throws ServletException, IOException {
        request.setParameter("inputRicerca", "");
        String message = "Errore: lunghezza ricerca";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testRicercaFilm2() throws ServletException, IOException {
        request.setParameter("inputRicerca", "ta");
        String message = "Ok: ricerca effettuata";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }
}
