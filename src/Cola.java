import java.util.NoSuchElementException;

/**
 * Cola de objetos implementada mediante nodos enlazados.
 * Los elementos se retiran en el mismo orden en que se agregan.
 */
public class Cola {
    private Nodo primero;
    private Nodo ultimo;

    /** Agrega un elemento al final de la cola. */
    public void agregar(Object elemento) {
        Nodo nuevo = new Nodo(elemento);

        if (estaVacia()) {
            primero = nuevo;
        } else {
            ultimo.siguiente = nuevo;
        }
        ultimo = nuevo;
    }

    /**
     * Devuelve el primer elemento de la cola sin retirarlo.
     *
     * @throws NoSuchElementException si la cola está vacía
     */
    public Object tomar() {
        if (estaVacia()) {
            throw new NoSuchElementException("La cola está vacía");
        }
        return primero.elemento;
    }

    /**
     * Retira el primer elemento de la cola.
     *
     * @throws NoSuchElementException si la cola está vacía
     */
    public void eliminar() {
        if (estaVacia()) {
            throw new NoSuchElementException("La cola está vacía");
        }

        primero = primero.siguiente;
        if (primero == null) {
            ultimo = null;
        }
    }

    /** Indica si la cola no contiene elementos. */
    public boolean estaVacia() {
        return primero == null;
    }

    /** Nodo interno de la lista enlazada usada por la cola. */
    private static class Nodo {
        private final Object elemento;
        private Nodo siguiente;

        private Nodo(Object elemento) {
            this.elemento = elemento;
        }
    }
}
