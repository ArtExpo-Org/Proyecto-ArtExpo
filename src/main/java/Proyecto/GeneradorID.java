package Proyecto;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public abstract class GeneradorID {
    protected int id;
    private static int contador = 0;
    public void generarID(){
        contador++;
        id = contador;
    }
}