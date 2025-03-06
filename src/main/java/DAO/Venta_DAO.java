package DAO;
import Proyecto.Venta;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Venta_DAO {
    public void addVenta(Venta venta) throws SQLException {
        String sql = "INSERT INTO Venta (precio_final, nombre_comprador, fecha_venta, obra_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBigDecimal(1, venta.getPrecioFinal());
            stmt.setString(2, venta.getNombreComprador());
            stmt.setDate(3, Date.valueOf(venta.getFechaVenta().toLocalDate()));
            stmt.setInt(4, venta.getObraId());
            stmt.executeUpdate();
        }
    }

    public List<Venta> getAllVentas() throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM Venta";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setVentaId(rs.getInt("venta_id"));
                venta.setPrecioFinal(rs.getBigDecimal("precio_final"));
                venta.setNombreComprador(rs.getString("nombre_comprador"));
                venta.setFechaVenta(rs.getDate("fecha_venta"));
                venta.setObraId(rs.getInt("obra_id"));
                ventas.add(venta);
            }
        }
        return ventas;
    }
}