package Proyecto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
@Getter @Setter @ToString
public class Visitante extends Usuario {
    private Entradas tipoEntrada;
    private List<Venta> compras;
    private Feria feriaAsistida;

    public Visitante(String nombre, String correo, String contraseña, TipoUsuario tipoUsuario, Entradas tipoEntrada, Feria feriaAsistida) {
        super(nombre, correo, contraseña, tipoUsuario);
        this.tipoEntrada = tipoEntrada;
        this.feriaAsistida = feriaAsistida;
    }

    public void comprarEntrada(LocalDate fechaVenta, Artista artista, int numObra) {
        compras.add(new Venta(fechaVenta, artista.getObras().get(numObra), this));
    }

    public void Ticket() {
        double precioFinal = 0;
        for (Venta venta : compras){
            precioFinal += venta.getObra().getPrecio();
        }
        System.out.println(precioFinal);
    }
}