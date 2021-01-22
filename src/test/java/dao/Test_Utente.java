package dao;

import junit.framework.TestCase;
import model.utente.UtenteDAO;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class Test_Utente extends TestCase {
    private UtenteDAO utenteDAO;

    @BeforeEach
    protected void setUp() {
        utenteDAO = new UtenteDAO();
        utenteDAO.doDeleteByUsername("frank");
        utenteDAO.doDeleteByUsername("ghost");
        utenteDAO.doSave("frank", "francesco@unisa.it", "Francesco1!", "Francesco", "Ceriello", "Uomo", "1985-12-10", "100000");
    }

    @Test
    public void testRecuperoUtenteEsistente() {
        assertEquals("frank", utenteDAO.doRetrieveByUsername("frank").getUsername());
    }

    @Test
    public void testRecuperoUtenteNonEsistente() {
        assertEquals(null, utenteDAO.doRetrieveByUsername("ghosts"));
    }

    @Test
    public void testRecuperoUtenteEsistenteByEmail() {
        assertEquals("frank", utenteDAO.doRetrieveByMail("francesco@unisa.it").getUsername());
    }

    @Test
    public void testRecuperoUtenteNonEsistenteByEmail() {
        assertEquals(null, utenteDAO.doRetrieveByMail("ghosts@unisa.it"));
    }

    @Test
    public void testRecuperoUtenteEsistenteByEmailPassword() {
        assertEquals("frank", utenteDAO.doRetrieveByMailPassword("francesco@unisa.it", "Francesco1!").getUsername());
    }

    @Test
    public void testRecuperoUtenteNonEsistenteByEmailPassword() {
        assertEquals(null, utenteDAO.doRetrieveByMailPassword("ghosts@unisa.it", "Ghosts1!"));
    }

    @Test
    public void testUpdateRuoloEsistente() {
        assertEquals(true, utenteDAO.doUpdateUtente("frank", "001001"));
    }

    @Test
    public void testUpdateRuoloNonEsistente() {
        assertEquals(false, utenteDAO.doUpdateUtente("ghost", "001001"));
    }

    @AfterEach
    protected void tearDown() {
        utenteDAO.doDeleteByUsername("frank");
    }
}