public class Trenes {

    private Cola[] pista;
    private int nVagones;
    private int nPistas; // Pistas para maniobras
    private int[] ordenInicial; // Orden inicial de los vagones
    private int[] ultimoVgnEnCola; // Último vagón en cada pista

    /**
     * Constructor que toma el orden de los vagones antes del reacomodo y
     * la cantidad de pistas para maniobras.
     *
     * @param ordenIni arreglo de enteros con el orden inicial de los vagones
     * @param numPistas cantidad de pistas en el área de maniobras
     */
    public Trenes(int[] ordenIni, int numPistas) {
        nVagones = ordenIni.length;
        nPistas = numPistas;
        pista = new Cola[nPistas + 1];

        for (int i = 1; i <= nPistas; i++) {
            pista[i] = new Cola();
        }

        ultimoVgnEnCola = new int[nPistas + 1];
        ordenInicial = new int[ordenIni.length];

        for (int i = 0; i < ordenInicial.length; i++) {
            ordenInicial[i] = ordenIni[i];
        }
    }

    /**
     * Constructor por omisión.
     */
    public Trenes() {
        this(new int[] {3, 6, 9, 2, 4, 7, 1, 8, 5}, 3);
    }

    /**
     * Método para acomodar los vagones del tren.
     *
     * @return true si es posible acomodarlos y false en otro caso
     */
    public boolean acomodarVagones() {
        int sgteVagonFuera = 1;

        for (int i = 0; i < nVagones; i++) {
            if (ordenInicial[i] == sgteVagonFuera) {
                System.out.println("Mueve el vagón " + ordenInicial[i]
                        + " de la pista de entrada a la salida");
                sgteVagonFuera++;

                while (sacarDeAreaM(sgteVagonFuera)) {
                    sgteVagonFuera++;
                }
            } else if (!colocarEnAreaM(ordenInicial[i])) {
                return false;
            }
        }

        return true;
    }

    private boolean sacarDeAreaM(int vagon) {
        for (int i = 1; i < nPistas; i++) {
            if (!pista[i].estaVacia()
                    && ((Integer) pista[i].tomar()).intValue() == vagon) {
                pista[i].eliminar();
                System.out.println("Mueve el vagón " + vagon
                        + " del campo de maniobra " + i + " a la salida");

                if (pista[i].estaVacia()) {
                    ultimoVgnEnCola[i] = 0;
                }

                return true;
            }
        }

        return false;
    }

    /**
     * Método para colocar un vagón en el área de maniobras.
     *
     * @param vagon número del vagón que se desea colocar
     * @return true si es posible colocarlo en alguna pista y false en otro caso
     */
    public boolean colocarEnAreaM(int vagon) {
        for (int i = 1; i < nPistas; i++) {
            if (pista[i].estaVacia() || vagon > ultimoVgnEnCola[i]) {
                pista[i].agregar(new Integer(vagon));
                ultimoVgnEnCola[i] = vagon;

                System.out.println("Mueve el vagón " + vagon
                        + " de la pista de entrada al campo de maniobra " + i);
                return true;
            }
        }

        return false;
    }
}

