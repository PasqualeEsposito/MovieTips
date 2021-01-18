import control.LoginServlet;
import model.utente.Utente;
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

public class TestCase_LoginUtente extends Mockito {
    private LoginServlet servlet;
    private UtenteDAO dao = new UtenteDAO();
    private Utente franco = new Utente("franco.ceriello", "Franco", "Ceriello",
            "franco.ceriello@unisa.it", "Franco1!", "m", "1999-12-30", "001000");
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    public void setUp() {
        dao.doDeleteByEmail(franco.getEmail());
        dao.doSave(franco);
        servlet = new LoginServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void TC_LoginUtente_1() throws ServletException, IOException {
        request.addParameter("email", "");
        request.addParameter("password", "Franco1!");
        String message = "Il login non va a buon fine poiché il campo email è vuoto";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_LoginUtente_2() throws ServletException, IOException {
        request.addParameter("email", "franco@unisa.it");
        request.addParameter("password", "Franco1!");
        String message = "Il login non va a buon fine poiché l'email non è presente nel db";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_LoginUtente_3() throws ServletException, IOException {
        request.addParameter("email", "franco.ceriello@unisa.it");
        request.addParameter("password", "");
        String message = "Il login non va a buon fine poiché il campo password è vuoto";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_LoginUtente_4() throws ServletException, IOException {
        request.addParameter("email", "franco.ceriello@unisa.it");
        request.addParameter("password", "Franco1?");
        String message = "Il login non va a buon fine poiché l'email e la password non combaciano";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }


    @Test
    public void TC_LoginUtente_5() throws ServletException, IOException {
        request.addParameter("email", "franco.ceriello@unisa.it");
        request.addParameter("password", "Franco1!");
        String message = "Il login viene effettuato correttamente";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @AfterEach
    public void tearDown() {
        dao.doDeleteByEmail(franco.getEmail());
        servlet = null;
        request = null;
        response = null;
    }
}
