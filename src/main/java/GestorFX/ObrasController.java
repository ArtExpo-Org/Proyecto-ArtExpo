package GestorFX;
import Proyecto.TipoPago;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
public class ObrasController {
    // region Atributos
    static String usr;
    @FXML
    private Text helloUsr;
    @FXML
    private TableView<ImprimirObra> obrasTable;
    @FXML
    private TableColumn<ImprimirObra, String> tituloColumn;
    @FXML
    private TableColumn<ImprimirObra, String> artistaColumn;
    @FXML
    private TableColumn<ImprimirObra, Integer> anioColumn;
    @FXML
    private TableColumn<ImprimirObra, String> tecnicaColumn;
    @FXML
    private TableColumn<ImprimirObra, Double> precioColumn;
    @FXML
    private RadioButton tarjetaRadio;
    @FXML
    private RadioButton paypalRadio;
    @FXML
    private RadioButton bizumRadio;
    @FXML
    private Text errorField;
    @FXML
    private Button justificanteButton;
    @FXML
    // endregion

    public void initialize(){
        Connection conexion = Funcionalidades.conectar();
        tituloColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitulo()));
        artistaColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombreArtista()));
        anioColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getAñoCreacion()).asObject());
        tecnicaColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTecnica()));
        precioColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPrecio()).asObject());

        obrasTable.setItems(Funcionalidades.obtenerObras(conexion));

        // Agrupo los RadioButtons para que solo se pueda seleccionar uno
        ToggleGroup grupoPago = new ToggleGroup();
        tarjetaRadio.setToggleGroup(grupoPago);
        paypalRadio.setToggleGroup(grupoPago);
        bizumRadio.setToggleGroup(grupoPago);
    }

    public void onCompraButtonClick(){
        Connection conexion = Funcionalidades.conectar();
        ImprimirObra obraSeleccionada = obrasTable.getSelectionModel().getSelectedItem();
        if (obraSeleccionada != null) {
            TipoPago metodoPago = null;
            if (tarjetaRadio.isSelected()) {
                metodoPago = TipoPago.TARJETA;
            } else if (paypalRadio.isSelected()) {
                metodoPago = TipoPago.PAYPAL;
            } else if (bizumRadio.isSelected()) {
                metodoPago = TipoPago.BIZUM;
            }

            Funcionalidades.realizarCompra(conexion ,obraSeleccionada, metodoPago, usr);
            errorField.setText("Compra realizada con éxito.");
            errorField.setVisible(true);
            justificanteButton.setDisable(false);
        } else {
            errorField.setText("Por favor, selecciona una obra para comprar.");
            errorField.setVisible(true);
        }
    }

    public void onJustificanteButtonClick() {
        String carpeta = "src/main/resources/Justificantes Artexpo";
        File directorio = new File(carpeta);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        String nombreArchivo = carpeta + "/Justificante_" + usr + ".pdf";

        try {
            File archivo = new File(nombreArchivo);
            if (archivo.createNewFile()) {
                errorField.setText("Justificante generado correctamente");
            } else {
                errorField.setText("El justificante ya existe");
            }
            errorField.setVisible(true);
            justificanteButton.setDisable(true);
        } catch (IOException e) {
            errorField.setText("Error al crear el justificante");
            errorField.setVisible(true);
            e.printStackTrace();
        }
    }

    public void recibirNombre(String usernameorEmail){
        usr = usernameorEmail.toUpperCase();
        helloUsr.setText("¡Bienvenido/a " + usr + "!");
    }
}