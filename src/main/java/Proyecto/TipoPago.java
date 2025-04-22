package Proyecto;
import lombok.Getter;
import lombok.ToString;
@Getter @ToString
public enum TipoPago {
    PAYPAL(0.50),
    TARJETA(1),
    BIZUM(1.50);

    final double comision;
    TipoPago(double comision) {
        this.comision = comision;
    }
}
