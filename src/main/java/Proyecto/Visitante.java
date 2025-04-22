package Proyecto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@Getter @Setter
public class Visitante extends Usuario {
    private Entradas tipoEntrada;
    private List<Venta> compras;
    private Feria feriaAsistida;
    private double precioTicket;

    public Visitante(String nombre, String correo, int telefono, String contraseña, TipoUsuario tipoUsuario, Entradas tipoEntrada) {
        super(nombre, correo, telefono, contraseña, tipoUsuario);
        this.tipoEntrada = tipoEntrada;
        compras = new ArrayList<>();
    }

    public Visitante(String nombre, String correo, int telefono, String contraseña, TipoUsuario tipoUsuario) {
        super(nombre, correo, telefono, contraseña, tipoUsuario);
        compras = new ArrayList<>();
    }

    public void comprarEntrada(LocalDate fechaVenta, Artista artista, int numObra) {
        Scanner sc = new Scanner(System.in);
        boolean valido = false;
        while (!valido){
            if (tipoEntrada == null){
                System.out.print("Inserte un tipo de entrada [General, VIP, Familiar, Grupo]: ");
                String entradas = sc.nextLine().toUpperCase();
                switch (entradas){
                    case "GENERAL":
                        tipoEntrada = Entradas.GENERAL;
                        valido = true;
                        break;
                    case "VIP":
                        tipoEntrada = Entradas.VIP;
                        valido = true;
                        break;
                    case "FAMILIAR":
                        tipoEntrada = Entradas.FAMILIAR;
                        valido = true;
                        break;
                    case "GRUPO":
                        tipoEntrada = Entradas.GRUPO;
                        valido = true;
                        break;
                    default:
                        System.out.println("ERROR: Valor no valido\n");
                        break;
                }
            } else {
                valido = true;
            }
        }
        compras.add(new Venta(fechaVenta, artista.getObras().get(numObra), this));
    }

    public void Ticket() {
        double precioFinal = 0;
        for (Venta venta : compras){
            precioFinal += venta.getObra().getPrecio();
        }
        precioTicket = precioFinal + tipoEntrada.getPrecio();
    }

    @Override
    public String toString() {
        return "Visitante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono=" + telefono +
                ", contraseña='" + contraseña + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                ", tipoEntrada=" + tipoEntrada +
                ", compras=" + compras +
                ", feriaAsistida=" + (feriaAsistida != null ? feriaAsistida.getNombre() : "No asignada") +
                ", precioTicket=" + precioTicket +
                '}';
    }
}