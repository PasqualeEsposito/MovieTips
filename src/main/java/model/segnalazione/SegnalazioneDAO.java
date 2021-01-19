package model.segnalazione;

import model.connection.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SegnalazioneDAO {
    public void doSave(Segnalazione s) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Segnalazione VALUES (?, ?);");
            ps.setInt(1, s.getIdRecensione());
            ps.setInt(1, s.getIdUtente());
            if (ps.executeUpdate() != 1)
                throw new RuntimeException("INSERT ERROR");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int doRetrieveByIdRecensione(int idRecensione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(id_recensione) FROM Segnalazione WHERE id_recensione = ? GROUP BY id_recensione;");
            ps.setInt(1, idRecensione);
            ResultSet rs = ps.executeQuery();
            Segnalazione s = new Segnalazione();
            if (rs.next())
                return rs.getInt(2);
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public ArrayList<Segnalazione> doRetrieveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT id_recensione, COUNT(id_recensione) FROM Segnalazione GROUP BY id_recensione;");
            ResultSet rs = ps.executeQuery();
            ArrayList<Segnalazione> list = new ArrayList<>();
            while (rs.next()) {
                Segnalazione s = new Segnalazione();
                s.setIdRecensione(rs.getInt(1));
                s.setCounter(rs.getInt(2));
                list.add(s);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    /*
    public boolean doCheckIdRecensioneIdUtente(int idRecensione, int idUtente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Segnalazione WHERE id_recensione = ? AND id_utente = ?;");
            ps.setInt(1, idRecensione);
            ps.setInt(2, idUtente);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    */

    public ArrayList<Integer> doRetrieveByIdUtente(int idUtente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT id_recensione FROM Segnalazione WHERE id_utente = ?;");
            ps.setInt(1, idUtente);
            ResultSet rs = ps.executeQuery();
            ArrayList<Integer> list = new ArrayList<>();
            while (rs.next())
                list.add(rs.getInt(1));
            return list;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
