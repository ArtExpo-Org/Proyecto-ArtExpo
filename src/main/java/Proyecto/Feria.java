package Proyecto;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
@Getter @Setter
public class Feria {
    private int feriaId;
    private String nombre;
    private String ubicacion;
    private Date fechaInicio;
    private Date fechaFin;
}