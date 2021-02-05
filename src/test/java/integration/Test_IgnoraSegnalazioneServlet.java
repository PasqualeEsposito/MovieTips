package integration;

import control.gestioneRecensione.IgnoraSegnalazioneServlet;
import model.gestioneUtente.Utente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test_IgnoraSegnalazioneServlet extends Mockito {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private IgnoraSegnalazioneServlet servlet;

    @BeforeEach
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new IgnoraSegnalazioneServlet();
    }

    @Test
    public void testIgnoraSegnalazione1() throws ServletException, IOException {
        request.addParameter("idRecensione", "1");
        request.getSession().setAttribute("utente", null);
        String message = "Errore: accesso non effettuato";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testIgnoraSegnalazione2() throws ServletException, IOException {
        Utente utente = new Utente();
        utente.setUsername("fabrizio_ceriello");
        utente.setRuolo("001000");
        request.addParameter("idRecensione", "3");
        request.getSession().setAttribute("utente", utente);
        String message = "Errore: utente non ricopre il ruolo di moderatore";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testIgnoraSegnalazione3() throws ServletException, IOException {
        Utente utente = new Utente();
        utente.setUsername("marco_bellamico");
        utente.setRuolo("000001");
        request.addParameter("idRecensione", "100");
        request.getSession().setAttribute("utente", utente);
        String message = "Errore: recensione non presente nel database";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testIgnoraSegnalazione4() throws ServletException, IOException {
        Utente utente = new Utente();
        utente.setUsername("marco_bellamico");
        utente.setRuolo("000001");
        request.addParameter("idRecensione", "1");
        request.getSession().setAttribute("utente", utente);
        String message = "Ok: recensione ignorata";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }
}
