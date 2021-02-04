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
    public void TC_Accesso1() throws ServletException, IOException {
        request.addParameter("mail", "francy");
        request.addParameter("password", "");
        String message = "LE_FAIL";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_Accesso2() throws ServletException, IOException {
        request.addParameter("mail", "francy.mauro");
        request.addParameter("password", "");
        String message = "FE_FAIL";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_Accesso3() throws ServletException, IOException {
        request.addParameter("mail", "francy.mauro@unisa.it");
        request.addParameter("password", "");
        String message = "EE_FAIL";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_Accesso4() throws ServletException, IOException {
        request.addParameter("mail", "francesca.mauro@unisa.it");
        request.addParameter("password", "");
        String message = "EB_FAIL";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_Accesso5() throws ServletException, IOException {
        request.addParameter("mail", "roberta.esposito@unisa.it");
        request.addParameter("password", "Rob");
        String message = "LP_FAIL";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_Accesso6() throws ServletException, IOException {
        request.addParameter("mail", "roberta.esposito@unisa.it");
        request.addParameter("password", "Roberta!");
        String message = "FP_FAIL";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_Accesso7() throws ServletException, IOException {
        request.addParameter("mail", "roberta.esposito@unisa.it");
        request.addParameter("password", "Roberta1");
        String message = "CP_FAIL";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_Accesso8() throws ServletException, IOException {
        request.addParameter("mail", "roberta.esposito@unisa.it");
        request.addParameter("password", "Roberta1!");
        String message = "OK";
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