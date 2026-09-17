/**
 * Clase para resolver el problema de las torres de Hanoi
 * @author Amparo López Gaona
 */
public class Hanoi {
    private static final int ANCHO_MINIMO_COLUMNA = 12;
    private Pila[] palo; // Arreglo de palos
    private final int nDiscos; // Cantidad de discos

    /**
     * Constructor por omisión, crea 3 discos.
     */
    public Hanoi() {
        this(3);
    }

    /**
     * Constructor que crea la cantidad de discos especificada
     * @param n cantidad de discos a crear
     */
    public Hanoi(int n) {
        System.out.println("Torres de Hanoi");
        nDiscos = n;
        palo = new Pila[4]; // Crea los tres postes
        for (int i = 1; i < 4; i++) {
            palo[i] = new Pila();
        }
        // coloca los discos en el primer palo
        for (int i = nDiscos; i > 0; i--) {
            palo[1].push(new Integer(i));
        }
        System.out.println("Las torres inicialmente tienen ");
        pinta();
        solucion(nDiscos, 1, 2, 3);
    }

    /**
     * Metodo para mostrar el contenido de cada palo
     */
    public void pinta() {
        java.util.Iterator<Object> it1 = palo[1].iterador();
        java.util.Iterator<Object> it2 = palo[2].iterador();
        java.util.Iterator<Object> it3 = palo[3].iterador();
        int anchoColumna = Math.max(ANCHO_MINIMO_COLUMNA, nDiscos + 4);
        String formato = "%-" + anchoColumna + "s%-" + anchoColumna + "s%-" + anchoColumna + "s%n";

        System.out.printf(formato, "Palo1", "Palo2", "Palo3");
        for (int i = 0; i < nDiscos; i++) {
            String disco1 = it1.hasNext() ? disco((Integer) it1.next()) : "";
            String disco2 = it2.hasNext() ? disco((Integer) it2.next()) : "";
            String disco3 = it3.hasNext() ? disco((Integer) it3.next()) : "";
            System.out.printf(formato, disco1, disco2, disco3);
        }
    }

    /**
     * Método auxiliar para mostrar un disco pintando "su diámetro" de estrellas
     */
    private String disco(Integer n) {
        StringBuilder estrellas = new StringBuilder();
        for (int i = 0; i < n; i++) {
            estrellas.append('*');
        }
        return estrellas.toString();
    }

    /**
     * Método que resuelve el problema
     */
    private void solucion(int n, int p1, int p2, int p3) {
        if (n > 0) {
            solucion(n - 1, p1, p3, p2);
            Object disco = palo[p1].pop();
            palo[p2].push(disco);
            pinta();
            solucion(n - 1, p3, p2, p1);
        }
    }
}
