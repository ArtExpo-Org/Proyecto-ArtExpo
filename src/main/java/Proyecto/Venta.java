package Proyecto;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
@Getter @Setter
public class Venta extends GeneradorID {
    private LocalDate fechaVenta;
    private Obra obra;
    private Visitante comprador;

    public Venta(LocalDate fechaVenta, Obra obra, Visitante comprador) {
        generarID();
        this.fechaVenta = fechaVenta;
        this.obra = obra;
        this.comprador = comprador;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "ventaId=" + id +
                ", fechaVenta=" + fechaVenta +
                ", obra=" + obra.getTitulo() +
                ", comprador=" + comprador.getNombre() +
                '}';
    }
}