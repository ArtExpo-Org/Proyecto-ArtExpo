package Proyecto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
@Getter @Setter @ToString
public class Visitante extends Usuario {
    private String tipoEntrada;
    private List<Venta> compras;
    private Feria feriaAsistida;

    public Visitante(String nombre, String correo, String contraseña, TipoUsuario tipoUsuario, String tipoEntrada, Feria feriaAsistida) {
        super(nombre, correo, contraseña, tipoUsuario);
        this.tipoEntrada = tipoEntrada;
        this.feriaAsistida = feriaAsistida;
    }

    public void comprarEntrada(Obra obra, String tipoEntrada){
        compras.add(new Venta());
    }
}