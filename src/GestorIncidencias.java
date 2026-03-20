public class GestorIncidencias {

    // Método antiguo que registra incidencias, lleno de fallos
    public static void registrarIncidencia(int id, String titulo, int urgencia) {

        // Defecto 1: Validación errónea. No comprueba nada, se traga IDs negativos.
        int idIncidencia = id;

        // Defecto 2: Nulos. Si el título llega null, el .trim() revienta el programa.
        String tituloLimpio = titulo.trim().toUpperCase();

        // Defecto 3: Cálculo mal planteado. Si meten urgencia 0, división por cero.
        int prioridad = 100 / urgencia;

        // Defecto 4: Mensaje poco informativo (print basura y desordenado).
        System.out.println("ok guardado " + idIncidencia);
    }

    public static void main(String[] args) {
        System.out.println("Iniciando sistema de incidencias...\n");

        // Caso 1: Pasa la ejecución, pero el ID -1 es un bug lógico (Validación
        // errónea)
        registrarIncidencia(-1, "  Pantalla rota  ", 2);

        // Caso 2: Si quitas las barras, revienta por ArithmeticException (división por
        // cero)
        // registrarIncidencia(102, "Raton no va", 0);

        // Caso 3: Si quitas las barras, revienta por NullPointerException
        // registrarIncidencia(103, null, 1);
    }
}