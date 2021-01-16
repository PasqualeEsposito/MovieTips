package model.recensione;

import model.connection.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecensioneDAO {
    //Ho tolto il primo campo (id_recensione) perché è auto_increment. Controllare se funziona
    public void doSave(Recensione r) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Recensione VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, r.getValutazione());
            ps.setString(2, r.getTesto());
            ps.setInt(3, r.getNumero_segnalazioni());
            ps.setInt(4, r.getId_utente());
            ps.setInt(5, r.getId_film());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Insert ERROR");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Recensione> doRetrieveById_utente(int id_utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Recensione WHERE id_utente = ?");
            ps.setInt(1, id_utente);
            ResultSet rs = ps.executeQuery();
            ArrayList<Recensione> recensioni = new ArrayList<>();
            while (rs.next()) {
                Recensione r = new Recensione();
                r.setId_recensione(rs.getInt(1));
                r.setValutazione(rs.getInt(2));
                r.setTesto(rs.getString(3));
                r.setNumero_segnalazioni(rs.getInt(4));
                r.setId_utente(rs.getInt(5));
                r.setId_film(rs.getInt(6));
                recensioni.add(r);
            }
            return recensioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Recensione> doRetrieveById_film(int id_film) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Recensione WHERE id_film = ?");
            ps.setInt(1, id_film);
            ResultSet rs = ps.executeQuery();
            ArrayList<Recensione> recensioni = new ArrayList<>();
            while (rs.next()) {
                Recensione r = new Recensione();
                r.setId_recensione(rs.getInt(1));
                r.setValutazione(rs.getInt(2));
                r.setTesto(rs.getString(3));
                r.setNumero_segnalazioni(rs.getInt(4));
                r.setId_utente(rs.getInt(5));
                r.setId_film(rs.getInt(6));
                recensioni.add(r);
            }
            return recensioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
