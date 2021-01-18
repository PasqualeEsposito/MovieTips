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
     * Metodo che recupera un utente dal database in base all'email e alla password
     *
     * @param email    L'email dell'utente
     * @param password La password dell'utente
     * @return Ritorna un utente
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
                u.setRuolo(rs.getString(9));
                return u;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void doDeleteByEmail(String email){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Utente WHERE email = ?");
            ps.setString(1, email);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Delete error");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
            ps.setString(8, u.getRuolo());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Insert ERROR");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*public ArrayList<Utente> doRetrieveAll() {
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
                u.setRuolo(rs.getString(9));
                utenti.add(u);
            }
            return utenti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
                u.setRuolo(rs.getString(9));
                return u;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
    }*/
}