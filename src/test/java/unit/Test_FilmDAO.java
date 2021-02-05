package unit;

import junit.framework.TestCase;
import model.connection.ConPool;
import model.gestioneFilm.Film;
import model.gestioneFilm.FilmDAO;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Test_FilmDAO extends TestCase {
    private FilmDAO filmDAO;

    @BeforeEach
    protected void setUp() throws SQLException, FileNotFoundException {
        filmDAO = new FilmDAO();

        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection con = ConPool.getConnection();
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader("src/test/java/movietips.sql"));
        sr.runScript(reader);
    }

    @Test
    public void testRicercaFilm1() {
        assertEquals(null, filmDAO.searchFilms(""));  // L’utente inserisce una stringa da ricercare che presenta una lunghezza minore di 1 carattere oppure maggiore di 255
    }

    @Test
    public void testRicercaFilm2() {
        Film film1 = new Film("Ratatouille", "Animazione", 2007, "Brad Bird", "Patton Oswalt, Lou Romano, Brad Garrett, Ian Holm", "USA", 110,
                "Buena Vista International Italia", "Brad Bird", "Robert Anderson (III), Sharon Calahan", "Michael Giacchino",
                "Pixar Animation Studios, Walt Disney Pictures",
                "Ratatouille, è un film d'animazione Pixar del 2007 diretto da Brad Bird e Jan Pinkava. Francia, 1970. Rémy è un piccolo topo delle campagne parigine dotato di un olfatto straordinario e un gusto raffinato, che ha un insolito sogno per essere un roditore: diventare un vero cuoco e cucinare in un ristorante rinomato. Nonostante la sua famiglia lo inviti ad accontentarsi dello stile di vita della colonia di topi, Remy non vuole continuare a rovistare nella spazzatura ma vuole sperimentare nuovi sapori. Le circostanze faranno sì che il topo si ritrovi da solo a Parigi nel ristorante che prende il nome dal celeberrimo Auguste Gusteau, il suo chef prediletto il cui motto 'chiunque può cucinare' ha ispirato Rémy per tutta la vita. Il topolino capisce ben presto di non essere il benvenuto in un cucina e nel momento in cui i suoi sogni sembrano sul punto di andare in fumo, Rémy fa amicizia con Alfredo Linguini, il maldestro e introverso sguattero che lavora nel ristorante stellato. Linguini infatti scopre l'incredibile talento del topo per la cucina dopo averlo osservato nell'intento di modificare una zuppa con diversi ingredienti. Dopo che la zuppa di Rémy riceve una recensione positiva da una critica gastronomica, lui e Linguini decideranno di formare la più improbabile delle coppie dal momento. Tutti crederanno che l'autore della zuppa sia stato proprio l'addetto alle pulizie. Sullo sfondo di un'affascinante Parigi, i due amici si ritroveranno a vivere un'incredibile avventura fatta di svolte comiche, strani sospetti e sviluppi emotivi, per riportare il ristorante di Gusteau al suo antico splendore.");
        ArrayList<Film> films = new ArrayList<>();
        films.add(film1);
        ArrayList<Film> output = filmDAO.searchFilms("Ratatouille");
        for (Film f : output) {
            if (film1.getIdFilm() == f.getIdFilm() && film1.getTitolo().equals(f.getTitolo()) && film1.getGenere().equals(f.getGenere())
                    && film1.getAnno() == f.getAnno() && film1.getRegia().equals(f.getRegia()) && film1.getAttori().equals(f.getAttori())
                    && film1.getPaese().equals(f.getPaese()) && film1.getDurata() == f.getDurata() && film1.getDistribuzione().equals(f.getDistribuzione())
                    && film1.getSceneggiatura().equals(f.getSceneggiatura()) && film1.getFotografia().equals(f.getFotografia())
                    && film1.getMusiche().equals(f.getMusiche()) && film1.getProduzione().equals(f.getProduzione()) && film1.getTrama().equals(f.getTrama())) {

            }
        }
    }
}
