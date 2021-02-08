package integration;

import control.gestioneUtente.AccessoServlet;
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

public class Test_AccessoServlet extends Mockito {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private AccessoServlet servlet;

    @BeforeEach
    public void setUp() throws SQLException, FileNotFoundException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection con = ConPool.getConnection();
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader("src/test/java/testmovietips.sql"));
        sr.runScript(reader);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new AccessoServlet();
    }

    @Test
    public void testAccesso1() throws ServletException, IOException {
        request.addParameter("mail", "francy");
        request.addParameter("password", "");
        String message = "Errore: password non corrispondente all’username";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testAccesso2() throws ServletException, IOException {
        request.addParameter("mail", "francesca.mauro@unisa.it");
        request.addParameter("password", "");
        String message = "Errore: password non corrispondente all’username";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testAccesso3() throws ServletException, IOException {
        request.addParameter("mail", "francesca.mauro@unisa.it");
        request.addParameter("password", "Francesca1!");
        String message = "Errore: e-mail bannata";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testAccesso4() throws ServletException, IOException {
        request.addParameter("mail", "roberta.esposito@unisa.it");
        request.addParameter("password", "Rob");
        String message = "Errore: password non corrispondente all’username";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testAccesso5() throws ServletException, IOException {
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