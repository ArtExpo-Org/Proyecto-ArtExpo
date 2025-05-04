package Proyecto;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class Stand extends GeneradorID {
    private int numero;
    private Estado estado;
    private Feria feria;
    private Artista artistaAsignado;

    public Stand(int numero, Feria feria) {
        generarID();
        this.numero = numero;
        estado = Estado.DISPONIBLE;
        this.feria = feria;
    }

    public static int obtenerStandDisponible(Feria feria){
        for (Stand stand : feria.getStands()){
            if (stand.getEstado().equals(Estado.DISPONIBLE)){
                return stand.id;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Stand{" +
                "standId=" + id +
                ", numero=" + numero +
                ", estado=" + estado +
                ", feria=" + feria.getNombre() +
                ", artistaAsignado=" + artistaAsignado.getNombre() +
                '}';
    }
}