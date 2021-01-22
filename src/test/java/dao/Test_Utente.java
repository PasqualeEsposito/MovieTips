package dao;

import junit.framework.TestCase;
import model.utente.Utente;
import model.utente.UtenteDAO;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class Test_Utente extends TestCase {
    private UtenteDAO utenteDAO;
    private Utente utenteEsistente;
    private Utente utenteNonEsistente;

    @BeforeEach
    protected void setUp() throws Exception {
        utenteDAO = new UtenteDAO();
        utenteEsistente = new Utente("testEsistente", "test", "esistente", "test@esistente.it", "TestEsistente1!", "M", "1999-01-01", "001000");
        utenteNonEsistente = new Utente("testNonEsistente", "test", "nonEsistente", "test@nonesistente.it", "TestNonEsistente1!", "F", "1999-01-01", "001000");
        utenteDAO.doSave("testEsistente", "test", "esistente", "test@esistente.it", "TestEsistente1!", "M", "1999-01-01", "001000");
    }

    @Test
    public void testRicercaUtenteEsistente() {
        assertEquals(utenteEsistente, utenteDAO.doRetrieveByUsername(utenteEsistente.getUsername()).getUsername());
    }

    @Test
    public void testRicercaUtenteNonEsistente() {
        assertEquals(null, utenteDAO.doRetrieveByUsername(utenteNonEsistente.getUsername()).getUsername());
    }

    @Test
    public void testRicercaUtenteEsistenteByEmail() {
        assertEquals(utenteEsistente, utenteDAO.doRetrieveByMail(utenteEsistente.getMail()).getMail());
    }

    @Test
    public void testRicercaUtenteNonEsistenteByEmail() {
        assertEquals(null, utenteDAO.doRetrieveByMail(utenteNonEsistente.getMail()));
    }

    @Test
    public void testRicercaUtenteEsistenteByEmailPassword() {
        assertEquals(utenteEsistente, utenteDAO.doRetrieveByMailPassword(utenteEsistente.getMail(), utenteEsistente.getPassword()));
    }

    @Test
    public void testRicercaUtenteNonEsistenteByEmailPassword() {
        assertEquals(null, utenteDAO.doRetrieveByMailPassword(utenteNonEsistente.getMail(), utenteNonEsistente.getPassword()));
    }

    @Test
    public void testUpdateRuoloEsistente() {
        assertEquals(true, utenteDAO.doUpdateUtente(utenteEsistente.getUsername(), "000100"));
    }

    @Test
    public void testUpdateRuoloNonEsistente() {
        assertEquals(false, utenteDAO.doUpdateUtente(utenteNonEsistente.getUsername(), "000100"));
    }


    @AfterEach
    protected void tearDown() {
        utenteDAO.doDeleteByUsername(utenteEsistente.getUsername());
        utenteDAO.doDeleteByUsername(utenteNonEsistente.getUsername());
    }
}
