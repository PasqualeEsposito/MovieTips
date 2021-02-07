package integration;

import control.gestioneRecensione.ModeraRecensioneServlet;
import model.connection.ConPool;
import model.gestioneUtente.Utente;
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


public class Test_ModeraRecensioneServlet extends Mockito {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private ModeraRecensioneServlet servlet;

    @BeforeEach
    public void setUp() throws SQLException, FileNotFoundException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection con = ConPool.getConnection();
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader("src/test/java/testmovietips.sql"));
        sr.runScript(reader);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new ModeraRecensioneServlet();
    }

    @Test
    public void testModeraRecensione1() throws ServletException, IOException {
        request.addParameter("idRecensione", "1");
        String message = "Errore: accesso non effettuato";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testModeraRecensione2() throws ServletException, IOException {
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
    public void testModeraRecensione3() throws ServletException, IOException {
        request.addParameter("idRecensione", "5");
        Utente utente = new Utente();
        utente.setUsername("marco_bellamico");
        utente.setRuolo("000001");
        request.getSession().setAttribute("utente", utente);
        String message = "Errore: recensione non esistente";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testModeraRecensione4() throws ServletException, IOException {
        request.addParameter("idRecensione", "1");
        Utente utente = new Utente();
        utente.setUsername("marco_bellamico");
        utente.setRuolo("000001");
        request.getSession().setAttribute("utente", utente);
        String message = "Ok: moderazione recensione effettuata";
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
