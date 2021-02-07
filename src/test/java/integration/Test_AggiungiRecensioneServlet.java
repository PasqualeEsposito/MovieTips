package integration;

import control.gestioneRecensione.AggiungiRecensioneServlet;
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

public class Test_AggiungiRecensioneServlet extends Mockito {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private AggiungiRecensioneServlet servlet;

    @BeforeEach
    public void setUp() throws SQLException, FileNotFoundException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection con = ConPool.getConnection();
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader("src/test/java/testmovietips.sql"));
        sr.runScript(reader);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new AggiungiRecensioneServlet();
    }

    @Test
    public void testAggiungiRecensione1() throws ServletException, IOException {
        request.addParameter("idFilm", "1");
        request.addParameter("valutazione", "5");
        request.addParameter("testo", "Test");
        String message = "Errore: accesso non effettuato";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testAggiungiRecensione2() throws ServletException, IOException {
        request.addParameter("idFilm", "1");
        request.addParameter("valutazione", "5");
        request.addParameter("testo", "Test");
        Utente utente = new Utente();
        utente.setRuolo("010000");
        utente.setUsername("roberta_esposito");
        request.getSession().setAttribute("utente", utente);
        String message = "Errore: utente non ricopre il ruolo di filmino";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testAggiungiRecensione3() throws ServletException, IOException {
        request.addParameter("idFilm", "1");
        request.addParameter("valutazione", "0");
        request.addParameter("testo", "Test");
        Utente utente = new Utente();
        utente.setRuolo("001000");
        utente.setUsername("fabrizio_ceriello");
        request.getSession().setAttribute("utente", utente);
        String message = "Errore: range valutazione";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testAggiungiRecensione4() throws ServletException, IOException {
        request.addParameter("idFilm", "1");
        request.addParameter("valutazione", "5");
        request.addParameter("testo", "");
        Utente utente = new Utente();
        utente.setRuolo("001000");
        utente.setUsername("fabrizio_ceriello");
        request.getSession().setAttribute("utente", utente);
        String message = "Errore: lunghezza testo";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testAggiungiRecensione5() throws ServletException, IOException {
        request.addParameter("idFilm", "1");
        request.addParameter("valutazione", "5");
        request.addParameter("testo", "Test");
        Utente utente = new Utente();
        utente.setRuolo("001000");
        utente.setUsername("fabrizio_ceriello");
        request.getSession().setAttribute("utente", utente);
        String message = "Ok: recensione effettuata";
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
