import java.util.ArrayList;
import java.util.List;

/** Ejercicio 3.5.4: ordena tomos accesibles por el tope de dos repisas-pila. */
public final class PilaEjercicio4Biblioteca {
    private PilaEjercicio4Biblioteca() { }

    /**
     * Transfiere los tomos a la tercera repisa en orden ascendente.
     * Devuelve una lista vacía si el siguiente tomo requerido no está en un tope.
     */
    public static List<Integer> ordenar(Pila primera, Pila segunda, int totalTomos) {
        List<Integer> terceraRepisa = new ArrayList<>();
        for (int esperado = 1; esperado <= totalTomos; esperado++) {
            if (!primera.estaVacia() && (Integer) primera.top() == esperado) {
                terceraRepisa.add((Integer) primera.pop());
            } else if (!segunda.estaVacia() && (Integer) segunda.top() == esperado) {
                terceraRepisa.add((Integer) segunda.pop());
            } else {
                return List.of();
            }
        }
        return terceraRepisa;
    }
}
