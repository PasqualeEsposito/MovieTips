package dao;

import model.film.Film;
import model.film.FilmDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestFilm {
    private FilmDAO filmDAO;
    private Film film;


    @BeforeEach
    void setUp() throws Exception {
        filmDAO = new FilmDAO();
        film = new Film("Film", "Test", "test", "test", "test", "test", 150, "test", "test", "test", "test", "test", 2000);
        film.setIdFilm(0);
        // INSERT INTO `movietipsdb`.`film` (`id_film`, `titolo`, `produzione`, `musiche`, `fotografia`, `sceneggiatura`, `distribuzione`, `durata`, `paese`, `attori`, `regia`, `genere`, `trama`, `anno`) VALUES ('0', 'Test', ' ', ' ', ' ', ' ', ' ', '120', ' ', ' ', ' ', ' ', ' ', ' ')
    }

    @Test
    public void testRicercaPerifericaEsistente() {
        assertEquals(film.getIdFilm(), filmDAO.doRetrieveById(film.getIdFilm()).getIdFilm());
    }

    @Test
    public void testRicercaPerifericaNonEsistente() {
        assertEquals("", dao.doRetrieveByKey(perifericaNonEsistente.getNome()).getNome());
    }

    /*@Test
    public void testInserimentoPerifericaEsistente() {
        assertEquals(-1, dao.doSave(perifericaEsistente));
    }
    @Test
    public void testInserimentoPerifericaNonEsistente() {
        assertEquals(0, dao.doSave(perifericaNonEsistente));
    }
    @Test
    public void testModificaPerifericaEsistente() {
        assertEquals(true,dao.doUpdate(perifericaEsistente, perifericaEsistente.getNome()));
    }
    @Test
    public void testListaPerifericheEsistentiDivisePerTipi()  {
        ArrayList<PerifericaBean> collection = new ArrayList<PerifericaBean>();
        assertNotEquals(collection, dao.doRetrieveAllTipi());
    }
    @Test
    public void testListaPerifericheEsistenti() {
        ArrayList<PerifericaBean> collection = new ArrayList<PerifericaBean>();
        assertNotEquals(collection, dao.doRetrieveAll());
    }
*/
}