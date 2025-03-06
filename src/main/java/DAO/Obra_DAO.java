package DAO;
import Proyecto.Obra;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Obra_DAO {
    public void addObra(Obra obra) throws SQLException {
        String sql = "INSERT INTO Obra (titulo, anio_creacion, tecnica, precio, artista_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obra.getTitulo());
            stmt.setInt(2, obra.getAnioCreacion());
            stmt.setString(3, obra.getTecnica());
            stmt.setBigDecimal(4, obra.getPrecio());
            stmt.setInt(5, obra.getArtistaId());
            stmt.executeUpdate();
        }
    }

    public List<Obra> getAllObras() throws SQLException {
        List<Obra> obras = new ArrayList<>();
        String sql = "SELECT * FROM Obra";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Obra obra = new Obra();
                obra.setObraId(rs.getInt("obra_id"));
                obra.setTitulo(rs.getString("titulo"));
                obra.setAnioCreacion(rs.getInt("anio_creacion"));
                obra.setTecnica(rs.getString("tecnica"));
                obra.setPrecio(rs.getBigDecimal("precio"));
                obra.setArtistaId(rs.getInt("artista_id"));
                obras.add(obra);
            }
        }
        return obras;
    }
}