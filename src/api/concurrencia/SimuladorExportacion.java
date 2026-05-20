package api.concurrencia;

public class SimuladorExportacion {

    private static int incidenciasProcesadas = 0;

    // Método sincronizado para evitar la condición de carrera
    private static synchronized void procesarSeguro() {
        incidenciasProcesadas++;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Iniciando exportación masiva concurrente");

        // Creamos 1000 tareas paralelas
        Thread[] hilos = new Thread[1000];

        for (int i = 0; i < 1000; i++) {
            hilos[i] = new Thread(() -> {
                try {
                    // Simulamos el tiempo que tarda en leer de la BD
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                }

                // Aplicamos la solución del paso 4 usando el método seguro
                procesarSeguro();
            });
            hilos[i].start(); // Arrancamos el hilo
        }

        // El hilo principal espera a que los 1000 hilos terminen
        for (int i = 0; i < 1000; i++) {
            hilos[i].join();
        }

        System.out.println("Esperado: 1000 incidencias procesadas.");
        System.out.println("Realidad: " + incidenciasProcesadas + " incidencias procesadas.");

        if (incidenciasProcesadas < 1000) {
            System.out.println("[ERROR] Se han perdido datos por culpa del acceso compartido sin sincronizar.");
        } else {
            System.out.println("[OK] Todo correcto.");
        }
    }
}