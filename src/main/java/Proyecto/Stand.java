package Proyecto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter @ToString
public class Stand {
    private int standId;
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

    private static int contador = 0;
    public void generarID(){
        contador++;
        standId = contador;
    }

    public static int obtenerStandDisponible(Feria feria){
        for (Stand stand : feria.getStands()){
            if (stand.getEstado().equals(Estado.DISPONIBLE)){
                return stand.getStandId();
            }
        }
        return 0;
    }
}