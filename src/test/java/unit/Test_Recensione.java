package unit;

import junit.framework.TestCase;
import model.gestioneFilm.Film;
import model.gestioneFilm.FilmDAO;
import model.gestioneRecensione.Recensione;
import model.gestioneRecensione.RecensioneDAO;
import model.gestioneUtente.UtenteDAO;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class Test_Recensione extends TestCase {
    private FilmDAO filmDAO;
    private Film film;
    private UtenteDAO utenteDAO;
    private RecensioneDAO recensioneDAO;
    private Recensione recensioneEsistente;
    private Recensione recensioneNonEsistente;

    @BeforeEach
    protected void setUp() {
        utenteDAO = new UtenteDAO();
        utenteDAO.doDeleteByUsername("frank");
        utenteDAO.doSave("frank", "francesco@unisa.it", "Francesco1!", "Francesco", "Ceriello", "Uomo", "1985-12-10", "100000");

        filmDAO = new FilmDAO();
        film = new Film("TestEsistente", "GenereTest", 2010, "RegiaTest", "AttoriTest", "PaeseTest", 120, "DistribuzioneTest", "SceneggiaturaTest", "FotografiaTest", "MusicheTest", "ProduzioneTest", "TramaTest");
        filmDAO.doDeleteByTitoloAnnoRegia(film.getTitolo(), film.getAnno(), film.getRegia());
        int idFilm = filmDAO.doSave("TestEsistente", "GenereTest", 2010, "RegiaTest", "AttoriTest", "PaeseTest", 120, "DistribuzioneTest", "SceneggiaturaTest", "FotografiaTest", "MusicheTest", "ProduzioneTest", "TramaTest");
        film.setIdFilm(idFilm);

        recensioneDAO = new RecensioneDAO();
        recensioneEsistente = new Recensione(5, "TestoEsistenteTest", false, "frank", film.getIdFilm());
        recensioneNonEsistente = new Recensione(1, "TestoNonEsistenteTest", false, "frank", film.getIdFilm());
        recensioneDAO.doDeleteByTestoUsernameUtenteIdFilm(recensioneEsistente.getTesto(), recensioneEsistente.getUsernameUtente(), recensioneEsistente.getIdFilm());
        recensioneDAO.doDeleteByTestoUsernameUtenteIdFilm(recensioneNonEsistente.getTesto(), recensioneNonEsistente.getUsernameUtente(), recensioneNonEsistente.getIdFilm());
        int idRecensione = recensioneDAO.doSave(recensioneEsistente.getValutazione(), recensioneEsistente.getTesto(), recensioneEsistente.getUsernameUtente(), recensioneEsistente.getIdFilm());
        recensioneEsistente.setIdRecensione(idRecensione);
        recensioneDAO.doUpdateSegnalazioneTrue(idRecensione);
    }

    @Test
    public void testRetrieveByIdFilmRecensioni() {
        ArrayList<Film> collection = new ArrayList<>();
        assertNotEquals(collection, recensioneDAO.doRetrieveByIdFilm(film.getIdFilm()));
    }

    @Test
    public void testRetrieveByUsernameRecensioni() {
        ArrayList<Film> collection = new ArrayList<>();
        assertNotEquals(collection, recensioneDAO.doRetrieveByUsername("frank"));
    }

    @Test
    public void testRetrieveBySegnalazioneRecensioni() {
        ArrayList<Film> collection = new ArrayList<>();
        assertNotEquals(collection, recensioneDAO.doRetrieveBySegnalazione());
    }

    @Test
    public void testSavaRecensioneNonEsistente() {
        assertNotEquals(-1, recensioneDAO.doSave(recensioneNonEsistente.getValutazione(), recensioneNonEsistente.getTesto(), recensioneNonEsistente.getUsernameUtente(), recensioneNonEsistente.getIdFilm()));
    }

    @Test
    public void testDeleteRecensioneEsistente() {
        assertEquals(true, recensioneDAO.doDeleteByIdRecensione(recensioneEsistente.getIdRecensione()));
    }

    @Test
    public void testDeleteRecensioneNonEsistente() {
        assertEquals(false, recensioneDAO.doDeleteByTestoUsernameUtenteIdFilm(recensioneNonEsistente.getTesto(), recensioneNonEsistente.getUsernameUtente(), recensioneNonEsistente.getIdFilm()));
    }

    @Test
    public void testUpdateSegnalazioneTrueRecensioneEsistente() {
        assertEquals(true, recensioneDAO.doUpdateSegnalazioneTrue(recensioneEsistente.getIdRecensione()));
    }

    @Test
    public void testUpdateSegnalazioneTrueRecensioneNonEsistente() {
        assertEquals(false, recensioneDAO.doUpdateSegnalazioneTrueByTestoUsernameUtenteIdFilm(recensioneNonEsistente.getTesto(), recensioneNonEsistente.getUsernameUtente(), recensioneNonEsistente.getIdFilm()));
    }

    @Test
    public void testUpdateSegnalazioneFalseRecensioneEsistente() {
        assertEquals(true, recensioneDAO.doUpdateSegnalazioneFalse(recensioneEsistente.getIdRecensione()));
    }

    @Test
    public void testUpdateSegnalazioneFalseRecensioneNonEsistente() {
        assertEquals(false, recensioneDAO.doUpdateSegnalazioneFalseByTestoUsernameUtenteIdFilm(recensioneNonEsistente.getTesto(), recensioneNonEsistente.getUsernameUtente(), recensioneNonEsistente.getIdFilm()));
    }

    @AfterEach
    protected void tearDown() {
        filmDAO.doDeleteByTitoloAnnoRegia(film.getTitolo(), film.getAnno(), film.getRegia());
        utenteDAO.doDeleteByUsername("frank");
        recensioneDAO.doDeleteByIdRecensione(recensioneEsistente.getIdRecensione());
        recensioneDAO.doDeleteByTestoUsernameUtenteIdFilm(recensioneNonEsistente.getTesto(), recensioneNonEsistente.getUsernameUtente(), recensioneNonEsistente.getIdFilm());
    }
}