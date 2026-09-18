import java.text.Normalizer;

/** Ejercicio 3.5.2: verifica palíndromos mediante una pila. */
public final class PilaEjercicio2Palindromo {
    private PilaEjercicio2Palindromo() { }

    public static boolean esPalindromo(String texto) {
        String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .replaceAll("[^A-Za-z0-9]", "")
                .toLowerCase();
        Pila caracteres = new Pila();
        for (char caracter : normalizado.toCharArray()) {
            caracteres.push(caracter);
        }
        for (char caracter : normalizado.toCharArray()) {
            if (caracter != (Character) caracteres.pop()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(esPalindromo("Isaac no ronca así"));
    }
}
