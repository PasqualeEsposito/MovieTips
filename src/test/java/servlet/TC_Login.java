package servlet;

import control.gestioneUtente.LoginServlet;
import model.connection.ConPool;
import model.utente.Utente;
import model.utente.UtenteDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TC_Login extends Mockito {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private LoginServlet servlet;
    private UtenteDAO utenteDAO = new UtenteDAO();
    private Utente utente = new Utente();

    @BeforeEach
    public void setUp() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT username FROM utente WHERE username = ?");
            ps.setString(1, "frank");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                try (Connection con1 = ConPool.getConnection()) {
                    PreparedStatement ps1 = con1.prepareStatement("DELETE FROM Utente WHERE mail = ?");
                    ps1.setString(1, "francesco@unisa.it");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO movietipsdb.utente (username, mail, password, nome, cognome, genere, data_nascita, ruolo) VALUES ('frank', 'francesco@unisa.it', SHA1('Francesco1!'), 'Francesco', 'Ceriello', 'Uomo', '1985-12-10', '100000')");
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM utente WHERE username = ?");
            ps.setString(1, "frank");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                utente.setUsername(rs.getString(1));
                utente.setMail(rs.getString(2));
                utente.setPassword(rs.getString(3));
                utente.setNome(rs.getString(4));
                utente.setCognome(rs.getString(5));
                utente.setGenere(rs.getString(6));
                utente.setDataNascita(rs.getString(7));
                utente.setRuolo(rs.getString(8));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        servlet = new LoginServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void TC_Login1() throws ServletException, IOException {
        request.addParameter("mail", "fra.it");
        request.addParameter("password", "");
        String message = "LE_FAIL";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_Login2() throws ServletException, IOException {
        request.addParameter("mail", "francesco.it");
        request.addParameter("password", "");
        String message = "FE_FAIL";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TTC_Login3() throws ServletException, IOException {
        request.addParameter("mail", "franco@unisa.it");
        request.addParameter("password", "");
        String message = "EE_FAIL";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_Login4() throws ServletException, IOException {
        request.addParameter("mail", utente.getMail());
        request.addParameter("password", "fra");
        String message = "LP_FAIL";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_Login5() throws ServletException, IOException {
        request.addParameter("mail", utente.getMail());
        request.addParameter("password", "Francesco");
        String message = "FP_FAIL";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_Login6() throws ServletException, IOException {
        request.addParameter("mail", utente.getMail());
        request.addParameter("password", "Francesco1");
        String message = "CP_FAIL";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    public void TC_Login7() throws ServletException, IOException {
        request.addParameter("mail", utente.getMail());
        request.addParameter("password", "Francesco1!");
        String message = "OK";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @AfterEach
    public void tearDown() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Utente WHERE mail = ?");
            ps.setString(1, "francesco@unisa.it");
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        servlet = null;
        request = null;
        response = null;
    }
}