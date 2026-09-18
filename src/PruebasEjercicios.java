import java.util.Iterator;
import java.util.List;

/** Comprobaciones ejecutables de los ejercicios desarrollados. */
public final class PruebasEjercicios {
    private PruebasEjercicios() { }

    public static void main(String[] args) {
        probarPilas();
        probarColas();
        System.out.println("Todas las comprobaciones pasaron.");
    }

    private static void probarPilas() {
        verificar(PilaEjercicio2Palindromo.esPalindromo("Isaac no ronca así"), "palíndromo");
        Pila a = pila(3, 1); Pila b = pila(4, 2);
        verificar(PilaEjercicio4Biblioteca.ordenar(a, b, 4).equals(List.of(1, 2, 3, 4)), "biblioteca");
        verificar(PilaEjercicio5Postfija.convertir("A+B*(C-D)").equals("A B C D - * +"), "postfija");
        verificar(PilaEjercicio7AritmeticaGrande.sumar("999999999999", "1").equals("1000000000000"), "suma grande");
        verificar(PilaEjercicio7AritmeticaGrande.restar("1000000000000", "1").equals("999999999999"), "resta grande");
        verificar(PilaEjercicio10Editor.editar("Pai-les$Eji-e%Pa3--iliy-lla").equals("Pililla"), "editor");
    }

    private static void probarColas() {
        Cola cola = cola("A", "B", "C");
        verificar(cola.ultimoElemento().equals("C"), "último de cola");
        verificar(aLista(ColaEjercicio1Operaciones.invertir(cola)).equals(List.of("C", "B", "A")), "invertir cola");
        verificar(aLista(ColaEjercicio1Operaciones.intercalar(cola("A", "C"), cola("B", "D", "E"))).equals(List.of("A", "B", "C", "D", "E")), "intercalar");
        ColaEjercicio4Circular circular = new ColaEjercicio4Circular(); circular.agregar("A"); circular.agregar("B"); circular.eliminar(); circular.agregar("C");
        verificar(aLista(circular).equals(List.of("B", "C")), "cola circular");
        ColaEjercicio6Deque deque = new ColaEjercicio6Deque(); deque.agregar("B"); deque.agregarPrimero("A"); deque.agregar("C"); deque.eliminarUltimo();
        verificar(deque.tomar().equals("A") && deque.ultimoElemento().equals("B"), "deque");
        ColaEjercicio7Cine cine = new ColaEjercicio7Cine(); cine.agregar("Ocasional", false); cine.agregar("Asiduo", true);
        verificar(cine.eliminar().equals("Asiduo"), "prioridad cine");
        Cola inventario = cola(new ColaEjercicio8Almacen.Consola("C1", "Consola"), new ColaEjercicio8Almacen.Consola("C2", "Consola"));
        Cola solicitudes = cola(new ColaEjercicio8Almacen.Solicitud("Tienda", 2));
        verificar(ColaEjercicio8Almacen.asignar(inventario, solicitudes).get(0).codigos().equals(List.of("C1", "C2")), "almacén");
        Cola reservas = cola(new ColaEjercicio9Restaurante.Reserva("Ana", 3));
        verificar(ColaEjercicio9Restaurante.asignarMejorOcupacion(List.of(new ColaEjercicio9Restaurante.Mesa("M1", 4), new ColaEjercicio9Restaurante.Mesa("M2", 3)), reservas).get(0).codigoMesa().equals("M2"), "restaurante");
    }

    private static Pila pila(int... valores) { Pila pila = new Pila(); for (int valor : valores) pila.push(valor); return pila; }
    private static Cola cola(Object... valores) { Cola cola = new Cola(); for (Object valor : valores) cola.agregar(valor); return cola; }
    private static List<Object> aLista(Iterable<Object> elementos) { java.util.ArrayList<Object> lista = new java.util.ArrayList<>(); for (Object elemento : elementos) lista.add(elemento); return lista; }
    private static List<Object> aLista(Cola cola) { java.util.ArrayList<Object> lista = new java.util.ArrayList<>(); Iterator<Object> it = cola.iterador(); while (it.hasNext()) lista.add(it.next()); return lista; }
    private static void verificar(boolean condicion, String nombre) { if (!condicion) throw new AssertionError("Falló: " + nombre); }
}
