package unit;

import junit.framework.TestCase;
import model.connection.TestConPool;
import model.gestioneFilm.Film;
import model.gestioneFilm.FilmDAO;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Test_FilmDAO extends TestCase {
    private FilmDAO filmDAO;

    @BeforeEach
    protected void setUp() throws SQLException, FileNotFoundException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection con = TestConPool.getConnection();
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
        ArrayList<Film> films = new ArrayList<>();
        films.add(film1);
        films.add(film2);
        ArrayList<Film> output = filmDAO.searchFilms("ta");
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
}