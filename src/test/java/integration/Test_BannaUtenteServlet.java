package integration;

import control.gestioneUtente.BannaUtenteServlet;
import model.gestioneUtente.Utente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test_BannaUtenteServlet extends Mockito {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private BannaUtenteServlet servlet;

    @BeforeEach
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new BannaUtenteServlet();
    }

    @Test
    public void testBan1() throws ServletException, IOException {
        request.addParameter("username", "roberta_esposito");
        String message = "Errore: accesso non effettuato";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testBan2() throws ServletException, IOException {
        Utente utente = new Utente();
        utente.setUsername("roberta_esposito");
        utente.setRuolo("001000");
        request.getSession().setAttribute("utente", utente);
        request.addParameter("username", "roberta_esposito");
        String message = "Errore: utente non ricopre il ruolo di moderatore";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testBan3() throws ServletException, IOException {
        Utente utente = new Utente();
        utente.setUsername("marco_bellamico");
        utente.setRuolo("000001");
        request.getSession().setAttribute("utente", utente);
        request.addParameter("username", "marco_bellamico");
        String message = "Errore: utente non può autoeliminarsi";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testBan4() throws ServletException, IOException {
        Utente utente = new Utente();
        utente.setUsername("marco_bellamico");
        utente.setRuolo("000001");
        request.getSession().setAttribute("utente", utente);
        request.addParameter("username", "marcobellamico");
        String message = "Errore: username non esistente";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testBan5() throws ServletException, IOException {
        Utente utente = new Utente();
        utente.setUsername("marco_bellamico");
        utente.setRuolo("000001");
        request.getSession().setAttribute("utente", utente);
        request.addParameter("username", "roberta_esposito");
        String message = "Ok: utente bannato";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }
}