package servlet;

import control.MyServletException;
import control.gestioneFilm.FilmServlet;
import model.film.Film;
import model.film.FilmDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TC_Film {
    private FilmServlet servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;

    @BeforeEach
    void setUp() {

        //instantiation servlet, mockedRequest, mockedResponse and session.
        servlet = new FilmServlet();
        mockedRequest = Mockito.mock(HttpServletRequest.class);
        mockedResponse = Mockito.mock(HttpServletResponse.class);
    }

    @Test
    void TC_Film1() {

        Mockito.when(mockedRequest.getParameter("id")).thenReturn("7");

        String message = "Siamo spiacenti, la pagina richiesta non è stata trovata";

        MyServletException exception = assertThrows(MyServletException.class, () ->
                servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());


    }


}
