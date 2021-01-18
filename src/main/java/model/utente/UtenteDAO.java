package model.utente;

import model.connection.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe che interagisce con la tabella Utente all'interno del database
 */
public class UtenteDAO {
    /**
     * @param u
     */
    public void doSave(Utente u) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Utente (username, nome, cognome, email, passowrd, genere, ddn, ruolo) " + "VALUE(?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getNome());
            ps.setString(3, u.getCognome());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getPassword());
            ps.setString(6, u.getGenere());
            ps.setString(7, u.getDdn());
            ps.setInt(8, u.getRuolo());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Insert ERROR");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return
     */
    public ArrayList<Utente> doRetrieveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Utente");
            ResultSet rs = ps.executeQuery();
            ArrayList<Utente> utenti = new ArrayList<>();
            while (rs.next()) {
                Utente u = new Utente();
                u.setIdUtente(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setNome(rs.getString(3));
                u.setCognome(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setGenere(rs.getString(7));
                u.setDdn(rs.getString(8));
                u.setRuolo(rs.getInt(9));
                utenti.add(u);
            }
            return utenti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param email
     * @param password
     * @return
     */
    public Utente doRetrieveByEmailPassword(String email, String password) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from Utente where email = ? and password = SHA1(?)");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente u = new Utente();
                u.setIdUtente(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setNome(rs.getString(3));
                u.setCognome(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setGenere(rs.getString(7));
                u.setDdn(rs.getString(8));
                u.setRuolo(rs.getInt(9));
                return u;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    /**
     * @param email
     * @return
     */
    public Utente doRetrieveByEmail(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Utente WHERE email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente u = new Utente();
                u.setIdUtente(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setNome(rs.getString(3));
                u.setCognome(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setPassword(rs.getString(6));
                u.setGenere(rs.getString(7));
                u.setDdn(rs.getString(8));
                u.setRuolo(rs.getInt(9));
                return u;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param email
     * @return
     */
    public String doRetrievePasswordByEmail(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Utente WHERE email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente u = new Utente();
                return rs.getString(5);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param email
     * @param password
     */
    public void doUpdatePasswordByEmail(String email, String password) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE Utente SET password = SHA1(?) WHERE email = ?");
            ps.setString(1, password);
            ps.setString(2, email);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("update ERROR");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}