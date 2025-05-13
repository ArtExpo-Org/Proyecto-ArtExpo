package GestorFX;
import Proyecto.Entradas;
import Proyecto.TipoPago;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.util.Locale;

public class Funcionalidades {
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

    public static void desconectar(Connection conexion) {
        try {
            conexion.close();
            System.out.println("Conexión cerrada");
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static boolean actualizarContraseña(Connection conexion, String email, String contraseña) {
        String query = "UPDATE usuario SET contrasena = ? WHERE correo = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, contraseña);
            stmt.setString(2, email);

            int filaAfectada = stmt.executeUpdate();
            return filaAfectada > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar la contraseña: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static boolean verificarCredenciales(Connection conexion, String usernameOrEmail, String password) {
        String query = "SELECT * FROM usuario WHERE (nombre = ? OR correo = ?) AND contrasena = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, usernameOrEmail);
            stmt.setString(2, usernameOrEmail);
            stmt.setString(3, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static boolean verificarCorreo(Connection conexion, String email) {
        String query = "SELECT * FROM usuario WHERE correo = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error al verificar correo: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static String obtenerTipoUsuario(Connection conexion, String usernameOrEmail, String password) {
        String query = "SELECT tipo_usuario FROM usuario WHERE (nombre = ? OR correo = ?) AND contrasena = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, usernameOrEmail);
            stmt.setString(2, usernameOrEmail);
            stmt.setString(3, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("tipo_usuario");
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Error al obtener tipo de usuario: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<ImprimirObra> obtenerObras(Connection conexion) {
        String query = "SELECT titulo, nombre , anio_creacion, tecnica, precio FROM obra inner JOIN usuario ON usuario.usuario_id = obra.artista_id";
        Statement stmt;
        ResultSet resultado;
        ObservableList<ImprimirObra> listaObras = FXCollections.observableArrayList();
        try {
            stmt = conexion.createStatement();
            resultado = stmt.executeQuery(query);

            while (resultado.next()){
                int año = resultado.getInt("anio_creacion");
                String nombre = resultado.getString("nombre");
                String titulo = resultado.getString("titulo");
                String tecnica = resultado.getString("tecnica");
                double precio = resultado.getDouble("precio");
                listaObras.add(new ImprimirObra(titulo, nombre, año, tecnica, precio));
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return listaObras;
    }

    public static Entradas obtenerEntrada(Connection conexion, String usernameOrEmail) {
        String query = "Select tipo_entrada from visitante inner join usuario on usuario.usuario_id = visitante.visitante_id where correo = ? or nombre = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, usernameOrEmail);
            stmt.setString(2, usernameOrEmail);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Entradas.valueOf(rs.getString("tipo_entrada"));
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Error al obtener tipo de usuario: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void realizarCompra(Connection conexion, ImprimirObra obraSeleccionada, TipoPago metodoPago, String usuario) {
        try {
            String query = "UPDATE visitante JOIN usuario ON usuario_id = visitante_id SET precio_ticket = ? WHERE correo = ? OR nombre = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                Entradas tipoEntrada = obtenerEntrada(conexion, usuario);
                double precioEntrada = 0;
                if (tipoEntrada != null) {
                    precioEntrada = tipoEntrada.getPrecio();
                }
                double precioTotal = obraSeleccionada.getPrecio() + metodoPago.getComision() + precioEntrada;
                stmt.setDouble(1, precioTotal);
                stmt.setString(2, usuario);
                stmt.setString(3, usuario);
                stmt.executeUpdate();
            }

            int obraId = obtenerObraId(conexion, obraSeleccionada.getTitulo(), obraSeleccionada.getNombreArtista());
            int compradorId = obtenerCompradorId(conexion, usuario);
            String query2 = "INSERT INTO venta (fecha_venta, obra_id, comprador_id) VALUES (?, ?, ?)";
            try (PreparedStatement insertStmt = conexion.prepareStatement(query2)) {
                LocalDate fechaActual = LocalDate.now();
                insertStmt.setDate(1, Date.valueOf(fechaActual));
                insertStmt.setInt(2, obraId);
                insertStmt.setInt(3, compradorId);

                insertStmt.executeUpdate();
                System.out.println("Venta registrada con éxito");
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar la compra: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static int obtenerObraId(Connection conexion, String titulo, String nombreArtista) {
        String query = "SELECT obra_id FROM obra JOIN usuario ON usuario.usuario_id = obra.artista_id WHERE titulo = ? AND nombre = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, titulo);
            stmt.setString(2, nombreArtista);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("obra_id");
            } else {
                throw new SQLException("No se encontró la obra con título: " + titulo + " del artista: " + nombreArtista);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener ID de la obra: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static int obtenerCompradorId(Connection conexion, String usernameOrEmail) {
        String query = "SELECT usuario_id FROM usuario WHERE correo = ? OR nombre = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, usernameOrEmail);
            stmt.setString(2, usernameOrEmail);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("usuario_id");
            } else {
                throw new SQLException("No se encontró el usuario: " + usernameOrEmail);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener ID del comprador: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}