package servlet;

import control.gestioneUtente.LoginServlet;
import model.utente.UtenteDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TC_Login extends Mockito {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private LoginServlet servlet;
    private UtenteDAO utenteDAO = new UtenteDAO();

    @BeforeEach
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new LoginServlet();
        utenteDAO.doDeleteByUsername("frank");

        utenteDAO.doSave("frank", "francesco@unisa.it", "Francesco1!", "Francesco", "Ceriello", "Uomo", "1985-12-10", "001000");    }

    @Test
    public void TC_Login1() throws ServletException, IOException {
        request.addParameter("mail", "fra.it");
        request.addParameter("password", "");
        String message = "LE_FAIL";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_Login2() throws ServletException, IOException {
        request.addParameter("mail", "francesco.it");
        request.addParameter("password", "");
        String message = "FE_FAIL";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_Login3() throws ServletException, IOException {
        request.addParameter("mail", "franco@unisa.it");
        request.addParameter("password", "");
        String message = "EE_FAIL";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_Login4() throws ServletException, IOException {
        request.addParameter("mail", "francesco@unisa.it");
        request.addParameter("password", "fra");
        String message = "LP_FAIL";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_Login5() throws ServletException, IOException {
        request.addParameter("mail", "francesco@unisa.it");
        request.addParameter("password", "Francesco");
        String message = "FP_FAIL";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_Login6() throws ServletException, IOException {
        request.addParameter("mail", "francesco@unisa.it");
        request.addParameter("password", "Francesco1");
        String message = "CP_FAIL";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_Login7() throws ServletException, IOException {
        request.addParameter("mail", "francesco@unisa.it");
        request.addParameter("password", "Francesco1!");
        String message = "OK";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @AfterEach
    public void tearDown() {
        utenteDAO.doDeleteByUsername("frank");
        servlet = null;
        request = null;
        response = null;
    }
}