package Proyecto;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class Obra {
    private int obraId;
    private String titulo;
    private int anioCreacion;
    private String tecnica;
    private BigDecimal precio;
    private int artistaId;
}