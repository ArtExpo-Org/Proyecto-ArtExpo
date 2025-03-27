package Proyecto;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class Obra {
    private int obraId;
    private String titulo;
    private int anioCreacion;
    private String tecnica;
    private double precio;
    private int artistaId;
}