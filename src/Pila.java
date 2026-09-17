import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Pila de objetos implementada mediante nodos enlazados.
 * El primer nodo siempre representa la cima de la pila.
 */
public class Pila {
    private Nodo cima;

    /** Agrega un elemento a la cima de la pila. */
    public void push(Object elemento) {
        cima = new Nodo(elemento, cima);
    }

    /**
     * Retira y devuelve el elemento que está en la cima.
     *
     * @throws EmptyStackException si la pila está vacía
     */
    public Object pop() {
        if (cima == null) {
            throw new EmptyStackException();
        }

        Object elemento = cima.elemento;
        cima = cima.siguiente;
        return elemento;
    }

    /**
     * Devuelve un iterador que recorre la pila desde la cima hasta la base.
     */
    public Iterator<Object> iterador() {
        return new Iterator<Object>() {
            private Nodo actual = cima;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Object elemento = actual.elemento;
                actual = actual.siguiente;
                return elemento;
            }
        };
    }

    /** Nodo interno de la lista enlazada usada por la pila. */
    private static class Nodo {
        private final Object elemento;
        private final Nodo siguiente;

        private Nodo(Object elemento, Nodo siguiente) {
            this.elemento = elemento;
            this.siguiente = siguiente;
        }
    }
}
