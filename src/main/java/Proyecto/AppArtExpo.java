package Proyecto;
public class AppArtExpo {
    public static void main(String[] args) {
        // Crear un organizador y una feria
        Organizador organizador1 = new Organizador();
        Organizador organizador2 = new Organizador();
        Feria feria1 = new Feria();
        Feria feria2 = new Feria();
        organizador1.agregarFeria(feria1);

        // Añadir un stand a la feria
        feria1.agregarStand(new Stand());

        // Un artista se registra y reserva el stand
        Artista artista = new Artista();
        feria1.reservarStand(artista);

        // El artista añade una obra
        artista.agregarObra(new Obra());

        // Un visitante compra una entrada
        Visitante visitante = new Visitante();
        visitante.comprarEntrada();
    }
}
