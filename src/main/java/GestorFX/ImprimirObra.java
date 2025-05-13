package GestorFX;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class ImprimirObra {
    private String titulo;
    private String nombreArtista;
    private int añoCreacion;
    private String tecnica;
    private double precio;

    public ImprimirObra(String titulo, String nombreArtista, int añoCreacion, String tecnica, double precio) {
        this.titulo = titulo;
        this.nombreArtista = nombreArtista;
        this.añoCreacion = añoCreacion;
        this.tecnica = tecnica;
        this.precio = precio;
    }

}
