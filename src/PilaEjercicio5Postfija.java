/** Ejercicio 3.5.5: conversión de notación infija a postfija. */
public final class PilaEjercicio5Postfija {
    private PilaEjercicio5Postfija() { }

    public static String convertir(String expresion) {
        Pila operadores = new Pila();
        StringBuilder salida = new StringBuilder();
        StringBuilder numero = new StringBuilder();
        for (int i = 0; i < expresion.length(); i++) {
            char actual = expresion.charAt(i);
            if (Character.isWhitespace(actual)) continue;
            if (Character.isLetterOrDigit(actual) || actual == '.') {
                numero.append(actual);
                continue;
            }
            agregarOperando(numero, salida);
            if (actual == '(') operadores.push(actual);
            else if (actual == ')') {
                while (!operadores.estaVacia() && (Character) operadores.top() != '(') {
                    salida.append(operadores.pop()).append(' ');
                }
                if (operadores.estaVacia()) throw new IllegalArgumentException("Paréntesis desbalanceados");
                operadores.pop();
            } else if (esOperador(actual)) {
                while (!operadores.estaVacia() && (Character) operadores.top() != '('
                        && (prioridad((Character) operadores.top()) > prioridad(actual)
                        || (prioridad((Character) operadores.top()) == prioridad(actual) && actual != '^'))) {
                    salida.append(operadores.pop()).append(' ');
                }
                operadores.push(actual);
            } else throw new IllegalArgumentException("Símbolo inválido: " + actual);
        }
        agregarOperando(numero, salida);
        while (!operadores.estaVacia()) {
            char operador = (Character) operadores.pop();
            if (operador == '(') throw new IllegalArgumentException("Paréntesis desbalanceados");
            salida.append(operador).append(' ');
        }
        return salida.toString().trim();
    }

    private static void agregarOperando(StringBuilder numero, StringBuilder salida) {
        if (!numero.isEmpty()) { salida.append(numero).append(' '); numero.setLength(0); }
    }
    private static boolean esOperador(char c) { return "+-*/^".indexOf(c) >= 0; }
    private static int prioridad(char c) { return c == '^' ? 3 : (c == '*' || c == '/') ? 2 : 1; }
}
