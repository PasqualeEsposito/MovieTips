package dao;

import junit.framework.TestCase;
import model.film.Film;
import model.film.FilmDAO;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class Test_Film extends TestCase {
    private FilmDAO filmDAO;
    private Film filmEsistente;
    private Film filmNonEsistente;

    @BeforeEach
    protected void setUp() throws Exception {
        filmDAO = new FilmDAO();
        filmEsistente = new Film("Test", "ProduzioneTest", "MusicheTest", "FotografiaTest", "SceneggiaturaTest", "DistribuzioneTest", 120, "PaeseTest", "AttoriTest", "RegiaTest", "GenereTest", "TramaTest", 2010);
        filmNonEsistente = new Film("TestNonEsistente", "ProduzioneTest", "MusicheTest", "FotografiaTest", "SceneggiaturaTest", "DistribuzioneTest", 120, "PaeseTest", "AttoriTest", "RegiaTest", "GenereTest", "TramaTest", 2010);
        filmDAO.doSave(filmEsistente);
    }

    @Test
    public void testRicercaPerifericaEsistente() {
        assertEquals(filmEsistente.getIdFilm(), filmDAO.doRetrieveById(filmEsistente.getIdFilm()).getIdFilm());
    }

    @Test
    public void testRicercaPerifericaNonEsistente() {
        assertEquals("", filmDAO.doRetrieveById(filmNonEsistente.getIdFilm()).getTitolo());
    }

    @Test
    public void testListaPerifericheEsistenti() {
        ArrayList<Film> collection = new ArrayList<>();
        assertNotEquals(collection, filmDAO.doRetrieveAll());
    }

    @After
    @Override
    protected void tearDown() {
        filmDAO.doDeleteById(filmNonEsistente.getIdFilm());
        filmDAO.doDeleteById(filmEsistente.getIdFilm());
    }
}
