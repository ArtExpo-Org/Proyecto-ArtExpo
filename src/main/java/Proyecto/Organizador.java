package Proyecto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter @Setter
public class Organizador extends Usuario {
    private String rol;
    private List<Feria> feriasOrganizadas;

    public Organizador(String nombre, String correo, int telefono, String contraseña, TipoUsuario tipoUsuario, String rol) {
        super(nombre, correo, telefono, contraseña, tipoUsuario);
        feriasOrganizadas = new ArrayList<>();
        this.rol = rol;
    }

    public void agregarFeria(Feria feria){
        feriasOrganizadas.add(feria);
        feria.asignarUsuario(this);
    }

    public void eliminarFeria(Feria feria){
        feriasOrganizadas.remove(feria);
        feria.retirarUsuario(this);
    }

    @Override
    public String toString() {
        return "Organizador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono=" + telefono +
                ", contraseña='" + contraseña + '\'' +
                ", tipoUsuario=" + tipoUsuario.name() +
                ", rol='" + rol + '\'' +
                '}';
    }
}