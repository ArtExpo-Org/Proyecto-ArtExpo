package Proyecto;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class Obra extends GeneradorID {
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

    @Override
    public String toString() {
        return "Obra{" +
                "obraId=" + id +
                ", titulo='" + titulo + '\'' +
                ", añoCreacion=" + añoCreacion +
                ", tecnica='" + tecnica + '\'' +
                ", precio=" + precio +
                ", artista=" + artista.getNombre() +
                '}';
    }
}