package servlet;

import control.MyServletException;
import control.gestioneRecensione.AggiungiValutazioneServlet;
import model.film.Film;
import model.film.FilmDAO;
import model.recensione.RecensioneDAO;
import model.utente.UtenteDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TC_AggiungiValutazione extends Mockito {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private AggiungiValutazioneServlet servlet;
    Film film;
    private UtenteDAO utenteDAO = new UtenteDAO();
    private FilmDAO filmDAO = new FilmDAO();
    private RecensioneDAO recensioneDAO = new RecensioneDAO();

    @BeforeEach
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new AggiungiValutazioneServlet();
        film = new Film("TestEsistente", "GenereTest", 2010, "RegiaTest", "AttoriTest", "PaeseTest", 120, "DistribuzioneTest", "SceneggiaturaTest", "FotografiaTest", "MusicheTest", "ProduzioneTest", "TramaTest");
        filmDAO.doDeleteByTitoloAnnoRegia(film.getTitolo(), film.getAnno(), film.getRegia());
        int idFilm = filmDAO.doSave("TestEsistente", "GenereTest", 2010, "RegiaTest", "AttoriTest", "PaeseTest", 120, "DistribuzioneTest", "SceneggiaturaTest", "FotografiaTest", "MusicheTest", "ProduzioneTest", "TramaTest");
        film.setIdFilm(idFilm);
        utenteDAO.doDeleteByUsername("frank");
        utenteDAO.doSave("frank", "francesco@unisa.it", "Francesco1!", "Francesco", "Ceriello", "Uomo", "1985-12-10", "001000");
    }

    @Test
    public void TC_AggiungiValutazione1() throws IOException, MyServletException {
        request.addParameter("valutazione", "-1");
        request.addParameter("idFilm", "" + film.getIdFilm());
        request.getSession().setAttribute("utente", utenteDAO.doRetrieveByMail("francesco@unisa.it"));
        String message = "RV1_FAIL";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_AggiungiValutazione2() throws IOException, MyServletException {
        request.addParameter("valutazione", "4");
        request.addParameter("idFilm", "" + film.getIdFilm());
        request.getSession().setAttribute("utente", utenteDAO.doRetrieveByMail("francesco@unisa.it"));
        request.addParameter("testo", "Sed ut perspiciatis unde omnis iste natus error sit voluptatem " +
                "accusantium doloremque laudantium, totam rem aperiam eaque ipsa, quae ab illo inventore veritatis et" +
                " quasi architecto beatae vitae dicta sunt, explicabo. Nemo enim ipsam voluptatem, quia voluptas sit," +
                " aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos, qui ratione voluptatem sequi" +
                " nesciunt, neque porro quisquam est, qui dolorem ipsum, quia dolor sit, amet, consectetur, adipisci " +
                "velit, sed quia non numquam eius modi tempora incidunt, ut labore et dolore magnam aliquam quaerat" +
                " voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam," +
                " nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit, qui in ea " +
                "voluptate velit esse, quam nihil molestiae consequatur, vel illum, qui dolorem eum fugiat, quo" +
                " voluptas nulla pariatur? [33] At vero eos et accusamus et iusto odio dignissimos ducimus, qui" +
                "blanditiis praesentium voluptatum deleniti atque corrupti, quos dolores et quas molestias excepturi " +
                "sint, obcaecati cupiditate non provident, similique sunt in culpa, qui officia deserunt " +
                "mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita " +
                "distinctio. Nam libero tempore, cum soluta nobis est eligendi optio, cumque nihil impedit, " +
                "quo minus id, quod maxime placeat, facere possimus, omnis voluptas assumenda est, omnis dolor " +
                "repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe " +
                "eveniet, ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic " +
                "tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut " +
                "perferendis doloribus asperiores repellat.");
        String message = "LT_FAIL";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_AggiungiValutazione3() throws IOException, MyServletException {
        request.addParameter("valutazione", "3");
        request.addParameter("testo", "Film mediocre");
        request.addParameter("idFilm", "" + film.getIdFilm());
        request.getSession().setAttribute("utente", utenteDAO.doRetrieveByMail("francesco@unisa.it"));
        String message = "OK";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @AfterEach
    public void tearDown() {
        utenteDAO.doDeleteByUsername("frank");
        filmDAO.doDeleteByTitoloAnnoRegia(film.getTitolo(), film.getAnno(), film.getRegia());
        servlet = null;
        request = null;
        response = null;
    }
}