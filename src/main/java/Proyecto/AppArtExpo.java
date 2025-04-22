package Proyecto;
import java.time.LocalDate;
public class AppArtExpo {
    public static void main(String[] args) {
        // Crear un organizador y una feria
        Organizador organizador1 = new Organizador("Carlos", "carlos@artexpo.com", 613538395, "pass123", TipoUsuario.ORGANIZADOR, "Director/a de Eventos");
        Organizador organizador2 = new Organizador("Alicia", "alicia@artexpo.com", 615950432, "pass456", TipoUsuario.ORGANIZADOR, "Director/a de Eventos");
        Feria feria1 = new Feria("Feria Internacional de Arte", "Madrid", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 5));
        Feria feria2 = new Feria("Feria de Arte Contemporáneo", "Barcelona", LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 5));
        organizador1.agregarFeria(feria1);
        organizador2.agregarFeria(feria2);

        // Añadir un stand a la feria
        feria1.agregarStand(new Stand(1, feria1));

        // Un artista se registra y reserva el stand
        Artista artista = new Artista("Selena", "SELE81@artDigital.com", 619493323, "pass789", TipoUsuario.ARTISTA, "Artista digital con experiencia en exposiciones internacionales.");
        feria1.asignarUsuario(artista);
        feria1.reservarStand(artista);

        // El artista añade una obra
        artista.agregarObra("La noche estrellada", 2013, "Óleo sobre lienzo", 500);
        artista.agregarObra("La chica de la perla", 2002, "Óleo sobre lienzo", 1200);
        artista.agregarObra("El grito", 2025, "Óleo sobre cartón", 800);

        // Crear un par de visitantes
        Visitante visitante1 = new Visitante("Almudena", "alma_12@outlook.com", 610843191, "pass101", TipoUsuario.VISITANTE);
        Visitante visitante2 = new Visitante("Almudena", "alma_12@outlook.com", 610843191, "pass101", TipoUsuario.VISITANTE);
        feria1.asignarUsuario(visitante1);
        feria1.asignarUsuario(visitante2);

        // Un visitante compra un par de entradas
        visitante1.comprarEntrada(LocalDate.now(), artista, 0);
        visitante1.comprarEntrada(LocalDate.now(), artista, 2);
        visitante1.Ticket();

        // Mostrar las ferias
        System.out.println(feria1.getListaUsuarios());
        System.out.println(feria2.getListaUsuarios());

        // Filtro de obras
        artista.filtroTecnica("Óleo sobre lienzo");
        artista.filtroAño(2013);
        artista.filtroPrecioDescendente();
    }
}