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
            PreparedStatement ps = con.prepareStatement("INSERT INTO Recensione VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, r.getValutazione());
            ps.setString(2, r.getTesto());
            ps.setInt(3, r.getNumeroSegnalazioni());
            ps.setInt(4, r.getIdUtente());
            ps.setInt(5, r.getIdFilm());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Insert ERROR");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Recensione> doRetrieveByIdUtente(int idUtente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Recensione WHERE id_utente = ?");
            ps.setInt(1, idUtente);
            ResultSet rs = ps.executeQuery();
            ArrayList<Recensione> recensioni = new ArrayList<>();
            while (rs.next()) {
                Recensione r = new Recensione();
                r.setIdRecensione(rs.getInt(1));
                r.setValutazione(rs.getInt(2));
                r.setTesto(rs.getString(3));
                r.setNumeroSegnalazioni(rs.getInt(4));
                r.setIdUtente(rs.getInt(5));
                r.setIdFilm(rs.getInt(6));
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
                r.setNumeroSegnalazioni(rs.getInt(4));
                r.setIdUtente(rs.getInt(5));
                r.setIdFilm(rs.getInt(6));
                recensioni.add(r);
            }
            return recensioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}