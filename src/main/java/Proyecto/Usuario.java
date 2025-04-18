package Proyecto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter @ToString
public class Usuario {
    protected int id;
    protected String nombre;
    protected String correo;
    protected String contraseña;
    protected TipoUsuario tipoUsuario;

    public Usuario(String nombre, String correo, String contraseña, TipoUsuario tipoUsuario) {
        generarID();
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
    }

    private static int contador = 0;
    public void generarID(){
        contador++;
        id = contador;
    }
}