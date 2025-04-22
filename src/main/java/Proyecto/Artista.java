package Proyecto;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
@Getter @Setter
public class Artista extends Usuario {
    private String biografia;
    private List<Obra> obras;
    private Stand standAsignado;

    public Artista(String nombre, String correo, int telefono, String contraseña, TipoUsuario tipoUsuario, String biografia) {
        super(nombre, correo, telefono, contraseña, tipoUsuario);
        this.biografia = biografia;
        obras = new ArrayList<>();
    }

    public void agregarObra(String titulo, int añoCreacion, String tecnica, double precio){
        obras.add(new Obra(titulo, añoCreacion, tecnica, precio, this));
    }

    public void eliminarObra(Obra obra){
        obras.remove(obra);
    }

    public void filtroTecnica(String tecnica){
        List<Obra> obrasFiltradas = new ArrayList<>();
        for (Obra obra : obras){
            if (obra.getTecnica().equals(tecnica)){
                obrasFiltradas.add(obra);
            }
        }
        System.out.println("Obras de " + this.nombre + " con la tecnica '" + tecnica + "': " + obrasFiltradas);
    }

    public void filtroAño(int año) {
        List<Obra> obrasFiltradas = new ArrayList<>();
        for (Obra obra : obras) {
            if (obra.getAñoCreacion() == año) {
                obrasFiltradas.add(obra);
            }
        }
        System.out.println("Obras de " + this.nombre + " creadas en " + año + ": " + obrasFiltradas);
    }

    public void filtroPrecioDescendente() {
        TreeSet<Double> preciosFiltrados = new TreeSet<>();
        List<Obra> obrasFiltradas = new ArrayList<>();
        for (Obra obra : obras) {
            preciosFiltrados.add(obra.getPrecio());
        }
        for (Double precio : preciosFiltrados){
            for (Obra obra : obras){
                if (obra.getPrecio() == precio){
                    obrasFiltradas.add(obra);
                }
            }
        }
        System.out.println("Obras de " + this.nombre + " filtradas por precio: " + obrasFiltradas);
    }

    @Override
    public String toString() {
        return "Artista{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono=" + telefono +
                ", contraseña='" + contraseña + '\'' +
                ", tipoUsuario=" + tipoUsuario.name() +
                ", biografia='" + biografia + '\'' +
                ", obras=" + obras +
                ", standAsignado=" + standAsignado.getNumero() +
                '}';
    }
}