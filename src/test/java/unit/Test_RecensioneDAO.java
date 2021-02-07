package unit;

import junit.framework.TestCase;
import model.connection.ConPool;
import model.gestioneRecensione.RecensioneDAO;
import model.gestioneUtente.Utente;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test_RecensioneDAO extends TestCase {
    private RecensioneDAO recensioneDAO;

    @BeforeEach
    protected void setUp() throws SQLException, FileNotFoundException {
        recensioneDAO = new RecensioneDAO();
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection con = ConPool.getConnection();
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader("src/test/java/testmovietips.sql"));
        sr.runScript(reader);
    }

    @Test
    public void testAggiungiRecensione1() {
        assertEquals(-1, recensioneDAO.addReview(5, "Test", 1, null));  // L’utente non ha effettuato l’accesso
    }

    @Test
    public void testAggiungiRecensione2() {
        Utente utente = new Utente();
        utente.setUsername("roberta_esposito");
        utente.setRuolo("010000");
        assertEquals(-2, recensioneDAO.addReview(5, "Test", 1, utente));  // L’utente non ricopre il ruolo di filmino
    }

    @Test
    public void testAggiungiRecensione3() {
        Utente utente = new Utente();
        utente.setUsername("fabrizio_ceriello");
        utente.setRuolo("001000");
        assertEquals(-3, recensioneDAO.addReview(0, "Test", 1, utente));  // valutazione < 1 or valutazione > 5
    }

    @Test
    public void testAggiungiRecensione4() {
        Utente utente = new Utente();
        utente.setUsername("fabrizio_ceriello");
        utente.setRuolo("001000");
        assertEquals(-4, recensioneDAO.addReview(5, "", 1, utente));  // LT < 1 or LT > 255
    }

    @Test
    public void testAggiungiRecensione5() {
        Utente utente = new Utente();
        utente.setUsername("fabrizio_ceriello");
        utente.setRuolo("001000");
        assertEquals(1, recensioneDAO.addReview(5, "Test", 1, utente));  // Ok: recensione effettuata
    }

    @Test
    public void testEliminaRecensione1() {
        assertEquals(-1, recensioneDAO.deleteReview(1, null)); // L’utente non ha effettuato l’accesso
    }

    @Test
    public void testEliminaRecensione2() {
        Utente utente = new Utente();
        utente.setUsername("fabrizio_ceriello");
        assertEquals(-2, recensioneDAO.deleteReview(3, utente)); // La recensione non è associata all’username dell’utente
    }

    @Test
    public void testEliminaRecensione3() {
        Utente utente = new Utente();
        utente.setUsername("fabrizio_ceriello");
        assertEquals(1, recensioneDAO.deleteReview(1, utente)); // Ok: recensione eliminata
    }

    @Test
    public void testIgnoraSegnalazione1() {
        assertEquals(-1, recensioneDAO.ignoreReporting(2, null)); // L’utente non ha effettuato l’accesso
    }

    @Test
    public void testIgnoraSegnalazione2() {
        Utente utente = new Utente();
        utente.setUsername("roberta_esposito");
        utente.setRuolo("010000");
        assertEquals(-2, recensioneDAO.ignoreReporting(2, utente)); // L’utente non ricopre il ruolo di moderatore
    }

    @Test
    public void testIgnoraSegnalazione3() {
        Utente utente = new Utente();
        utente.setUsername("marco_bellamico");
        utente.setRuolo("000001");
        assertEquals(-3, recensioneDAO.ignoreReporting(5, utente)); // La recensione non è presente nel database
    }

    @Test
    public void testIgnoraSegnalazione4() {
        Utente utente = new Utente();
        utente.setUsername("marco_bellamico");
        utente.setRuolo("000001");
        assertEquals(1, recensioneDAO.ignoreReporting(2, utente)); // Ok: segnalazione ignorata
    }

    @Test
    public void testModeraRecensione1() {
        assertEquals(-1, recensioneDAO.moderateReview(1, null)); // L’utente non ha effettuato l’accesso
    }

    @Test
    public void testModeraRecensione2() {
        Utente utente = new Utente();
        utente.setUsername("roberta_esposito");
        utente.setRuolo("010000");
        assertEquals(-2, recensioneDAO.moderateReview(1, utente)); // L’utente non ricopre il ruolo di moderatore
    }

    @Test
    public void testModeraRecensione3() {
        Utente utente = new Utente();
        utente.setUsername("marco_bellamico");
        utente.setRuolo("000001");
        assertEquals(-3, recensioneDAO.moderateReview(5, utente)); // La recensione non è presente nel database
    }

    @Test
    public void testModeraRecensione4() {
        Utente utente = new Utente();
        utente.setUsername("marco_bellamico");
        utente.setRuolo("000001");
        assertEquals(1, recensioneDAO.moderateReview(1, utente)); // Ok: moderazione recensione effettuata
    }

    @Test
    public void testSegnalaRecensione1() {
        assertEquals(-1, recensioneDAO.reportReview(1, null)); // L’utente non ha effettuato l’accesso
    }

    @Test
    public void testSegnalaRecensione2() {
        Utente utente = new Utente();
        utente.setUsername("roberta_esposito");
        utente.setRuolo("010000");
        assertEquals(-2, recensioneDAO.reportReview(1, utente)); // L’utente non ha attivato l’account
    }

    @Test
    public void testSegnalaRecensione3() {
        Utente utente = new Utente();
        utente.setUsername("fabrizio_ceriello");
        utente.setRuolo("001000");
        assertEquals(1, recensioneDAO.reportReview(1, utente)); // Ok: recensione segnalata
    }

    @AfterEach
    public void tearDown() {
        recensioneDAO = null;
    }
}
