import java.util.Iterator;

/** Ejercicio 4.5.1: operaciones complementarias para Cola. */
public final class ColaEjercicio1Operaciones {
    private ColaEjercicio1Operaciones() { }

    /** Crea una cola nueva con el orden inverso, sin modificar la original. */
    public static Cola invertir(Cola original) {
        Pila auxiliar = new Pila();
        Iterator<Object> it = original.iterador();
        while (it.hasNext()) auxiliar.push(it.next());
        Cola invertida = new Cola();
        while (!auxiliar.estaVacia()) invertida.agregar(auxiliar.pop());
        return invertida;
    }

    /** Devuelve una cola con los elementos de primera seguidos por los de segunda. */
    public static Cola concatenar(Cola primera, Cola segunda) {
        Cola resultado = new Cola();
        copiar(primera, resultado); copiar(segunda, resultado);
        return resultado;
    }

    /** Alterna un elemento de cada cola y conserva los sobrantes al final. */
    public static Cola intercalar(Cola primera, Cola segunda) {
        Cola resultado = new Cola();
        Iterator<Object> a = primera.iterador(), b = segunda.iterador();
        while (a.hasNext() || b.hasNext()) {
            if (a.hasNext()) resultado.agregar(a.next());
            if (b.hasNext()) resultado.agregar(b.next());
        }
        return resultado;
    }

    private static void copiar(Cola origen, Cola destino) {
        Iterator<Object> it = origen.iterador();
        while (it.hasNext()) destino.agregar(it.next());
    }
}
