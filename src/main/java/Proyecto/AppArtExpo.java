package Proyecto;
import java.time.LocalDate;
public class AppArtExpo {
    public static void main(String[] args) {
        // Crear un organizador y una feria
        Organizador organizador1 = new Organizador("Carlos", "carlos@artexpo.com", "pass123", TipoUsuario.ORGANIZADOR, "Director/a de Eventos");
        Organizador organizador2 = new Organizador("Alicia", "alicia@artexpo.com", "pass456", TipoUsuario.ORGANIZADOR, "Director/a de Eventos");
        Feria feria1 = new Feria("Feria Internacional de Arte", "Madrid", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 5));
        Feria feria2 = new Feria("Feria de Arte Contemporáneo", "Barcelona", LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 5));
        organizador1.agregarFeria(feria1);
        organizador2.agregarFeria(feria2);

        // Añadir un stand a la feria
        feria1.agregarStand(new Stand(1, feria1));

        // Un artista se registra y reserva el stand
        Artista artista = new Artista("Selena", "SELE81@artDigital.com", "pass789", TipoUsuario.ARTISTA, null, "Artista digital con experiencia en exposiciones internacionales.");
        feria1.reservarStand(artista);

        // El artista añade una obra
        artista.agregarObra("La noche estrellada", 1889, "Óleo sobre lienzo", 100000);

        // Un visitante compra una entrada
        Visitante visitante = new Visitante("Almudena", "alma_12@outlook.com", "pass101", TipoUsuario.VISITANTE, Entradas.GENERAL, feria1);
        visitante.comprarEntrada(LocalDate.now(), artista, 0);
        visitante.Ticket();
    }
}