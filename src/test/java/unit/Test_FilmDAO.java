package unit;

import junit.framework.TestCase;
import model.connection.ConPool;
import model.gestioneFilm.Film;
import model.gestioneFilm.FilmDAO;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test_FilmDAO extends TestCase {
    private FilmDAO filmDAO;

    @BeforeEach
    protected void setUp() throws SQLException, FileNotFoundException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection con = ConPool.getConnection();
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader("src/test/java/testmovietips.sql"));
        sr.runScript(reader);
        filmDAO = new FilmDAO();
    }

    @Test
    public void testRicercaFilm1() {
        assertNull(filmDAO.searchFilms(""));    // LR < 1 or LR > 255
    }

    @Test
    public void testRicercaFilm2() {
        Film film1 = new Film();
        film1.setIdFilm(1);
        film1.setTitolo("La città incantata");
        film1.setGenere("Animazione, Avventura, Fantasy");
        film1.setAnno(2001);
        film1.setRegia("Hayao Miyazaki");
        Film film2 = new Film();
        film2.setIdFilm(2);
        film2.setTitolo("Ratatouille");
        film2.setGenere("Animazione");
        film2.setAnno(2007);
        film2.setRegia("Brad Bird");
        List<Film> films = new ArrayList<>();
        films.add(film1);
        films.add(film2);
        List<Film> output = filmDAO.searchFilms("ta");
        int flag = 1;
        if (output.size() != films.size()) {
            flag = 0;
        } else {
            for (int i = 0; i < output.size(); i++) {
                if (!(films.get(i).getIdFilm() == output.get(i).getIdFilm()
                        && films.get(i).getTitolo().equals(output.get(i).getTitolo())
                        && films.get(i).getGenere().equals(output.get(i).getGenere())
                        && films.get(i).getAnno() == output.get(i).getAnno()
                        && films.get(i).getRegia().equals(output.get(i).getRegia())))
                    flag = 0;
            }
        }
        assertEquals(1, flag);  // Ok: ricerca effettuata
    }

    @Test
    public void testDoRetrieveLastTen1() {
        Film film1 = new Film();
        film1.setIdFilm(3);
        film1.setTitolo("Il Cavaliere Oscuro");
        film1.setGenere("Azione, Fantasy");
        film1.setAnno(2008);
        film1.setRegia("Christopher Nolan");

        Film film2 = new Film();
        film2.setIdFilm(2);
        film2.setTitolo("Ratatouille");
        film2.setGenere("Animazione");
        film2.setAnno(2007);
        film2.setRegia("Brad Bird");

        Film film3 = new Film();
        film3.setIdFilm(1);
        film3.setTitolo("La città incantata");
        film3.setGenere("Animazione, Avventura, Fantasy");
        film3.setAnno(2001);
        film3.setRegia("Hayao Miyazaki");

        List<Film> films = new ArrayList<>();
        films.add(film1);
        films.add(film2);
        films.add(film3);
        List<Film> output = filmDAO.doRetrieveLastTen();
        int flag = 1;
        if (output.size() != films.size()) {
            flag = 0;
        } else {
            for (int i = 0; i < output.size(); i++) {
                if (!(films.get(i).getIdFilm() == output.get(i).getIdFilm()
                        && films.get(i).getTitolo().equals(output.get(i).getTitolo())
                        && films.get(i).getGenere().equals(output.get(i).getGenere())
                        && films.get(i).getAnno() == output.get(i).getAnno()
                        && films.get(i).getRegia().equals(output.get(i).getRegia())))
                    flag = 0;
            }
        }
        assertEquals(1, flag);
    }

    @Test
    public void testDoRetrieveById1() {
        Film film = filmDAO.getFilm(0);
        assertEquals(null, film);
    }

    @Test
    public void testDoRetrieveById2() {
        Film film1 = new Film();
        film1.setIdFilm(1);
        film1.setTitolo("La città incantata");
        film1.setGenere("Animazione, Avventura, Fantasy");
        film1.setAnno(2001);
        film1.setRegia("Hayao Miyazaki");
        film1.setPaese("Giappone");
        film1.setDurata(122);
        film1.setDistribuzione("Lucky Red");
        film1.setSceneggiatura("Hayao Miyazaki");
        film1.setFotografia("Atsushi Okui");
        film1.setMusiche("Joe Hisaishi");
        film1.setProduzione("Studio Ghibli, Nippon Television Network (NTV), DENTSU Music And Entertainment");
        film1.setTrama("La città incantata è un film d'animazione del 2001 scritto e diretto da Hayao Miyazaki. " +
                "La famiglia di Chihiro, una bambina di 10 anni, è viaggio verso la nuova città dove abiteranno; " +
                "durante il percorso, il papà sbaglia strada e si trova all'imbocco di un tunnel che sbuca su una " +
                "vallata su alcune case. Incuriositi, il papà e la mamma si addentrano alla scoperta del posto in cui" +
                " sono capitati, contro il parere di Chihiro, che ha un brutto presentimento. Superando un fiume il cui " +
                "letto è in secca, arrivano in una città piena di ristoranti e si siedono a mangiare, mentre la " +
                "bambina incontra un ragazzo, Haku, che le ordina di andarsene subito. Spaventata, Chihiro torna " +
                "dai genitori ma scopre che si sono trasformati in maiali, e non riesce a fuggire perchè il fiume " +
                "è in piena. Si rende conto che sta diventando invisibile, ma Haku decide di aiutarla e la fa" +
                " assumere con il nome di Sen da Yubaba, la potente strega che controlla tutta la città, che " +
                "usa questo stratagemma (privare del proprio nome) per impedire alle persone di scappare. " +
                "Lavorando nella città incantata, Chihiro incontra creature fantastiche e magiche, e proprio lei che" +
                " è una ragazzina capricciosa e viziata, imparerà il valore dell'amore, dell'amicizia e della " +
                "solidarietà, affrontando come un'adulta le difficoltà e le scelte che le si presenteranno, cercando " +
                "di tornare a casa senza dimenticare chi è.");
        Film film2 = filmDAO.getFilm(1);
        int flag = 1;
        if (!(film1.getIdFilm() == film2.getIdFilm()
                && film1.getTitolo().equals(film2.getTitolo())
                && film1.getGenere().equals(film2.getGenere())
                && film1.getAnno() == film2.getAnno()
                && film1.getRegia().equals(film2.getRegia())
                && film1.getPaese().equals(film2.getPaese())
                && film1.getDurata() == film2.getDurata()
                && film1.getDistribuzione().equals(film2.getDistribuzione())
                && film1.getSceneggiatura().equals(film2.getSceneggiatura())
                && film1.getFotografia().equals(film2.getFotografia())
                && film1.getMusiche().equals(film2.getMusiche())
                && film1.getProduzione().equals(film2.getProduzione())
                && film1.getTrama().equals(film2.getTrama())))
            flag = 0;
        assertEquals(1, flag);

    }

    @AfterEach
    public void tearDown() {
        filmDAO = null;
    }
}