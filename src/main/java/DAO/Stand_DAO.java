package DAO;
import Proyecto.Stand;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Stand_DAO {
    public void addStand(Stand stand) throws SQLException {
        String sql = "INSERT INTO Stand (numero, feria_id, artista_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, stand.getNumero());
            stmt.setInt(2, stand.getFeriaId());
            stmt.setInt(3, stand.getArtistaId());
            stmt.executeUpdate();
        }
    }

    public List<Stand> getAllStands() throws SQLException {
        List<Stand> stands = new ArrayList<>();
        String sql = "SELECT * FROM Stand";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Stand stand = new Stand();
                stand.setStandId(rs.getInt("stand_id"));
                stand.setNumero(rs.getInt("numero"));
                stand.setFeriaId(rs.getInt("feria_id"));
                stand.setArtistaId(rs.getInt("artista_id"));
                stands.add(stand);
            }
        }
        return stands;
    }
}