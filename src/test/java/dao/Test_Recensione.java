package dao;

import model.film.Film;
import model.film.FilmDAO;
import model.recensione.Recensione;
import model.recensione.RecensioneDAO;
import model.utente.Utente;
import model.utente.UtenteDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class Test_Recensione {
    private UtenteDAO utenteDAO;
    private Utente utente;

    private RecensioneDAO recensioneDAO;
    private Recensione recensione;

    private Film film;
    private FilmDAO filmDAO;

    private int b;
    @BeforeEach
    void setUp() throws Exception {
        utenteDAO= new UtenteDAO();
        recensioneDAO = new RecensioneDAO();

        utente = new Utente("testUsername", "testNome","testCognome","TestEmail@test.it","TestPassword1!","M","2001-05-22","001000");
        utenteDAO.doDeleteByEmail(utente.getEmail());
        utenteDAO.doSave(utente);

        film=new Film("testFilm","testProduzione","testMusiche",
                "testFotografia", "testSceneggiatura","testDistribuzione",32,"testPaese",
                "testAttori","testRegia","M","testTrama",2020);
        filmDAO.doDeleteById(film.getIdFilm());
        filmDAO.doSave(film);

        recensione = new Recensione(233,"test test",utente.getUsername(),));
        recensioneDAO.doDeleteByIdRecensione(recensione.getIdRecensione());
        recensioneDAO.doSave(recensione);
    }

    @Test
    void testRicercaRecensioneTramiteUsername() {
        Recensione recensione = new Recensione();
        assertNotEquals(bean, dao.doRetrieveByKey(recensione.getUtenteEmail()));
    }

    @Test
    void testListaCompletaRecensioni() {
        ArrayList<RecensioneBean> collection = new ArrayList<RecensioneBean>();
        assertNotEquals(collection, dao.doRetrieveAll());
    }

    @Test
    void testInserimentoRecensione() {
        assertNotEquals(-1, b);
    }

    @Test
    void testAggiornamentoRecensione() {
        assertEquals(true, dao.doUpdate(recensione, beanTest.getEmail()));
    }

    @Test
    void testEliminazioneRecensione() {
        assertEquals(true, dao.doDelete(beanTest.getEmail()));
    }

    @Test
    void testApprovazioneRecensione() {
        assertEquals(true, dao.approva(beanTest.getEmail()));
    }

    @AfterEach
    void tearDown() throws Exception {
        dao.doDelete(beanTest.getEmail());
        daoTest.doDelete(beanTest.getEmail());

    }


}
