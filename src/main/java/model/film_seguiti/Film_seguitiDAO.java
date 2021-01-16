package model.film_seguiti;

import model.connection.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Film_seguitiDAO {
    public void doSave(Film_seguiti f) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Film_seguiti VALUES (?, ?)");
            ps.setInt(1, f.getId_utente());
            ps.setInt(2, f.getId_film());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Insert ERROR");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Film_seguiti> doRetrieveById_utente(int id_utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Film_seguiti WHERE id_utente  = ?");
            ps.setInt(1, id_utente);
            ResultSet rs = ps.executeQuery();
            ArrayList<Film_seguiti> film_seguiti = new ArrayList<>();
            while (rs.next()) {
                Film_seguiti f = new Film_seguiti();
                f.setId_utente(rs.getInt(1));
                f.setId_film(rs.getInt(2));
                film_seguiti.add(f);
            }
            return film_seguiti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Film_seguiti> doRetrieveById_film(int id_film) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Film_seguiti WHERE id_film  = ?");
            ps.setInt(1, id_film);
            ResultSet rs = ps.executeQuery();
            ArrayList<Film_seguiti> film_seguiti = new ArrayList<>();
            while (rs.next()) {
                Film_seguiti f = new Film_seguiti();
                f.setId_utente(rs.getInt(1));
                f.setId_film(rs.getInt(2));
                film_seguiti.add(f);
            }
            return film_seguiti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
