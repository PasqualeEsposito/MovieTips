package model.filmSeguiti;

import model.connection.ConPool;
import model.film.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilmSeguitiDAO {
    public void doSave(int idUtente, int idFilm) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Film_seguiti VALUES (?, ?)");
            ps.setInt(1, idUtente);
            ps.setInt(2, idFilm);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Insert ERROR");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Film> doRetrieveByIdUtente(int id_utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT f.id_film, f.titolo, f.produzione, f.musiche, f.fotografia, f.sceneggiatura, f.distribuzione, f.durata," +
                    "f.paese, f.attori, f.regia, f.genere, f.trama, f.anno FROM Film_seguiti s JOIN Film f WHERE id_utente  = ? AND f.id_film = s.id_film");
            ps.setInt(1, id_utente);
            ResultSet rs = ps.executeQuery();
            ArrayList<Film> filmSeguiti = new ArrayList<>();
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
                f.setGenere(rs.getString(13));
                f.setTrama(rs.getString(14));
                f.setAnno(rs.getInt(15));
                filmSeguiti.add(f);
            }
            return filmSeguiti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}