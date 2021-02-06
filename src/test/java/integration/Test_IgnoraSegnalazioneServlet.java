package integration;

import control.gestioneRecensione.IgnoraSegnalazioneServlet;
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

public class Test_IgnoraSegnalazioneServlet extends Mockito {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private IgnoraSegnalazioneServlet servlet;

    @BeforeEach
    public void setUp() throws SQLException, FileNotFoundException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection con = TestConPool.getConnection();
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader("src/test/java/testmovietips.sql"));
        sr.runScript(reader);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new IgnoraSegnalazioneServlet();
    }

    @Test
    public void testIgnoraSegnalazione1() throws ServletException, IOException {
        request.addParameter("idRecensione", "1");
        String message = "Errore: accesso non effettuato";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testIgnoraSegnalazione2() throws ServletException, IOException {
        request.addParameter("idRecensione", "1");
        Utente utente = new Utente();
        utente.setUsername("roberta_esposito");
        utente.setRuolo("010000");
        request.getSession().setAttribute("utente", utente);
        String message = "Errore: utente non ricopre il ruolo di moderatore";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testIgnoraSegnalazione3() throws ServletException, IOException {
        request.addParameter("idRecensione", "5");
        Utente utente = new Utente();
        utente.setUsername("marco_bellamico");
        utente.setRuolo("000001");
        request.getSession().setAttribute("utente", utente);
        String message = "Errore: recensione non presente nel database";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testIgnoraSegnalazione4() throws ServletException, IOException {
        request.addParameter("idRecensione", "1");
        Utente utente = new Utente();
        utente.setUsername("marco_bellamico");
        utente.setRuolo("000001");
        request.getSession().setAttribute("utente", utente);
        String message = "Ok: recensione ignorata";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }
}
