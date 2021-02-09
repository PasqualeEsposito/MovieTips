package model.gestioneUtente;

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
     * @param mail
     * @param password
     * @param utente
     * @return
     */
    public int signIn(String mail, String password, Utente utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT username, mail, nome, cognome, genere, data_nascita, ruolo FROM utente WHERE mail = ? AND password = SHA1(?)");
            ps.setString(1, mail);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                utente.setRuolo(rs.getString(7));
                if (utente.isBanned())
                    return -2;
                utente.setUsername(rs.getString(1));
                utente.setMail(rs.getString(2));
                utente.setNome(rs.getString(3));
                utente.setCognome(rs.getString(4));
                utente.setGenere(rs.getString(5));
                utente.setDataNascita(rs.getString(6));
                return 1;
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    /**
     * @param username
     * @param utente
     * @return
     */
    public int banUser(String username, Utente utente) {
        if (utente == null) {
            return -1;
        }
        if (!utente.isModeratore()) {
            return -2;
        }
        if (username.equals(utente.getUsername())) {
            return -3;
        }
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE utente SET ruolo = 100000 WHERE username = ?");
            ps.setString(1, username);
            if (ps.executeUpdate() != 1) {
                return -4;
            }
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param username
     * @return
     */
    public Utente getUser(String username) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT username, mail, nome, cognome, genere, data_nascita, ruolo FROM utente WHERE username = ?");
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
                u.setRuolo(rs.getString(7));
                return u;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}