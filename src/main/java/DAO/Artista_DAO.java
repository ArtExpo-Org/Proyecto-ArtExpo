package DAO;
import Proyecto.Artista;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Artista_DAO {
    public void addArtista(Artista artista) throws SQLException {
        String sql = "INSERT INTO Artista (nombre, biografia, contacto) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, artista.getNombre());
            stmt.setString(2, artista.getBiografia());
            stmt.setString(3, artista.getContacto());
            stmt.executeUpdate();
        }
    }

    public List<Artista> getAllArtistas() throws SQLException {
        List<Artista> artistas = new ArrayList<>();
        String sql = "SELECT * FROM Artista";
        try (Connection co = DatabaseConnection.getConnection();
             Statement stmt = co.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Artista artista = new Artista();
                artista.setArtistaId(rs.getInt("artista_id"));
                artista.setNombre(rs.getString("nombre"));
                artista.setBiografia(rs.getString("biografia"));
                artista.setContacto(rs.getString("contacto"));
                artistas.add(artista);
            }
        }
        return artistas;
    }
}