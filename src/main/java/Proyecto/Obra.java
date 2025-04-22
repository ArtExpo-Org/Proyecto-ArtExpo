package Proyecto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter
public class Obra {
    private int obraId;
    private String titulo;
    private int añoCreacion;
    private String tecnica;
    private double precio;
    private Artista artista;

    public Obra(String titulo, int añoCreacion, String tecnica, double precio, Artista artista) {
        generarID();
        this.titulo = titulo;
        this.añoCreacion = añoCreacion;
        this.tecnica = tecnica;
        this.precio = precio;
        this.artista = artista;
    }

    private static int contador = 0;
    public void generarID(){
        contador++;
        obraId = contador;
    }

    @Override
    public String toString() {
        return "Obra{" +
                "obraId=" + obraId +
                ", titulo='" + titulo + '\'' +
                ", añoCreacion=" + añoCreacion +
                ", tecnica='" + tecnica + '\'' +
                ", precio=" + precio +
                ", artista=" + artista.getNombre() +
                '}';
    }
}