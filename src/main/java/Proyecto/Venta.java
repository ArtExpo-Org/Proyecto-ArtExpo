package Proyecto;
import java.math.BigDecimal;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class Venta {
    private int ventaId;
    private BigDecimal precioFinal;
    private String nombreComprador;
    private Date fechaVenta;
    private int obraId;
}