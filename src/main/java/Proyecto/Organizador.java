package Proyecto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Getter @Setter @ToString
public class Organizador extends Usuario {
    private String rol;
    private List<Feria> feriasOrganizadas;

    public Organizador(String rol) {
        this.rol = rol;
        feriasOrganizadas = new ArrayList<>();
    }

    public void agregarFeria(Feria feria){
        feriasOrganizadas.add(feria);
        feria.asignarResponsable(this);
    }

    public void eliminarFeria(Feria feria){
        feriasOrganizadas.remove(feria);
        feria.retirarResponsable();
    }
}