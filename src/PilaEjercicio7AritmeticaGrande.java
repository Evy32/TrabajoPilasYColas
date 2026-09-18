/** Ejercicio 3.5.7: suma y resta decimal sin usar tipos numéricos grandes. */
public final class PilaEjercicio7AritmeticaGrande {
    private PilaEjercicio7AritmeticaGrande() { }

    public static String sumar(String a, String b) { return operar(a, b, false); }
    public static String restar(String a, String b) { return operar(a, b, true); }

    private static String operar(String a, String b, boolean resta) {
        validar(a); validar(b);
        if (resta && comparar(a, b) < 0) return "-" + operar(b, a, true);
        Pila izquierda = digitos(a), derecha = digitos(b), resultado = new Pila();
        int acarreo = 0;
        while (!izquierda.estaVacia() || !derecha.estaVacia()) {
            int x = izquierda.estaVacia() ? 0 : (Integer) izquierda.pop();
            int y = derecha.estaVacia() ? 0 : (Integer) derecha.pop();
            int valor = resta ? x - y - acarreo : x + y + acarreo;
            if (resta && valor < 0) { valor += 10; acarreo = 1; }
            else acarreo = resta ? 0 : valor / 10;
            resultado.push(valor % 10);
        }
        if (!resta && acarreo > 0) resultado.push(acarreo);
        StringBuilder texto = new StringBuilder();
        while (!resultado.estaVacia()) texto.append(resultado.pop());
        return texto.toString().replaceFirst("^0+(?!$)", "");
    }

    private static Pila digitos(String numero) { Pila p = new Pila(); for (char c : numero.toCharArray()) p.push(c - '0'); return p; }
    private static void validar(String n) { if (!n.matches("\\d+")) throw new IllegalArgumentException("Solo se aceptan enteros no negativos"); }
    private static int comparar(String a, String b) { a = a.replaceFirst("^0+", ""); b = b.replaceFirst("^0+", ""); return a.length() != b.length() ? Integer.compare(a.length(), b.length()) : a.compareTo(b); }
}
