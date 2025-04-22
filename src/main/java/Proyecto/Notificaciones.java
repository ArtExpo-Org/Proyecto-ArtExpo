package Proyecto;
public class Notificaciones {
    public static void enviarEmail(Usuario usuario, String asunto, String mensaje) {
        System.out.println("Enviando email a " + usuario.getCorreo() + " con el asunto: " + asunto);
        System.out.println("Cuerpo del mensaje: " + mensaje);
    }

    public static void notificarVenta(Usuario artista, String obraTitulo, double precio) {
        String mensaje = "¡Felicidades! Tu obra '" + obraTitulo + "' ha sido vendida por " + precio + "€";
        enviarEmail(artista, "Venta de obra", mensaje);
    }
}