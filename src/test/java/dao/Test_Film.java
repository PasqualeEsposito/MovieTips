package dao;

import junit.framework.TestCase;
import model.film.Film;
import model.film.FilmDAO;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestPeriferica extends TestCase {
    private FilmDAO filmDAO;
    private Film filmEsistente;
    private Film filmNonEsistente;

    @BeforeEach
    protected void setUp() throws Exception {
        filmDAO = new FilmDAO();
        filmEsistente = new Film("TitoloTest", "ProduzioneTest", "MusicheTest", "FotografiaTest", "SceneggiaturaTest", "DistribuzioneTest", 120, "PaeseTest", "AttoriTest", "RegiaTest", "GenereTest", "TramaTest", 2010);
        perifericaNonEsistente = new PerifericaBean("mouse", "TestNonEsistente", 5, 21);

        dao.doDelete(perifericaNonEsistente.getNome());
        dao.doDelete(perifericaEsistente.getNome());
        dao.doSave(perifericaEsistente);
    }

    @Test
    public void testRicercaPerifericaEsistente() {
        assertEquals(perifericaEsistente.getNome(), dao.doRetrieveByKey(perifericaEsistente.getNome()).getNome());
    }

    @Test
    public void testRicercaPerifericaNonEsistente() {
        assertEquals("", dao.doRetrieveByKey(perifericaNonEsistente.getNome()).getNome());
    }

    @Test
    public void testListaPerifericheEsistenti() {
        ArrayList<PerifericaBean> collection = new ArrayList<PerifericaBean>();
        assertNotEquals(collection, dao.doRetrieveAll());
    }

    @After
    @Override
    protected void tearDown() throws Exception {
        dao.doDelete(perifericaNonEsistente.getNome());
        dao.doDelete(perifericaEsistente.getNome());
    }
}