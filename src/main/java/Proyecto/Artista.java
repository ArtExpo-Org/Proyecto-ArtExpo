package Proyecto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;
@Getter @Setter @ToString
public class Artista extends Usuario {
    private String biografia;
    private List<Obra> obras;
    private Stand standAsignado;

    public Artista(String nombre, String correo, String contraseña, TipoUsuario tipoUsuario, Stand standAsignado, String biografia) {
        super(nombre, correo, contraseña, tipoUsuario);
        this.standAsignado = standAsignado;
        this.biografia = biografia;
        obras = new ArrayList<>();
    }

    public void agregarObra(String titulo, int añoCreacion, String tecnica, double precio){
        obras.add(new Obra(titulo, añoCreacion, tecnica, precio, this));
    }

    public void eliminarObra(Obra obra){
        obras.remove(obra);
    }
}