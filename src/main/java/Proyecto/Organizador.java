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

    public Organizador(String nombre, String correo, String contraseña, TipoUsuario tipoUsuario, String rol) {
        super(nombre, correo, contraseña, tipoUsuario);
        this.rol = rol;
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