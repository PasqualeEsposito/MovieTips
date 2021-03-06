package unit;

import junit.framework.TestCase;
import model.connection.ConPool;
import model.gestioneUtente.Utente;
import model.gestioneUtente.UtenteDAO;
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

public class Test_UtenteDAO extends TestCase {
    private UtenteDAO utenteDAO;

    @BeforeEach
    protected void setUp() throws SQLException, FileNotFoundException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection con = ConPool.getConnection();
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader("src/test/java/testmovietips.sql"));
        sr.runScript(reader);
        utenteDAO = new UtenteDAO();
    }

    @Test
    public void testAccesso1() {
        assertEquals(-1, utenteDAO.signIn("francy", "", new Utente())); // La password non corrisponde all’username
    }

    @Test
    public void testAccesso2() {
        assertEquals(-1, utenteDAO.signIn("francesca.mauro@unisa.it", "", new Utente())); // La password non corrisponde all’username
    }

    @Test
    public void testAccesso3() {
        assertEquals(-2, utenteDAO.signIn("francesca.mauro@unisa.it", "Francesca1!", new Utente())); // L'e-mail è stata bannata
    }

    @Test
    public void testAccesso4() {
        assertEquals(-1, utenteDAO.signIn("roberta.esposito@unisa.it", "Rob", new Utente())); // La password non corrisponde all’username
    }

    @Test
    public void testAccesso5() {
        assertEquals(1, utenteDAO.signIn("roberta.esposito@unisa.it", "Roberta1!", new Utente())); // Ok: accesso effettuato
    }

    @Test
    public void testBan1() {
        assertEquals(-1, utenteDAO.banUser("roberta_esposito", null));  // L’utente non ha effettuato l’accesso
    }

    @Test
    public void testBan2() {
        Utente utente = new Utente();
        utente.setUsername("fabrizio_ceriello");
        utente.setRuolo("001000");
        assertEquals(-2, utenteDAO.banUser("roberta_esposito", utente));  // L’utente non ricopre il ruolo di moderatore
    }

    @Test
    public void testBan3() {
        Utente utente = new Utente();
        utente.setUsername("marco_bellamico");
        utente.setRuolo("000001");
        assertEquals(-3, utenteDAO.banUser("marco_bellamico", utente));  // L'username corrisponde all’username dell’utente
    }

    @Test
    public void testBan4() {
        Utente utente = new Utente();
        utente.setUsername("marco_bellamico");
        utente.setRuolo("000001");
        assertEquals(-4, utenteDAO.banUser("robertaesposito", utente));  // L’username non esiste nel database
    }

    @Test
    public void testBan5() {
        Utente utente = new Utente();
        utente.setUsername("marco_bellamico");
        utente.setRuolo("000001");
        assertEquals(1, utenteDAO.banUser("roberta_esposito", utente));  // Ok: utente bannato
    }

    @Test
    public void testVisualizzazioneProfiloUtente1() {
        String username = "Saverio";
        assertEquals(null, utenteDAO.getUser(username)); // L’username non è presente nel database
    }

    @Test
    public void testVisualizzazioneProfiloUtente2() {
        String username = "fabrizio_ceriello";
        Utente utente1 = new Utente("fabrizio_ceriello", "fabrizio.ceriello@unisa.it", "Fabrizio", "Ceriello", "Uomo", "1999-12-30", "001000");
        Utente utente2 = utenteDAO.getUser(username);
        int flag = 1;
        if (!(utente1.getUsername().equals(utente2.getUsername())
                && utente1.getMail().equals(utente2.getMail())
                && utente1.getNome().equals(utente2.getNome())
                && utente1.getCognome().equals(utente2.getCognome())
                && utente1.getGenere().equals(utente2.getGenere())
                && utente1.getDataNascita().equals(utente2.getDataNascita())
                && utente1.getRuolo().equals(utente2.getRuolo())))
            flag = 0;
        assertEquals(1, flag);  // Ok: profilo utente visualizzato
    }

    @AfterEach
    public void tearDown() {
        utenteDAO = null;
    }
}