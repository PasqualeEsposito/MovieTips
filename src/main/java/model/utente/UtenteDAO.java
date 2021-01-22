package model.utente;

import model.connection.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe che gestisce le interazioni con la tabella del database Utente
 */
public class UtenteDAO {
    /**
     * @param username
     * @return
     */
    public Utente doRetrieveByUsername(String username) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT username, mail, nome, cognome, genere, data_nascita FROM utente WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente u = new Utente();
                u.setUsername(rs.getString(1));
                u.setMail(rs.getString(2));
                u.setNome(rs.getString(3));
                u.setCognome(rs.getString(4));
                u.setGenere(rs.getString(5));
                u.setDataNascita(rs.getString(6));
                return u;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param mail
     * @return
     */
    public Utente doRetrieveByMail(String mail) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT username FROM utente WHERE mail = ?");
            ps.setString(1, mail);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente u = new Utente();
                u.setUsername(rs.getString(1));
                return u;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param mail
     * @param password
     * @return
     */
    public Utente doRetrieveByMailPassword(String mail, String password) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT username, mail, nome, cognome, genere, data_nascita, ruolo FROM utente WHERE mail = ? AND password = SHA1(?)");
            ps.setString(1, mail);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente u = new Utente();
                u.setUsername(rs.getString(1));
                u.setMail(rs.getString(2));
                u.setNome(rs.getString(3));
                u.setCognome(rs.getString(4));
                u.setGenere(rs.getString(5));
                u.setDataNascita(rs.getString(6));
                u.setRuolo(rs.getString(7));
                return u;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    /**
     * @param username
     * @param ruolo
     */
    public void doUpdateUtente(String username, String ruolo) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE utente SET ruolo = ? WHERE username = ?");
            ps.setString(1, ruolo);
            ps.setString(2, username);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}