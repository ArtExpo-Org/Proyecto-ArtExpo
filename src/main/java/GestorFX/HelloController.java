package GestorFX;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import java.sql.Connection;
public class HelloController {
    public Button iniciarButton;
    public Hyperlink restablecerButton;
    public Button passButton;
    public Text errorField;

    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;

    public void onIniciarButtonClick() {
        String usernameOrEmail = usernameField.getText();
        String password = passwordField.getText();

        if (usernameOrEmail.isEmpty() || password.isEmpty()) {
            errorField.setText("Por favor, complete todos los campos.");
            errorField.visibleProperty().set(true);
            return;
        }

        Connection conexion = Funcionalidades.conectar();
        boolean credencialesValidas = Funcionalidades.verificarCredenciales(conexion, usernameOrEmail, password);

        if (credencialesValidas) {
            System.out.println("Inicio de sesión exitoso para: " + usernameOrEmail);
            String tipoUsuario = Funcionalidades.obtenerTipoUsuario(conexion, usernameOrEmail, password);
            String pantallaDestino = "";

            if ("VISITANTE".equals(tipoUsuario)) {
                pantallaDestino = "/GestorFX/visitante.fxml";
            }

            Stage stage = (Stage) iniciarButton.getScene().getWindow();
            stage.close();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(pantallaDestino));
                Parent root = loader.load();

                ObrasController controller = loader.getController();
                controller.recibirNombre(usernameOrEmail);

                Stage newStage = new Stage();
                newStage.setTitle("Panel de " + tipoUsuario);
                newStage.setScene(new Scene(root));
                newStage.show();
            } catch (IOException e) {
                System.err.println("Error al cargar la ventana: " + e.getMessage());
            }
        } else {
            errorField.setText("Credenciales no validas. Intentelo de nuevo.");
            errorField.visibleProperty().set(true);
        }
        Funcionalidades.desconectar(conexion);
    }

    public void onRestablecerButtonClick() {
        Stage stage = (Stage) restablecerButton.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GestorFX/restablecer.fxml"));
            Parent root = loader.load();

            Stage newStage = new Stage();
            newStage.setTitle("Recuperar Contraseña");
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            System.err.println("Error al cargar la ventana de recuperación de contraseña: " + e.getMessage());
        }
    }

    public void onPassButtonClick() {
        String email = emailField.getText();
        String nuevaPassword = passwordField.getText();

        if (email.isEmpty() || nuevaPassword.isEmpty()) {
            errorField.setText("Por favor, complete todos los campos.");
            errorField.setVisible(true);
            return;
        }

        Connection conexion = Funcionalidades.conectar();

        if (!Funcionalidades.verificarCorreo(conexion, email)) {
            errorField.setText("No existe un usuario con este correo electrónico.");
            errorField.setVisible(true);
            Funcionalidades.desconectar(conexion);
            return;
        }

        boolean actualizado = Funcionalidades.actualizarContraseña(conexion, email, nuevaPassword);

        if (actualizado) {
            System.out.println("Contraseña actualizada con éxito para el usuario: " + email);
            Stage stage = (Stage) passButton.getScene().getWindow();
            stage.close();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GestorFX/visitante.fxml"));
                Parent root = loader.load();

                ObrasController controller = loader.getController();
                controller.recibirNombre(email);

                Stage newStage = new Stage();
                newStage.setTitle("Panel de VISITANTE");
                newStage.setScene(new Scene(root));
                newStage.show();
            } catch (IOException e) {
                System.err.println("Error al cargar la ventana: " + e.getMessage());
            }
        } else {
            errorField.setText("Error al actualizar la contraseña. Inténtelo de nuevo.");
            errorField.setVisible(true);
        }
        Funcionalidades.desconectar(conexion);
    }
}