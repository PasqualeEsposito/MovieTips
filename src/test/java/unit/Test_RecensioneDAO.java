package unit;

import junit.framework.TestCase;
import model.connection.ConPool;
import model.gestioneRecensione.RecensioneDAO;
import model.gestioneUtente.Utente;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Test;
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
        Reader reader = new BufferedReader(new FileReader("src/test/java/movietips.sql"));
        sr.runScript(reader);
    }

    @Test
    public void testAggiungiValutazione1() {
        //Utente null?
        assertEquals(-1, recensioneDAO.addReview(5, "Test", null, 1));  // L’utente non ha effettuato l’accesso
    }

    @Test
    public void testAggiungiValutazione2() {
        Utente utente = new Utente();
        utente.setRuolo("000100");
        utente.setUsername("marco_bellamico");
        assertEquals(-2, recensioneDAO.addReview(5, "Test", utente, 1));  // L’utente non ricopre il ruolo di filmino
    }

    @Test
    public void testAggiungiValutazione3() {
        Utente utente = new Utente();
        utente.setRuolo("001000");
        utente.setUsername("fabrizio_ceriello");
        assertEquals(-3, recensioneDAO.addReview(0, "Test", utente, 1));  // valutazione < 1 or valutazione > 5
    }

    @Test
    public void testAggiungiValutazione4() {
        Utente utente = new Utente();
        utente.setRuolo("001000"); // lunghezza testo > 255 (La lunghezza massima del testo è stata ridotta a 255 per facilitare il testing)
        utente.setUsername("fabrizio_ceriello");
        assertEquals(-4, recensioneDAO.addReview(5, "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam eaque ipsa, quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt, explicabo. Nemo enim ipsam voluptatem, quia voluptas sit, aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos, qui ratione voluptatem sequi nesciunt, neque porro quisquam est, qui dolorem ipsum, quia dolor sit, amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt, ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit, qui in ea voluptate velit esse, quam nihil molestiae consequatur, vel illum, qui dolorem eum fugiat, quo voluptas nulla pariatur? [33] At vero eos et accusamus et iusto odio dignissimos ducimus, qui blanditiis praesentium voluptatum deleniti atque corrupti, quos dolores et quas molestias excepturi sint, obcaecati cupiditate non provident, similique sunt in culpa, qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio, cumque nihil impedit, quo minus id, quod maxime placeat, facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet, ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.", utente, 1));  // valutazione < 1 or valutazione > 5
    }

    @Test
    public void testAggiungiValutazione5() {
        Utente utente = new Utente();
        utente.setUsername("fabrizio_ceriello");
        utente.setRuolo("001000");
        assertEquals(1, recensioneDAO.addReview(5, "Test", utente, 1));  // valutazione < 1 or valutazione > 5
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
        assertEquals(1, recensioneDAO.deleteReview(1, utente)); // OK
    }

    @Test
    public void testSegnalaRecensione1() {
        assertEquals(-1, recensioneDAO.deleteReview(1, null)); // L’utente non ha effettuato l’accesso
    }

    @Test
    public void testSegnalaRecensione2() {
        Utente utente = new Utente();
        utente.setUsername("roberta_esposito");
        assertEquals(-2, recensioneDAO.deleteReview(1, utente)); // L’utente non ha attivato l’account
    }

    @Test
    public void testSegnalaRecensione3() {
        Utente utente = new Utente();
        utente.setUsername("fabrizio_ceriello");
        assertEquals(1, recensioneDAO.deleteReview(1, utente)); // OK
    }
}
