package model.notizia;

import model.connection.ConPool;
import model.recensione.Recensione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NotiziaDAO {
    //Ho tolto il primo campo (id_notizia) perché è auto_increment. Controllare se funziona
    public void doSave(Notizia n) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Notizia VALUES (?, ?, ?, ?)");
            ps.setString(1, n.getTitolo());
            ps.setString(2, n.getTesto());
            ps.setString(3, n.getFonte());
            ps.setInt(4, n.getId_film());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Insert ERROR");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
