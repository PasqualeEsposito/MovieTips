package model.recensione;

import model.connection.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecensioneDAO {
    public void doSave(Recensione r) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Recensione (valutazione, testo, username_utente, id_film) VALUES (?, ?, ?, ?);");
            ps.setInt(1, r.getValutazione());
            ps.setString(2, r.getTesto());
            ps.setString(3, r.getUsernameUtente());
            ps.setInt(4, r.getIdFilm());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Insert ERROR");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Recensione> doRetrieveByUsername(String username) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Recensione WHERE username_utente = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            ArrayList<Recensione> recensioni = new ArrayList<>();
            while (rs.next()) {
                Recensione r = new Recensione();
                r.setIdRecensione(rs.getInt(1));
                r.setValutazione(rs.getInt(2));
                r.setTesto(rs.getString(3));
                r.setUsernameUtente(rs.getString(4));
                r.setIdFilm(rs.getInt(5));
                recensioni.add(r);
            }
            return recensioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Recensione> doRetrieveByIdFilm(int idFilm) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Recensione WHERE id_film = ?");
            ps.setInt(1, idFilm);
            ResultSet rs = ps.executeQuery();
            ArrayList<Recensione> recensioni = new ArrayList<>();
            while (rs.next()) {
                Recensione r = new Recensione();
                r.setIdRecensione(rs.getInt(1));
                r.setValutazione(rs.getInt(2));
                r.setTesto(rs.getString(3));
                r.setUsernameUtente(rs.getString(4));
                r.setIdFilm(rs.getInt(5));
                r.setSegnalazione(rs.getBoolean(6));
                recensioni.add(r);
            }
            return recensioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Recensione doRetrieveByIdRecensione(int idRecensione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Recensione WHERE id_recensione = ?");
            ps.setInt(1, idRecensione);
            ResultSet rs = ps.executeQuery();
            Recensione r = new Recensione();
            if (rs.next()) {
                r.setIdRecensione(rs.getInt(1));
                r.setValutazione(rs.getInt(2));
                r.setTesto(rs.getString(3));
                r.setUsernameUtente(rs.getString(4));
                r.setIdFilm(rs.getInt(5));
                r.setSegnalazione(rs.getBoolean(6));
            }
            return r;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Recensione> doRetrieveBySegnalazione() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Recensione WHERE segnalazione = true");
            ResultSet rs = ps.executeQuery();
            ArrayList<Recensione> list = new ArrayList<>();
            while (rs.next()) {
                Recensione r = new Recensione();
                r.setIdRecensione(rs.getInt(1));
                r.setValutazione(rs.getInt(2));
                r.setTesto(rs.getString(3));
                r.setUsernameUtente(rs.getString(4));
                r.setIdFilm(rs.getInt(5));
                r.setSegnalazione(rs.getBoolean(6));
                list.add(r);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDeleteByIdRecensione(int idRecensione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Recensione WHERE id_recensione = ?");
            ps.setInt(1, idRecensione);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Delete error");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateSegnalazioneTrue(int idRecensione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE Recensione SET segnalazione = true WHERE id_recensione = ?");
            ps.setInt(1, idRecensione);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateSegnalazioneFalse(int idRecensione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE Recensione SET segnalazione = false WHERE id_recensione = ?");
            ps.setInt(1, idRecensione);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}