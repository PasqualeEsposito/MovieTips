package unit;

import junit.framework.TestCase;
import model.gestioneUtente.Utente;
import model.gestioneUtente.UtenteDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Test_UtenteDAO extends TestCase {
    private UtenteDAO utenteDAO;

    @Before
    protected void setUp() {
        utenteDAO = new UtenteDAO();
    }

    @Test
    public void testAccesso1() {
        assertEquals(-1, utenteDAO.doRetrieveByMailPassword(new Utente(), "francy", "")); // LE < 8 or LE > 255
    }

    @Test
    public void testAccesso2() {
        assertEquals(-2, utenteDAO.doRetrieveByMailPassword(new Utente(), "francy.mauro", "")); // Non rispetta il formato
    }

    @Test
    public void testAccesso3() {
        assertEquals(-3, utenteDAO.doRetrieveByMailPassword(new Utente(), "francy.mauro@unisa.it", "")); // Non esiste nel database
    }

    @Test
    public void testAccesso4() {
        assertEquals(-4, utenteDAO.doRetrieveByMailPassword(new Utente(), "francesca.mauro@unisa.it", "")); // isBanned
    }

    @Test
    public void testAccesso5() {
        assertEquals(-5, utenteDAO.doRetrieveByMailPassword(new Utente(), "roberta.esposito@unisa.it", "Rob")); // LP < 8 or LP > 255
    }

    @Test
    public void testAccesso6() {
        assertEquals(-6, utenteDAO.doRetrieveByMailPassword(new Utente(), "roberta.esposito@unisa.it", "Roberta!")); // Non rispetta il formato
    }

    @Test
    public void testAccesso7() {
        assertEquals(-7, utenteDAO.doRetrieveByMailPassword(new Utente(), "roberta.esposito@unisa.it", "Roberta1")); // Non corrisponde la password
    }

    @Test
    public void testAccesso8() {
        assertEquals(1, utenteDAO.doRetrieveByMailPassword(new Utente(), "roberta.esposito@unisa.it", "Roberta1!")); // OK
    }

    @After
    protected void tearDown() {
    }
}