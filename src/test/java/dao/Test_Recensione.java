package dao;

import junit.framework.TestCase;
import model.film.Film;
import model.film.FilmDAO;
import model.recensione.Recensione;
import model.recensione.RecensioneDAO;
import model.utente.Utente;
import model.utente.UtenteDAO;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class Test_Recensione extends TestCase{
    private FilmDAO filmDAO;
    private Film film;
    private UtenteDAO utenteDAO;
    private RecensioneDAO recensioneDAO;
    private Recensione recensione;
    private Utente utente;

    @BeforeEach
    protected void setUp() throws Exception {
        utenteDAO=new UtenteDAO();
        utenteDAO.doDeleteByUsername("frank");
        utenteDAO.doSave("frank", "francesco@unisa.it", "Francesco1!", "Francesco", "Ceriello", "Uomo", "1985-12-10", "100000");

        filmDAO=new FilmDAO();
        film=new Film("TestEsistente", "GenereTest", 2010, "RegiaTest", "AttoriTest", "PaeseTest", 120, "DistribuzioneTest", "SceneggiaturaTest", "FotografiaTest", "MusicheTest", "ProduzioneTest", "TramaTest");
        filmDAO.doDeleteByTitoloAnnoRegia(film.getTitolo(), film.getAnno(), film.getRegia());
        int idFilm=filmDAO.doSave("TestEsistente", "GenereTest", 2010, "RegiaTest", "AttoriTest", "PaeseTest", 120, "DistribuzioneTest", "SceneggiaturaTest", "FotografiaTest", "MusicheTest", "ProduzioneTest", "TramaTest");
        film.setIdFilm(idFilm);

        recensioneDAO=new RecensioneDAO();
        recensione=new Recensione(2, "test test test",false, "frank",film.getIdFilm());
        recensioneDAO.doDeleteByTestUsernameFilm("test test test","frank",film.getIdFilm());//
        int idRecensione=recensioneDAO.doSave(2, "test test test","frank",film.getIdFilm());
        recensione.setIdRecensione(idRecensione);
    }

    @Test
    public void testRicercaPerIdFilm(){
        ArrayList<Film> collection = new ArrayList<>();
        assertNotEquals(collection, recensioneDAO.doRetrieveByIdFilm(film.getIdFilm()));
    }

    @Test
    public void testRicercaPerUsername() {
        ArrayList<Film> collection = new ArrayList<>();
        assertNotEquals(collection, recensioneDAO.doRetrieveByUsername("frank"));
    }


    @Test
    public void testUpdateSegnalazioneTrue() {
        assertEquals(true, recensioneDAO.doUpdateSegnalazioneTrue(recensione.getIdRecensione()));
    }

    @Test
    public void testUpdateSegnalazioneFalse() {

        assertEquals(true, recensioneDAO.doUpdateSegnalazioneFalse(recensione.getIdRecensione()));
    }

    @Test
    public void testRicercaPerSegnalazioneTrue() {
        assertNotEquals(recensione , recensioneDAO.doRetrieveBySegnalazione());
    }


    @AfterEach
    protected void tearDown() {
        filmDAO.doDeleteByTitoloAnnoRegia(film.getTitolo(),film.getAnno(),film.getRegia());
        utenteDAO.doDeleteByUsername("frank");
        recensioneDAO.doDeleteByTestUsernameFilm(recensione.getTesto(),"frank",film.getIdFilm());

    }
}