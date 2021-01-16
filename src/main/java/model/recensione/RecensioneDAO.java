package model.recensione;

import model.connection.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
