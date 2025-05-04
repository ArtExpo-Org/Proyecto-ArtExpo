package DAO;
import Proyecto.TipoUsuario;
import java.sql.*;
public class Conector {
    public static void main(String[] args) {
        Connection conexion = conectar();
        borrar(conexion);
        insertar(conexion);
        consulta(conexion);
        desconectar(conexion);
    }

    public static Connection conectar() {
        Connection conexion;
        String host = "jdbc:mariadb://localhost:3307/";
        String usuario = "root";
        String password = "";
        String bd = "expo_db";
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
        String query = "SELECT * FROM usuario";
        Statement stmt;
        ResultSet resultado;

        try {
            stmt = conexion.createStatement();
            resultado = stmt.executeQuery(query);

            while (resultado.next()){
                int id = resultado.getInt("usuario_id");
                String nombre = resultado.getString("nombre");
                String correo = resultado.getString("correo");
                String telefono = resultado.getString("telefono");
                String contrasena = resultado.getString("contrasena");
                TipoUsuario tipoUsuario = TipoUsuario.valueOf(resultado.getString("tipo_usuario"));

                System.out.println("ID: " + id +
                        ", Nombre: " + nombre +
                        ", Correo: " + correo +
                        ", Teléfono: " + telefono +
                        ", Contraseña: " + contrasena +
                        ", Tipo de Usuario: " + tipoUsuario
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static void insertar(Connection conexion) {
        String query = "INSERT INTO usuario (nombre, correo, telefono, contrasena, tipo_usuario) VALUES ('Ana', 'anita@outlook.es', '456789123', 'GAMMA123', 'ARTISTA')";
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
        String query = "UPDATE usuario SET nombre = 'Juan Alberto' WHERE correo = 'juan21@outlook.es'";
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
        String query = "DELETE FROM usuario WHERE correo = 'anita@outlook.es'";
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
