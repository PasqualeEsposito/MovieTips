package integration;

import control.gestioneRecensione.GestioneSegnalazioniServlet;
import model.connection.ConPool;
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

public class Test_GestioneSegnalazioniServlet extends Mockito {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private GestioneSegnalazioniServlet servlet;

    @BeforeEach
    public void setUp() throws SQLException, FileNotFoundException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection con = ConPool.getConnection();
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader("src/test/java/testmovietips.sql"));
        sr.runScript(reader);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new GestioneSegnalazioniServlet();
    }

    @Test
    public void testVisualizzaSegnalazioni1() throws ServletException, IOException {
        String message = "Errore: accesso non effettuato";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testVisualizzaSegnalazioni2() throws ServletException, IOException {
        Utente utente = new Utente("fabrizio_ceriello", "fabrizio.ceriello@unisa.it",
                "Fabrizio", "Ceriello", "Uomo", "1999-12-30", "001000");
        request.getSession().setAttribute("utente", utente);
        String message = "Errore: utente non riveste il ruolo di moderatore";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testVisualizzaSegnalazioni3() throws ServletException, IOException {
        Utente utente = new Utente("marco_bellamico", "marco.bellamico@unisa.it",
                "Marco", "Bellamico", "Uomo", "1990-03-01", "000001");
        request.getSession().setAttribute("utente", utente);
        String message = "Ok: pagina segnalazioni visualizzata";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }
}
