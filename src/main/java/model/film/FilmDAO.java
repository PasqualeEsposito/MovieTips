package model.film;

import model.connection.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe che interagisce con la tabella Film all'interno del database
 */
public class FilmDAO {
    /**
     * @param f
     */
    public void doSave(Film f) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Film VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, f.getTitolo());
            ps.setString(2, f.getProduzione());
            ps.setString(3, f.getMusiche());
            ps.setString(4, f.getFotografia());
            ps.setString(5, f.getSceneggiatura());
            ps.setString(6, f.getDistribuzione());
            ps.setInt(7, f.getDurata());
            ps.setString(8, f.getPaese());
            ps.setString(9, f.getAttori());
            ps.setString(10, f.getRegia());
            ps.setString(11, f.getGenere());
            ps.setString(12, f.getTrama());
            ps.setInt(13, f.getAnno());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Insert ERROR");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param offset
     * @param limit
     * @return
     */
    public ArrayList<Film> doRetrieveAll(int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Film LIMIT ?, ?");
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();
            ArrayList<Film> film = new ArrayList<>();
            while (rs.next()) {
                Film f = new Film();
                f.setIdFilm(rs.getInt(1));
                f.setTitolo(rs.getString(2));
                f.setProduzione(rs.getString(3));
                f.setMusiche(rs.getString(4));
                f.setFotografia(rs.getString(5));
                f.setSceneggiatura(rs.getString(6));
                f.setDistribuzione(rs.getString(7));
                f.setDurata(rs.getInt(8));
                f.setPaese(rs.getString(9));
                f.setAttori(rs.getString(10));
                f.setRegia(rs.getString(11));
                f.setGenere(rs.getString(12));
                f.setTrama(rs.getString(13));
                f.setAnno(rs.getInt(14));
                film.add(f);
            }
            return film;
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
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Film");
            ResultSet rs = ps.executeQuery();
            ArrayList<Film> film = new ArrayList<>();
            while (rs.next()) {
                String titolo = rs.getString(2).toLowerCase();
                if (titolo.contains(ricerca.toLowerCase()) || titolo.equalsIgnoreCase(ricerca)) {
                    Film f = new Film();
                    f.setIdFilm(rs.getInt(1));
                    f.setTitolo(rs.getString(2));
                    f.setProduzione(rs.getString(3));
                    f.setMusiche(rs.getString(4));
                    f.setFotografia(rs.getString(5));
                    f.setSceneggiatura(rs.getString(6));
                    f.setDistribuzione(rs.getString(7));
                    f.setDurata(rs.getInt(8));
                    f.setPaese(rs.getString(9));
                    f.setAttori(rs.getString(10));
                    f.setRegia(rs.getString(11));
                    f.setGenere(rs.getString(12));
                    f.setTrama(rs.getString(13));
                    f.setAnno(rs.getInt(14));
                    film.add(f);
                }
            }
            return film;
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
            PreparedStatement ps = con.prepareStatement("SELECT * FROM FILM WHERE Id_film=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Film f = new Film();
                f.setIdFilm(rs.getInt(1));
                f.setTitolo(rs.getString(2));
                f.setProduzione(rs.getString(3));
                f.setMusiche(rs.getString(4));
                f.setFotografia(rs.getString(5));
                f.setSceneggiatura(rs.getString(6));
                f.setDistribuzione(rs.getString(7));
                f.setDurata(rs.getInt(8));
                f.setPaese(rs.getString(9));
                f.setAttori(rs.getString(10));
                f.setRegia(rs.getString(11));
                f.setGenere(rs.getString(12));
                f.setTrama(rs.getString(13));
                f.setAnno(rs.getInt(14));
                return f;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param id
     */
    public void doDeleteById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Film where id_film = ?");
            ps.setInt(1, id);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("delete ERROR");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}