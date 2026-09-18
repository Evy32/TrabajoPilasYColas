/** Ejercicio 3.5.10: simulador de edición; - retroceso, $ borra hasta el final y % toda la línea. */
public final class PilaEjercicio10Editor {
    private PilaEjercicio10Editor() { }

    public static String editar(String entrada) {
        Pila izquierda = new Pila();
        Pila derecha = new Pila();
        for (char caracter : entrada.toCharArray()) {
            switch (caracter) {
                case '-' -> { if (!izquierda.estaVacia()) izquierda.pop(); }
                case '$' -> { while (!derecha.estaVacia()) derecha.pop(); }
                case '%' -> { while (!izquierda.estaVacia()) izquierda.pop(); while (!derecha.estaVacia()) derecha.pop(); }
                default -> izquierda.push(caracter);
            }
        }
        while (!izquierda.estaVacia()) derecha.push(izquierda.pop());
        StringBuilder resultado = new StringBuilder();
        while (!derecha.estaVacia()) resultado.append(derecha.pop());
        return resultado.toString();
    }
}
