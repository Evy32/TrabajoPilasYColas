import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/** Ejercicio 4.5.8: asigna consolas, respetando la cola de solicitudes y el orden de salida del almacén. */
public final class ColaEjercicio8Almacen {
    public record Consola(String codigo, String descripcion) { }
    public record Solicitud(String tienda, int cantidad) { }
    public record Asignacion(String tienda, List<String> codigos) { }
    private ColaEjercicio8Almacen() { }

    public static List<Asignacion> asignar(Cola inventario, Cola solicitudes) {
        List<Asignacion> resultado = new ArrayList<>();
        while (!solicitudes.estaVacia()) {
            Solicitud solicitud = (Solicitud) solicitudes.tomar(); solicitudes.eliminar();
            List<String> codigos = new ArrayList<>();
            for (int i = 0; i < solicitud.cantidad(); i++) {
                if (inventario.estaVacia()) throw new NoSuchElementException("Inventario insuficiente para " + solicitud.tienda());
                Consola consola = (Consola) inventario.tomar(); inventario.eliminar();
                codigos.add(consola.codigo());
            }
            resultado.add(new Asignacion(solicitud.tienda(), List.copyOf(codigos)));
        }
        return resultado;
    }
}
