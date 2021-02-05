package integration;

import control.gestioneRecensione.AggiungiRecensioneServlet;
import model.gestioneUtente.Utente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test_AggiungiRecensioneServlet extends Mockito {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private AggiungiRecensioneServlet servlet;

    @BeforeEach
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new AggiungiRecensioneServlet();
    }

    @Test
    public void testAggiungiValutazione1() throws ServletException, IOException {
        request.addParameter("valutazione", "5");
        request.addParameter("idFilm", "1");
        request.addParameter("testo", "Test");
        request.getSession().setAttribute("utente", null);
        String message = "Errore: accesso non effettuato";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testAggiungiValutazione2() throws ServletException, IOException {
        request.addParameter("valutazione", "5");
        request.addParameter("idFilm", "1");
        request.addParameter("testo", "Test");
        Utente utente = new Utente();
        utente.setRuolo("000100");
        utente.setUsername("marco_bellamico");
        request.getSession().setAttribute("utente", utente);
        String message = "Errore: utente non ricopre il ruolo di filmino";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testAggiungiValutazione3() throws ServletException, IOException {
        request.addParameter("valutazione", "0");
        request.addParameter("idFilm", "1");
        request.addParameter("testo", "Test");
        Utente utente = new Utente();
        utente.setRuolo("001000");
        utente.setUsername("fabrizio_ceriello");
        request.getSession().setAttribute("utente", utente);
        String message = "Errore: range valutazione";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testAggiungiValutazione4() throws ServletException, IOException {
        request.addParameter("valutazione", "5");
        request.addParameter("idFilm", "1");
        request.addParameter("testo", "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam eaque ipsa, quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt, explicabo. Nemo enim ipsam voluptatem, quia voluptas sit, aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos, qui ratione voluptatem sequi nesciunt, neque porro quisquam est, qui dolorem ipsum, quia dolor sit, amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt, ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit, qui in ea voluptate velit esse, quam nihil molestiae consequatur, vel illum, qui dolorem eum fugiat, quo voluptas nulla pariatur? [33] At vero eos et accusamus et iusto odio dignissimos ducimus, qui blanditiis praesentium voluptatum deleniti atque corrupti, quos dolores et quas molestias excepturi sint, obcaecati cupiditate non provident, similique sunt in culpa, qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio, cumque nihil impedit, quo minus id, quod maxime placeat, facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet, ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.");
        Utente utente = new Utente();
        utente.setRuolo("001000");
        utente.setUsername("fabrizio_ceriello");
        request.getSession().setAttribute("utente", utente);
        String message = "Errore: lunghezza testo";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void testAggiungiValutazione5() throws ServletException, IOException {
        request.addParameter("valutazione", "5");
        request.addParameter("idFilm", "1");
        request.addParameter("testo", "Test");
        Utente utente = new Utente();
        utente.setRuolo("001000");
        utente.setUsername("fabrizio_ceriello");
        request.getSession().setAttribute("utente", utente);
        String message = "Ok: recensione effettuata";
        servlet.doGet(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }
}
