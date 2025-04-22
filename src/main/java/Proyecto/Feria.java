package Proyecto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter @Setter @ToString
public class Feria {
    private int feriaId;
    private String nombre;
    private String ubicacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Stand> stands;
    private Organizador responsable;

    public Feria(String nombre, String ubicacion, LocalDate fechaInicio, LocalDate fechaFin) {
        generarID();
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        stands = new ArrayList<>();
    }

    private static int contador = 0;
    public void generarID(){
        contador++;
        feriaId = contador;
    }

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
        stand.setArtistaAsignado(null);
    }

    public void asignarResponsable(Organizador organizador){
        responsable = organizador;
    }

    public void retirarResponsable(){
        responsable = null;
    }
}