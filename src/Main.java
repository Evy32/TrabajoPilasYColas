/** Punto de entrada para los ejemplos de Torres de Hanoi y Trenes. */
public class Main {
    public static void main(String[] args) {
        ejecutarHanoi();
        ejecutarTrensitos();
        ejecutarPruebasEjercicios();
    }

    private static void ejecutarHanoi() {
        System.out.println("\n=== Torres de Hanoi ===");
        new Hanoi();
    }

    private static void ejecutarTrensitos() {
        System.out.println("\n=== Reacomodo de trenes ===");
        Trenes trenes = new Trenes();

        if (trenes.acomodarVagones()) {
            System.out.println("Los vagones se acomodaron correctamente.");
        } else {
            System.out.println("No fue posible acomodar los vagones.");
        }
    }

    private static void ejecutarPruebasEjercicios() {
        System.out.println("\n=== Pruebas de ejercicios ===");
        PruebasEjercicios.main(new String[0]);
    }
}
