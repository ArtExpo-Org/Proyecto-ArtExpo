package Proyecto;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Getter @Setter
public class Evento extends GeneradorID {
    private String nombre;
    private TipoEvento tipoEvento;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Feria feria;
    private List<Usuario> participantes;

    public Evento(String nombre, TipoEvento tipoEvento, LocalTime horaInicio, LocalTime horaFin, Feria feria) {
        generarID();
        this.nombre = nombre;
        this.tipoEvento = tipoEvento;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.feria = feria;
        this.participantes = new ArrayList<>();
    }

    public void agregarParticipante(Usuario usuario) {
        participantes.add(usuario);
    }

    public void eliminarParticipante(Usuario usuario) {
        participantes.remove(usuario);
    }

    public void notificarInicio() {
        for (Usuario usuario : feria.getListaUsuarios()) {
            if (usuario.getTipoUsuario() == TipoUsuario.VISITANTE) {
                Notificaciones.enviarEmail(usuario,
                    "Evento a punto de comenzar: " + nombre,
                    "El evento '" + nombre + "' (" + tipoEvento + ") comenzará en breve. ¡Te esperamos!");
            }
        }
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipoEvento=" + tipoEvento +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", fecha=" + feria.getFechaInicio() + " a " + feria.getFechaFin() +
                ", feria=" + feria.getNombre() +
                ", participantes=" + participantes.size() +
                '}';
    }
}