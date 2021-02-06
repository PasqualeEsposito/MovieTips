package integration;

import control.gestioneRecensione.EliminaRecensioneServlet;
import model.connection.TestConPool;
import model.gestioneUtente.Utente;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test_EliminaRecensioneServlet extends Mockito {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private EliminaRecensioneServlet servlet;

    @BeforeEach
    public void setUp() throws SQLException, FileNotFoundException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection con = TestConPool.getConnection();
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader("src/test/java/testmovietips.sql"));
        sr.runScript(reader);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new EliminaRecensioneServlet();
    }

    @Test
    public void testEliminaRecensione1() throws ServletException, IOException {
        request.addParameter("idRecensione", "1");
        String message = "Errore: accesso non effettuato";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testEliminaRecensione2() throws ServletException, IOException {
        request.addParameter("idRecensione", "3");
        Utente utente = new Utente();
        utente.setUsername("fabrizio_ceriello");
        request.getSession().setAttribute("utente", utente);
        String message = "Errore: utente non può eliminare la recensione di un altro utente";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testEliminaRecensione3() throws ServletException, IOException {
        request.addParameter("idRecensione", "1");
        Utente utente = new Utente();
        utente.setUsername("fabrizio_ceriello");
        request.getSession().setAttribute("utente", utente);
        String message = "Ok: recensione eliminata";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }
}
