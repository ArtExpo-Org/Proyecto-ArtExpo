package Proyecto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Objects;
@Getter @Setter @ToString
public class Usuario {
    protected int id;
    protected String nombre;
    protected String correo;
    protected int telefono;
    protected String contraseña;
    protected TipoUsuario tipoUsuario;

    public Usuario(String nombre, String correo, int telefono, String contraseña, TipoUsuario tipoUsuario) {
        generarID();
        this.nombre = nombre;
        autenticarCorreo(correo);
        autenticarTelefono(telefono);
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
    }

    private static int contador = 0;
    public void generarID(){
        contador++;
        id = contador;
    }

    public void autenticarCorreo(String correo){
        if (correo.matches("[^@]+@[^@]+\\.[a-z]{2,}")){
            this.correo = correo;
        } else {
            this.correo = null;
        }
    }

    public void autenticarTelefono(int telefono){
        String numTelefono = String.valueOf(telefono);
        if (numTelefono.length() == 9) {
            this.telefono = telefono;
        } else {
            this.telefono = 0;
        }
    }
}