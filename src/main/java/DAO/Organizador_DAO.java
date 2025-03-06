package DAO;
import Proyecto.Organizador;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Organizador_DAO {
    public void addOrganizador(Organizador organizador) throws SQLException {
        String sql = "INSERT INTO Organizador (nombre, rol, contacto, feria_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, organizador.getNombre());
            stmt.setString(2, organizador.getRol());
            stmt.setString(3, organizador.getContacto());
            stmt.setInt(4, organizador.getFeriaId());
            stmt.executeUpdate();
        }
    }

    public List<Organizador> getAllOrganizadores() throws SQLException {
        List<Organizador> organizadores = new ArrayList<>();
        String sql = "SELECT * FROM Organizador";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Organizador organizador = new Organizador();
                organizador.setOrganizadorId(rs.getInt("organizador_id"));
                organizador.setNombre(rs.getString("nombre"));
                organizador.setRol(rs.getString("rol"));
                organizador.setContacto(rs.getString("contacto"));
                organizador.setFeriaId(rs.getInt("feria_id"));
                organizadores.add(organizador);
            }
        }
        return organizadores;
    }
}