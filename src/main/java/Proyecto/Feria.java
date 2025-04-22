package Proyecto;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter @Setter
public class Feria {
    private int feriaId;
    private String nombre;
    private String ubicacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Stand> stands;
    private List<Usuario> listaUsuarios;
    private Organizador responsable;

    public Feria(String nombre, String ubicacion, LocalDate fechaInicio, LocalDate fechaFin) {
        generarID();
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        stands = new ArrayList<>();
        listaUsuarios = new ArrayList<>();
    }

    private static int contador = 0;
    public void generarID(){
        contador++;
        feriaId = contador;
    }

    // region Stands
    public void agregarStand(Stand stand){
        stands.add(stand);
    }

    public void eliminarStand(Stand stand){
        stands.remove(stand);
    }

    public void reservarStand(Artista artista){
        int id = Stand.obtenerStandDisponible(this);
        if (id != 0){
            for (Stand stand : stands){
                if (stand.getStandId() == id){
                    artista.setStandAsignado(stand);
                    stand.setArtistaAsignado(artista);
                    stand.setEstado(Estado.OCUPADO);
                }
            }
        } else {
            System.out.println("No hay ningun stand disponible en esta feria. Lo sentimos.");
        }
    }

    public void liberarStand(Stand stand){
        for (Usuario usuario : listaUsuarios){
            if (((Artista) usuario).getStandAsignado() == stand){
                ((Artista) usuario).setStandAsignado(null);
            }
        }
        stand.setArtistaAsignado(null);
    }
    // endregion

    // region Usuarios
    public void asignarUsuario(Usuario usuario){
        boolean valido = true;
        for (Usuario usuarioLista : listaUsuarios){
            if (usuario.correo.equals(usuarioLista.correo)){
                System.out.println("ERROR: El correo " + usuario.correo + " ya existe");
                valido = false;
                break;
            }
        }
        if (valido){
            if (usuario.getTipoUsuario() == TipoUsuario.VISITANTE) {
                Visitante visitante = (Visitante) usuario;
                visitante.setFeriaAsistida(this);
            } else if (usuario.getTipoUsuario() == TipoUsuario.ORGANIZADOR) {
                responsable = (Organizador) usuario;
            }
            listaUsuarios.add(usuario);
        }
    }

    public void retirarUsuario(Usuario usuario){
        if (usuario.getTipoUsuario() == TipoUsuario.VISITANTE) {
            Visitante visitante = (Visitante) usuario;
            visitante.setFeriaAsistida(null);
        } else if (usuario.getTipoUsuario() == TipoUsuario.ORGANIZADOR) {
            responsable = null;
        }
        listaUsuarios.remove(usuario);
    }
    // endregion

    @Override
    public String toString() {
        return "Feria{" +
                "feriaId=" + feriaId +
                ", nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", responsable=" + responsable.getNombre() +
                '}';
    }
}