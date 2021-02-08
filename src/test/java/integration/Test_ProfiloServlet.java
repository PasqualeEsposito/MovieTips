package integration;

import control.gestioneUtente.ProfiloServlet;
import model.connection.ConPool;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.AfterEach;
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

public class Test_ProfiloServlet extends Mockito {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private ProfiloServlet servlet;

    @BeforeEach
    public void setUp() throws SQLException, FileNotFoundException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection con = ConPool.getConnection();
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader("src/test/java/testmovietips.sql"));
        sr.runScript(reader);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new ProfiloServlet();
    }

    @Test
    public void testProfiloUtente1() throws ServletException, IOException {
        request.addParameter("username", "Saverio");
        String message = "Errore: utente non esistente";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testProfiloUtente2() throws ServletException, IOException {
        request.addParameter("username", "fabrizio_ceriello");
        String message = "Ok: utente esistente";
        servlet.doGet(request, response);
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
