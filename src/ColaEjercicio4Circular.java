import java.util.Iterator;
import java.util.NoSuchElementException;

/** Ejercicio 4.5.4: cola circular enlazada que reutiliza nodos vacíos. */
public class ColaEjercicio4Circular implements Iterable<Object> {
    private Nodo primeroDisponible;
    private Nodo primeroOcupado;
    private int tamanio;

    public boolean estaVacia() { return tamanio == 0; }

    /** Marca los nodos como disponibles sin eliminarlos. */
    public void vaciar() {
        if (primeroDisponible == null) return;
        Nodo actual = primeroDisponible;
        do { actual.elemento = null; actual = actual.siguiente; } while (actual != primeroDisponible);
        primeroOcupado = primeroDisponible;
        tamanio = 0;
    }

    public void agregar(Object elemento) {
        if (primeroDisponible == null) {
            Nodo nuevo = new Nodo(); nuevo.siguiente = nuevo;
            primeroDisponible = primeroOcupado = nuevo;
        }
        if (tamanio == capacidad()) insertarNodo();
        primeroDisponible.elemento = elemento;
        primeroDisponible = primeroDisponible.siguiente;
        tamanio++;
    }

    public Object tomar() {
        if (estaVacia()) throw new NoSuchElementException("La cola está vacía");
        return primeroOcupado.elemento;
    }

    public void eliminar() {
        if (estaVacia()) throw new NoSuchElementException("La cola está vacía");
        primeroOcupado.elemento = null;
        primeroOcupado = primeroOcupado.siguiente;
        tamanio--;
    }

    @Override public Iterator<Object> iterator() {
        return new Iterator<Object>() {
            private Nodo actual = primeroOcupado;
            private int restantes = tamanio;
            public boolean hasNext() { return restantes > 0; }
            public Object next() { if (!hasNext()) throw new NoSuchElementException(); restantes--; Object e = actual.elemento; actual = actual.siguiente; return e; }
        };
    }

    private int capacidad() {
        if (primeroDisponible == null) return 0;
        int total = 1; for (Nodo actual = primeroDisponible.siguiente; actual != primeroDisponible; actual = actual.siguiente) total++;
        return total;
    }
    private void insertarNodo() {
        Nodo anterior = primeroDisponible;
        while (anterior.siguiente != primeroDisponible) anterior = anterior.siguiente;
        Nodo nuevo = new Nodo();
        nuevo.siguiente = primeroDisponible;
        anterior.siguiente = nuevo;
        // La posición disponible recién creada queda al final lógico de la cola.
        primeroDisponible = nuevo;
    }
    private static class Nodo { private Object elemento; private Nodo siguiente; }
}
