package DAO;
import Proyecto.Feria;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Feria_DAO {
    public void addFeria(Feria feria) throws SQLException {
        String sql = "INSERT INTO Feria (nombre, ubicacion, fecha_inicio, fecha_fin) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, feria.getNombre());
            stmt.setString(2, feria.getUbicacion());
            stmt.setDate(3, Date.valueOf(feria.getFechaInicio().toLocalDate()));
            stmt.setDate(4, Date.valueOf(feria.getFechaFin().toLocalDate()));
            stmt.executeUpdate();
        }
    }

    public List<Feria> getAllFerias() throws SQLException {
        List<Feria> ferias = new ArrayList<>();
        String sql = "SELECT * FROM Feria";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Feria feria = new Feria();
                feria.setFeriaId(rs.getInt("feria_id"));
                feria.setNombre(rs.getString("nombre"));
                feria.setUbicacion(rs.getString("ubicacion"));
                feria.setFechaInicio(Date.valueOf(rs.getDate("fecha_inicio").toLocalDate()));
                feria.setFechaFin(Date.valueOf(rs.getDate("fecha_fin").toLocalDate()));
                ferias.add(feria);
            }
        }
        return ferias;
    }
}