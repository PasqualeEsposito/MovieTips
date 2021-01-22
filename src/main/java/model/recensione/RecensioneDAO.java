package model.recensione;

import model.connection.ConPool;

import java.sql.*;
import java.util.ArrayList;

public class RecensioneDAO {
    /**
     * @param idFilm
     * @return
     */
    public ArrayList<Recensione> doRetrieveByIdFilm(int idFilm) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM recensione WHERE id_film = ?");
            ps.setInt(1, idFilm);
            ResultSet rs = ps.executeQuery();
            ArrayList<Recensione> recensioni = new ArrayList<>();
            while (rs.next()) {
                Recensione r = new Recensione();
                r.setIdRecensione(rs.getInt(1));
                r.setValutazione(rs.getInt(2));
                r.setTesto(rs.getString(3));
                r.setSegnalazione(rs.getBoolean(4));
                r.setUsernameUtente(rs.getString(5));
                r.setIdFilm(rs.getInt(6));
                recensioni.add(r);
            }
            return recensioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param username
     * @return
     */
    public ArrayList<Recensione> doRetrieveByUsername(String username) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM recensione WHERE username_utente = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            ArrayList<Recensione> recensioni = new ArrayList<>();
            while (rs.next()) {
                Recensione r = new Recensione();
                r.setIdRecensione(rs.getInt(1));
                r.setValutazione(rs.getInt(2));
                r.setTesto(rs.getString(3));
                r.setSegnalazione(rs.getBoolean(4));
                r.setUsernameUtente(rs.getString(5));
                r.setIdFilm(rs.getInt(6));
                recensioni.add(r);
            }
            return recensioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return
     */
    public ArrayList<Recensione> doRetrieveBySegnalazione() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT id_recensione, valutazione, testo, username_utente FROM recensione WHERE segnalazione = true");
            ResultSet rs = ps.executeQuery();
            ArrayList<Recensione> recensioni = new ArrayList<>();
            while (rs.next()) {
                Recensione r = new Recensione();
                r.setIdRecensione(rs.getInt(1));
                r.setValutazione(rs.getInt(2));
                r.setTesto(rs.getString(3));
                r.setUsernameUtente(rs.getString(4));
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
     * @param usernameUtente
     * @param idFilm
     * @return
     */
    public int doSave(int valutazione, String testo, String usernameUtente, int idFilm) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO recensione (valutazione, testo, username_utente, id_film) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, valutazione);
            ps.setString(2, testo);
            ps.setString(3, usernameUtente);
            ps.setInt(4, idFilm);
            if (ps.executeUpdate() != 1) {
                return -1;
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param idRecensione
     */
    public boolean doDeleteByIdRecensione(int idRecensione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM recensione WHERE id_recensione = ?");
            ps.setInt(1, idRecensione);
            if (ps.executeUpdate() == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param idRecensione
     */
    public boolean doUpdateSegnalazioneTrue(int idRecensione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE recensione SET segnalazione = true WHERE id_recensione = ?");
            ps.setInt(1, idRecensione);
            if (ps.executeUpdate() == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    /**
     * @param idRecensione
     */
    public boolean doUpdateSegnalazioneFalse(int idRecensione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE recensione SET segnalazione = false WHERE id_recensione = ?");
            ps.setInt(1, idRecensione);
            if (ps.executeUpdate() == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean doDeleteByTestoUsernameUtenteIdFilm(String testo, String usernameUtente, int idFilm) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM recensione WHERE testo = ? AND username_utente = ? AND id_film = ?");
            ps.setString(1, testo);
            ps.setString(2, usernameUtente);
            ps.setInt(3, idFilm);
            if (ps.executeUpdate() == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean doUpdateSegnalazioneTrueByTestoUsernameUtenteIdFilm(String testo, String usernameUtente, int idFilm) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE recensione SET segnalazione = true WHERE testo = ? AND username_utente = ? AND id_film = ?");
            ps.setString(1, testo);
            ps.setString(2, usernameUtente);
            ps.setInt(3, idFilm);
            if (ps.executeUpdate() == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean doUpdateSegnalazioneFalseByTestoUsernameUtenteIdFilm(String testo, String usernameUtente, int idFilm) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE recensione SET segnalazione = false WHERE testo = ? AND username_utente = ? AND id_film = ?");
            ps.setString(1, testo);
            ps.setString(2, usernameUtente);
            ps.setInt(3, idFilm);
            if (ps.executeUpdate() == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}