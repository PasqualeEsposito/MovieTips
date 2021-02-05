package integration;

import control.gestioneUtente.AccessoServlet;
import model.gestioneUtente.UtenteDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test_AccessoServlet extends Mockito {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private AccessoServlet servlet;
    private UtenteDAO utenteDAO = new UtenteDAO();

    @BeforeEach
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new AccessoServlet();
    }

    @Test
    public void testAccesso1() throws ServletException, IOException {
        request.addParameter("mail", "francy");
        request.addParameter("password", "");
        String message = "Errore: lunghezza e-mail";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testAccesso2() throws ServletException, IOException {
        request.addParameter("mail", "francy.mauro");
        request.addParameter("password", "");
        String message = "Errore: formato e-mail";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testAccesso3() throws ServletException, IOException {
        request.addParameter("mail", "francy.mauro@unisa.it");
        request.addParameter("password", "");
        String message = "Errore: e-mail non esistente";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testAccesso4() throws ServletException, IOException {
        request.addParameter("mail", "francesca.mauro@unisa.it");
        request.addParameter("password", "");
        String message = "Errore: e-mail bannata";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testAccesso5() throws ServletException, IOException {
        request.addParameter("mail", "roberta.esposito@unisa.it");
        request.addParameter("password", "Rob");
        String message = "Errore: lunghezza password";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testAccesso6() throws ServletException, IOException {
        request.addParameter("mail", "roberta.esposito@unisa.it");
        request.addParameter("password", "Roberta!");
        String message = "Errore: formato password";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testAccesso7() throws ServletException, IOException {
        request.addParameter("mail", "roberta.esposito@unisa.it");
        request.addParameter("password", "Roberta1");
        String message = "Errore: password non corrispondente all’username";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testAccesso8() throws ServletException, IOException {
        request.addParameter("mail", "roberta.esposito@unisa.it");
        request.addParameter("password", "Roberta1!");
        String message = "Ok: accesso effettuato";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @AfterEach
    public void tearDown() {
        servlet = null;
        request = null;
        response = null;
    }
}