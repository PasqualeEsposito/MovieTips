package model.gestioneRecensione;

import model.connection.ConPool;
import model.gestioneUtente.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDAO {
    /**
     * @param idFilm
     * @return
     */
    public List<Recensione> getReviewsByFilm(int idFilm) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM recensione WHERE id_film = ?");
            ps.setInt(1, idFilm);
            ResultSet rs = ps.executeQuery();
            List<Recensione> recensioni = new ArrayList<>();
            while (rs.next()) {
                Recensione r = new Recensione();
                r.setIdRecensione(rs.getInt(1));
                r.setValutazione(rs.getInt(2));
                r.setTesto(rs.getString(3));
                r.setSegnalazione(rs.getBoolean(4));
                r.setIdFilm(rs.getInt(5));
                r.setUsernameUtente(rs.getString(6));
                recensioni.add(r);
            }
            return recensioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param valutazione
     * @param testo
     * @param idFilm
     * @param utente
     * @return
     */
    public int addReview(int valutazione, String testo, int idFilm, Utente utente) {
        if (utente == null) {
            return -1;
        }
        if (!utente.isFilmino()) {
            return -2;
        }
        if (valutazione < 1 || valutazione > 5) {
            return -3;
        } else {
            if (testo.length() < 1 || testo.length() > 255) {
                return -4;
            }
        }
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO recensione (valutazione, testo, id_film, username_utente) VALUES (?, ?, ?, ?)");
            ps.setInt(1, valutazione);
            ps.setString(2, testo);
            ps.setInt(3, idFilm);
            ps.setString(4, utente.getUsername());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error");
            }
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param idRecensione
     * @param utente
     * @return
     */
    public int deleteReview(int idRecensione, Utente utente) {
        if (utente == null) {
            return -1;
        }
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM recensione WHERE id_recensione = ? AND username_utente = ?");
            ps.setInt(1, idRecensione);
            ps.setString(2, utente.getUsername());
            if (ps.executeUpdate() != 1) {
                return -2;
            }
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param utente
     * @param recensioni
     * @return
     */
    public int getReportedReviews(Utente utente, List<Recensione> recensioni) {
        if (utente == null)
            return -1;
        if (!utente.isModeratore())
            return -2;
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT id_recensione, valutazione, testo, username_utente FROM recensione WHERE segnalazione = true");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Recensione r = new Recensione();
                r.setIdRecensione(rs.getInt(1));
                r.setValutazione(rs.getInt(2));
                r.setTesto(rs.getString(3));
                r.setUsernameUtente(rs.getString(4));
                recensioni.add(r);
            }
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param idRecensione
     * @param utente
     * @return
     */
    public int ignoreReporting(int idRecensione, Utente utente) {
        if (utente == null) {
            return -1;
        }
        if (!utente.isModeratore()) {
            return -2;
        }
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE recensione SET segnalazione = false WHERE id_recensione = ? AND segnalazione = true");
            ps.setInt(1, idRecensione);
            if (ps.executeUpdate() != 1) {
                return -3;
            }
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param idRecensione
     * @param utente
     * @return
     */
    public int moderateReview(int idRecensione, Utente utente) {
        if (utente == null) {
            return -1;
        }
        if (!utente.isModeratore()) {
            return -2;
        }
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM recensione WHERE id_recensione = ?");
            ps.setInt(1, idRecensione);
            if (ps.executeUpdate() != 1) {
                return -3;
            }
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param idRecensione
     * @param utente
     * @return
     */
    public int reportReview(int idRecensione, Utente utente) {
        if (utente == null) {
            return -1;
        }
        if (utente.isNotActive()) {
            return -2;
        }
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE recensione SET segnalazione = true WHERE id_recensione = ?");
            ps.setInt(1, idRecensione);
            if (ps.executeUpdate() != 1) {
                return -3;
            }
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param username
     * @return
     */
    public List<Recensione> getReviewsByUser(String username) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM recensione WHERE username_utente = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            List<Recensione> recensioni = new ArrayList<>();
            while (rs.next()) {
                Recensione r = new Recensione();
                r.setIdRecensione(rs.getInt(1));
                r.setValutazione(rs.getInt(2));
                r.setTesto(rs.getString(3));
                r.setSegnalazione(rs.getBoolean(4));
                r.setIdFilm(rs.getInt(5));
                r.setUsernameUtente(rs.getString(6));
                recensioni.add(r);
            }
            return recensioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}