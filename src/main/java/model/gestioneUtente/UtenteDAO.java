package model.gestioneUtente;

import control.MyServletException;
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
   private int doRetrieveByMail(String mail) {
        if (mail.length() < 8 || mail.length() > 255) {
            return -1;
        } else {
            if (!mail.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")) {
                return -2;
            }
        }
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT username, ruolo FROM utente WHERE mail = ?");
            ps.setString(1, mail);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente u = new Utente();
                u.setUsername(rs.getString(1));
                u.setRuolo(rs.getString(2));
                if(u.isBanned()){
                    return -3;
                }
                return 1;
            }
            return -4;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param mail
     * @param password
     * @return
     */
    public int doRetrieveByMailPassword(String mail,String password) {
        int numero=doRetrieveByMail(mail);
        if(numero != 1){
            return numero;
        }
        if (password.length() < 8 || password.length() > 255) {
            return -5;
        } else {
            if (password.toUpperCase().equals(password) || password.toLowerCase().equals(password) || !password.matches(".*[0-9].*")) {
                return -6;
            }
        }
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
                return 1;
            }
            return -7;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    /**
     * @param username
     * @param ruolo
     */
    public int doUpdateUtente(Utente utente,String username, String ruolo) {
        if (utente == null || !utente.isModeratore()) {
            return -1;
        }
        if (username.equals(utente.getUsername())){
            return -2;
        }
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE utente SET ruolo = ? WHERE username = ?");
            ps.setString(1, ruolo);
            ps.setString(2, username);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error");
            }
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*public void doSave(String username, String mail, String password, String nome, String cognome, String genere, String dataNascita, String ruolo) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO utente (username, mail, password, nome, cognome, genere, data_nascita, ruolo) VALUES (?, ?, SHA1(?), ?, ?, ?, ?, ?)");
            ps.setString(1, username);
            ps.setString(2, mail);
            ps.setString(3, password);
            ps.setString(4, nome);
            ps.setString(5, cognome);
            ps.setString(6, genere);
            ps.setString(7, dataNascita);
            ps.setString(8, ruolo);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean doDeleteByUsername(String username) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM utente where username = ?");
            ps.setString(1, username);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }*/
}