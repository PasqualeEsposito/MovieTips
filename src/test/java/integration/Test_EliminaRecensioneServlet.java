package integration;

import control.gestioneRecensione.EliminaRecensioneServlet;
import model.gestioneUtente.Utente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test_EliminaRecensioneServlet extends Mockito {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private EliminaRecensioneServlet servlet;

    @BeforeEach
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new EliminaRecensioneServlet();
    }

    @Test
    public void testAggiungiValutazione1() throws ServletException, IOException {
        request.addParameter("idRecensione", "1");
        request.getSession().setAttribute("utente", null);
        String message = "Errore: accesso non effettuato";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testAggiungiValutazione2() throws ServletException, IOException {
        Utente utente = new Utente();
        utente.setUsername("fabrizio_ceriello");
        request.addParameter("idRecensione", "3");
        request.getSession().setAttribute("utente", utente);
        String message = "Errore: utente non può eliminare la recensione di un altro utente";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testAggiungiValutazione3() throws ServletException, IOException {
        Utente utente = new Utente();
        utente.setUsername("fabrizio_ceriello");
        request.addParameter("idRecensione", "1");
        request.getSession().setAttribute("utente", utente);
        String message = "Ok: recensione eliminata";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }
}
