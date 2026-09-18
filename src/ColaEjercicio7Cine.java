import java.util.NoSuchElementException;

/** Ejercicio 4.5.7: cola con prioridad para asiduos y FIFO dentro de cada grupo. */
public class ColaEjercicio7Cine {
    private final Cola asiduos = new Cola();
    private final Cola ocasionales = new Cola();
    private int cantidadAsiduos, cantidadOcasionales;
    public void agregar(String nombre, boolean esAsiduo) { if (esAsiduo) { asiduos.agregar(nombre); cantidadAsiduos++; } else { ocasionales.agregar(nombre); cantidadOcasionales++; } }
    public String tomar() { if (estaVacia()) throw new NoSuchElementException("No hay clientes"); return (String) (!asiduos.estaVacia() ? asiduos.tomar() : ocasionales.tomar()); }
    public String eliminar() { String cliente = tomar(); if (!asiduos.estaVacia()) { asiduos.eliminar(); cantidadAsiduos--; } else { ocasionales.eliminar(); cantidadOcasionales--; } return cliente; }
    public int numeroAsiduos() { return cantidadAsiduos; }
    public int numeroOcasionales() { return cantidadOcasionales; }
    public boolean estaVacia() { return cantidadAsiduos + cantidadOcasionales == 0; }
}
