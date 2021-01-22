package model.film;

import model.connection.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe che gestisce le interazioni con la tabella del database Film
 */
public class FilmDAO {
    /**
     * @param limit
     * @return
     */
    public ArrayList<Film> doRetrieveAll(int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM film ORDER BY id_film LIMIT ?");
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            ArrayList<Film> films = new ArrayList<>();
            while (rs.next()) {
                Film f = new Film();
                f.setIdFilm(rs.getInt(1));
                f.setTitolo(rs.getString(2));
                f.setGenere(rs.getString(3));
                f.setAnno(rs.getInt(4));
                f.setRegia(rs.getString(5));
                films.add(f);
            }
            return films;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param id
     * @return
     */
    public Film doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM film WHERE id_film = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Film f = new Film();
                f.setIdFilm(rs.getInt(1));
                f.setTitolo(rs.getString(2));
                f.setGenere(rs.getString(3));
                f.setAnno(rs.getInt(4));
                f.setRegia(rs.getString(5));
                f.setAttori(rs.getString(6));
                f.setPaese(rs.getString(7));
                f.setDurata(rs.getInt(8));
                f.setDistribuzione(rs.getString(9));
                f.setSceneggiatura(rs.getString(10));
                f.setFotografia(rs.getString(11));
                f.setMusiche(rs.getString(12));
                f.setProduzione(rs.getString(13));
                f.setTrama(rs.getString(14));
                return f;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param ricerca
     * @return
     */
    public ArrayList<Film> doRetrieveByWord(String ricerca) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM film");
            ResultSet rs = ps.executeQuery();
            ArrayList<Film> films = new ArrayList<>();
            while (rs.next()) {
                String titolo = rs.getString(2).toLowerCase();
                if (titolo.contains(ricerca.toLowerCase())) {
                    Film f = new Film();
                    f.setIdFilm(rs.getInt(1));
                    f.setTitolo(rs.getString(2));
                    f.setGenere(rs.getString(3));
                    f.setAnno(rs.getInt(4));
                    f.setRegia(rs.getString(5));
                    films.add(f);
                }
            }
            return films;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}