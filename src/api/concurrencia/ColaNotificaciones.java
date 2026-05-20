package api.concurrencia;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ColaNotificaciones {

    // Cola thread-safe que actuará como nuestro "buzón" intermedio
    private static final BlockingQueue<String> cola = new LinkedBlockingQueue<>();
    private static volatile boolean ejecutando = true;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Iniciando pruebas de productor-consumidor ===");

        int ritmoProductorMs = 200; // Produce 5 tareas por segundo
        int ritmoConsumidorMs = 1000; // Consume 1 tarea por segundo

        // 1. El productor (Ej. El endpoint que recibe las nuevas incidencias)
        Thread productor = new Thread(() -> {
            int contador = 1;
            while (ejecutando && contador <= 10) { // Producirá 10 notificaciones
                try {
                    String tarea = "Email para Incidencia #" + contador;
                    cola.put(tarea);
                    System.out.println("[PRODUCTOR] Ha encolado: " + tarea + " | Tamaño de la cola: " + cola.size());
                    contador++;
                    Thread.sleep(ritmoProductorMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("[PRODUCTOR] Ha terminado de generar tareas.");
        });

        // 2. El consumidor (Ej. El servicio en segundo plano que envía los emails)
        Thread consumidor = new Thread(() -> {
            while (ejecutando || !cola.isEmpty()) {
                try {
                    String tarea = cola.poll(); // Extrae de la cola (no bloqueante si está vacía)
                    if (tarea != null) {
                        System.out.println("[CONSUMIDOR] Enviando " + tarea + "...");
                        Thread.sleep(ritmoConsumidorMs); // Simula el tiempo de envío del email
                        System.out.println("[CONSUMIDOR]  " + tarea + " enviado. | Tareas pendientes: " + cola.size());
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("[CONSUMIDOR] Ha vaciado la cola y termina su trabajo.");
        });

        // Arrancamos ambos hilos a la vez
        productor.start();
        consumidor.start();

        // Esperamos a que el productor termine de meter sus 10 tareas
        productor.join();

        // Damos tiempo al consumidor para vaciar lo que quede
        while (!cola.isEmpty()) {
            Thread.sleep(500);
        }

        ejecutando = false;
        consumidor.join();
        System.out.println("=== Final de la simulacion ===");
    }
}