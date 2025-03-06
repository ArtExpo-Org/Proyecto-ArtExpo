package DAO;
import Proyecto.Visitante;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Visitante_DAO {
    public void addVisitante(Visitante visitante) throws SQLException {
        String sql = "INSERT INTO Visitante (nombre, correo, tipo_entrada) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, visitante.getNombre());
            stmt.setString(2, visitante.getCorreo());
            stmt.setString(3, visitante.getTipoEntrada());
            stmt.executeUpdate();
        }
    }

    public List<Visitante> getAllVisitantes() throws SQLException {
        List<Visitante> visitantes = new ArrayList<>();
        String sql = "SELECT * FROM Visitante";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Visitante visitante = new Visitante();
                visitante.setVisitanteId(rs.getInt("visitante_id"));
                visitante.setNombre(rs.getString("nombre"));
                visitante.setCorreo(rs.getString("correo"));
                visitante.setTipoEntrada(rs.getString("tipo_entrada"));
                visitantes.add(visitante);
            }
        }
        return visitantes;
    }
}