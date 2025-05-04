package DAO;
import java.sql.*;
import java.time.LocalDate;
public class Conector {
    public static void main(String[] args) {
        Connection conexion = conectar();
        consulta(conexion);
        desconectar(conexion);
    }

    public static Connection conectar() {
        Connection conexion;
        String host = "jdbc:mariadb://localhost:3307/";
        String usuario = "root";
        String password = "";
        String bd = "instituto";
        try {
            conexion = DriverManager.getConnection(host + bd, usuario, password);
            System.out.println("Conexión exitosa a la base de datos " + bd);
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return conexion;
    }

    private static void desconectar(Connection conexion) {
        try {
            conexion.close();
            System.out.println("Conexión cerrada");
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static void consulta(Connection conexion) {
        String query = "SELECT * FROM estudiante";
        Statement stmt;
        ResultSet resultado;

        try {
            stmt = conexion.createStatement();
            resultado = stmt.executeQuery(query);

            while (resultado.next()){
                int nia = resultado.getInt("nia");
                String nombre = resultado.getString("nombre");
                LocalDate fechaNacimiento = resultado.getDate("fecha_nacimiento").toLocalDate();

                System.out.println("nia: " + nia + ", nombre: " + nombre + ", fecha de nacimiento: " + fechaNacimiento);
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static void insertar(Connection conexion) {
        String query = "INSERT INTO estudiante (nia, nombre, fecha_nacimiento) VALUES ('59424830', 'Hector', '1999-01-01')";
        Statement stmt;
        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Inserción exitosa");
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la inserción: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static void modificar(Connection conexion) {
        String query = "UPDATE estudiante SET nombre = 'Santaolalla' WHERE nia = 22345678";
        Statement stmt;
        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Modificación exitosa");
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la modificación: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static void borrar(Connection conexion) {
        String query = "DELETE FROM estudiante WHERE nia = 22345678";
        Statement stmt;
        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Borrado exitoso");
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el borrado: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
