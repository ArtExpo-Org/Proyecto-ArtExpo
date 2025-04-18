package Proyecto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;
@Getter @Setter @ToString
public class Venta {
    private int ventaId;
    private double precioFinal;
    private LocalDate fechaVenta;
    private Obra obra;
    private Visitante comprador;

    public Venta(double precioFinal, LocalDate fechaVenta, Obra obra, Visitante comprador) {
        generarID();
        this.precioFinal = precioFinal;
        this.fechaVenta = fechaVenta;
        this.obra = obra;
        this.comprador = comprador;
    }

    private static int contador = 0;
    public void generarID(){
        contador++;
        ventaId = contador;
    }
}