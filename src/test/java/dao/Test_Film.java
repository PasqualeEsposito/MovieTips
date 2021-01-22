package dao;

import junit.framework.TestCase;
import model.film.Film;
import model.film.FilmDAO;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class Test_Film extends TestCase {
    private FilmDAO filmDAO;
    private Film filmNonEsistente;
    private Film filmEsistente;


    @BeforeEach
    protected void setUp() throws Exception {
        filmDAO = new FilmDAO();
        filmNonEsistente = new Film("TestNonEsistente", "GenereTest", 2010, "RegiaTest", "AttoriTest", "PaeseTest", 120, "DistribuzioneTest", "SceneggiaturaTest", "FotografiaTest", "MusicheTest", "ProduzioneTest", "TramaTest");
        filmEsistente = new Film("TestEsistente", "GenereTest", 2010, "RegiaTest", "AttoriTest", "PaeseTest", 120, "DistribuzioneTest", "SceneggiaturaTest", "FotografiaTest", "MusicheTest", "ProduzioneTest", "TramaTest");
        filmDAO.doDeleteByTitoloAnnoRegia(filmNonEsistente.getTitolo(), filmNonEsistente.getAnno(), filmNonEsistente.getRegia());
        filmDAO.doDeleteByTitoloAnnoRegia(filmEsistente.getTitolo(), filmEsistente.getAnno(), filmEsistente.getRegia());
        int idFilmEsistente = filmDAO.doSave(filmEsistente.getTitolo(), filmEsistente.getGenere(), filmEsistente.getAnno(), filmEsistente.getRegia(), filmEsistente.getAttori(), filmEsistente.getPaese(), filmEsistente.getDurata(), filmEsistente.getDistribuzione(), filmEsistente.getSceneggiatura(), filmEsistente.getFotografia(), filmEsistente.getMusiche(), filmEsistente.getProduzione(), filmEsistente.getTrama());
        filmEsistente.setIdFilm(idFilmEsistente);
    }

    @Test
    public void testEsistenzaFilmEsistente() {
        assertEquals(filmEsistente.getIdFilm(), filmDAO.doRetrieveByTitoloAnnoRegia(filmEsistente.getTitolo(), filmEsistente.getAnno(), filmEsistente.getRegia()).getIdFilm());
    }

    @Test
    public void testEsistenzaFilmNonEsistente() {
        assertEquals(null, filmDAO.doRetrieveByTitoloAnnoRegia(filmNonEsistente.getTitolo(), filmNonEsistente.getAnno(), filmNonEsistente.getRegia()));
    }

    @Test
    public void testListaFilm() {
        ArrayList<Film> collection = new ArrayList<>();
        assertNotEquals(collection, filmDAO.doRetrieveAll(10));
    }

    @AfterEach
    protected void tearDown() {
        filmDAO.doDeleteByTitoloAnnoRegia(filmNonEsistente.getTitolo(), filmNonEsistente.getAnno(), filmNonEsistente.getRegia());
        filmDAO.doDeleteByTitoloAnnoRegia(filmEsistente.getTitolo(), filmEsistente.getAnno(), filmEsistente.getRegia());
    }
}