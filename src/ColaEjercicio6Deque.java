import java.util.Iterator;
import java.util.NoSuchElementException;

/** Ejercicio 4.5.6: deque enlazada con operaciones en ambos extremos. */
public class ColaEjercicio6Deque implements Iterable<Object> {
    private Nodo primero, ultimo;
    public boolean estaVacia() { return primero == null; }
    public void agregar(Object elemento) { Nodo n = new Nodo(elemento); if (estaVacia()) primero = ultimo = n; else { n.anterior = ultimo; ultimo.siguiente = n; ultimo = n; } }
    public void agregarPrimero(Object elemento) { Nodo n = new Nodo(elemento); if (estaVacia()) primero = ultimo = n; else { n.siguiente = primero; primero.anterior = n; primero = n; } }
    public Object tomar() { if (estaVacia()) throw new NoSuchElementException(); return primero.elemento; }
    public Object ultimoElemento() { if (estaVacia()) throw new NoSuchElementException(); return ultimo.elemento; }
    public void eliminar() { if (estaVacia()) throw new NoSuchElementException(); primero = primero.siguiente; if (primero == null) ultimo = null; else primero.anterior = null; }
    public void eliminarUltimo() { if (estaVacia()) throw new NoSuchElementException(); ultimo = ultimo.anterior; if (ultimo == null) primero = null; else ultimo.siguiente = null; }
    @Override public Iterator<Object> iterator() { return iterar(primero, true); }
    public Iterator<Object> iteradorEnReversa() { return iterar(ultimo, false); }
    private Iterator<Object> iterar(Nodo inicio, boolean haciaSiguiente) { return new Iterator<Object>() { Nodo actual = inicio; public boolean hasNext() { return actual != null; } public Object next() { if (!hasNext()) throw new NoSuchElementException(); Object e = actual.elemento; actual = haciaSiguiente ? actual.siguiente : actual.anterior; return e; } }; }
    private static class Nodo { Object elemento; Nodo anterior, siguiente; Nodo(Object elemento) { this.elemento = elemento; } }
}
