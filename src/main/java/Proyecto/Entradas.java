package Proyecto;
import lombok.Getter;
import lombok.ToString;
@Getter @ToString
public enum Entradas {
    GENERAL(15),
    VIP(45),
    FAMILIAR(30),
    GRUPO(120);

    final int precio;
    Entradas(int precio) {
        this.precio = precio;
    }
}