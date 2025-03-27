package Proyecto;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class Venta {
    private int ventaId;
    private double precioFinal;
    private String nombreComprador;
    private String fechaVenta;
    private int obraId;
}