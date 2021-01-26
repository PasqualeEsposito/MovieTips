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

    /*public Film doRetrieveByTitoloAnnoRegia(String titolo, int anno, String regia) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM film WHERE titolo = ? AND anno = ? AND regia = ?");
            ps.setString(1, titolo);
            ps.setInt(2, anno);
            ps.setString(3, regia);
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

    public int doSave(String titolo, String genere, int anno, String regia, String attori, String paese, int durata, String distribuzione, String sceneggiatura, String fotografia, String musiche, String produzione, String trama) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps;
            if (attori.isEmpty()) {
                ps = con.prepareStatement(
                        "INSERT INTO film (titolo, genere, anno, regia, attori, paese, durata, distribuzione, sceneggiatura, fotografia, musiche, produzione, trama) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, titolo);
                ps.setString(2, genere);
                ps.setInt(3, anno);
                ps.setString(4, regia);
                ps.setString(5, attori);
                ps.setString(6, paese);
                ps.setInt(7, durata);
                ps.setString(8, distribuzione);
                ps.setString(9, sceneggiatura);
                ps.setString(10, fotografia);
                ps.setString(11, musiche);
                ps.setString(12, produzione);
                ps.setString(13, trama);
            } else {
                ps = con.prepareStatement(
                        "INSERT INTO film (titolo, genere, anno, regia, paese, durata, distribuzione, sceneggiatura, fotografia, musiche, produzione, trama) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, titolo);
                ps.setString(2, genere);
                ps.setInt(3, anno);
                ps.setString(4, regia);
                ps.setString(5, paese);
                ps.setInt(6, durata);
                ps.setString(7, distribuzione);
                ps.setString(8, sceneggiatura);
                ps.setString(9, fotografia);
                ps.setString(10, musiche);
                ps.setString(11, produzione);
                ps.setString(12, trama);
            }
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean doDeleteByTitoloAnnoRegia(String titolo, int anno, String regia) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM film WHERE titolo = ? AND anno = ? AND regia = ?");
            ps.setString(1, titolo);
            ps.setInt(2, anno);
            ps.setString(3, regia);
            if (ps.executeUpdate() == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
}