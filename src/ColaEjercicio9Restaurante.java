import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** Ejercicio 4.5.9: asignación FIFO de reservaciones por primera mesa o mejor ajuste. */
public final class ColaEjercicio9Restaurante {
    public record Mesa(String codigo, int capacidad) { }
    public record Reserva(String nombre, int comensales) { }
    public record Asignacion(String nombre, String codigoMesa) { }
    private ColaEjercicio9Restaurante() { }

    public static List<Asignacion> asignarPrimeraMesa(List<Mesa> mesas, Cola reservas) { return asignar(mesas, reservas, false); }
    public static List<Asignacion> asignarMejorOcupacion(List<Mesa> mesas, Cola reservas) { return asignar(mesas, reservas, true); }

    private static List<Asignacion> asignar(List<Mesa> mesas, Cola reservas, boolean mejorAjuste) {
        List<Mesa> disponibles = new ArrayList<>(mesas);
        List<Asignacion> resultado = new ArrayList<>();
        while (!reservas.estaVacia()) {
            Reserva reserva = (Reserva) reservas.tomar(); reservas.eliminar();
            Mesa mesa = disponibles.stream().filter(m -> m.capacidad() >= reserva.comensales())
                    .sorted(mejorAjuste ? Comparator.comparingInt(Mesa::capacidad) : (a, b) -> 0).findFirst().orElse(null);
            if (mesa != null) { resultado.add(new Asignacion(reserva.nombre(), mesa.codigo())); disponibles.remove(mesa); }
        }
        return resultado;
    }
}
