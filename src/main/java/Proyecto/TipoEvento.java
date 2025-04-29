package Proyecto;
import lombok.Getter;
import lombok.ToString;
@Getter @ToString
public enum TipoEvento {
    CHARLA,             // Presentación temática o conferencia
    TALLER,             // Actividad práctica participativa
    DEMOSTRACION,       // Exhibición de técnicas artísticas
    NETWORKING,         // Espacio para contactos y relaciones
    SUBASTA,            // Venta competitiva de obras
    FIRMAS,             // Artistas firman obras o material
}