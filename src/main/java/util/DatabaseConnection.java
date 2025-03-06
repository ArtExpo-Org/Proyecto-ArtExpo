package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://localhost;databaseName=FERIA_ARTE_LOCAL";
    private static final String USER = "yourUsername"; // Actualiza con tu nombre de usuario
    private static final String PASSWORD = "yourPassword"; // Actualiza con tu contraseña

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}