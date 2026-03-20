import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Clase Logger, centraliza y da formato profesional a los mensajes, evitando prints desordenados.
class SimpleLogger {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static void log(String nivel, String mensaje) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.printf("[%s] [%-5s] %s%n", timestamp, nivel, mensaje);
    }

    public static void info(String mensaje) {
        log("INFO", mensaje);
    }

    public static void debug(String mensaje) {
        log("DEBUG", mensaje);
    }

    public static void error(String mensaje) {
        log("ERROR", mensaje);
    }
}

public class GestorIncidencias {

    // Refactorizacion: extraccion de metodo de validacion
    private static boolean esValida(int id, String titulo, int urgencia) {
        if (id <= 0) {
            SimpleLogger.error("Validación fallida: El ID debe ser mayor que 0. Recibido: " + id);
            return false;
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            SimpleLogger.error("Validación fallida: El título no puede ser nulo o vacío.");
            return false;
        }
        if (urgencia <= 0) {
            SimpleLogger.error("Validación fallida: La urgencia debe ser un valor positivo. Recibido: " + urgencia);
            return false;
        }
        return true;
    }

    public static void registrarIncidencia(int id, String titulo, int urgencia) {
        SimpleLogger.debug("Iniciando registro para ticket ID: " + id);

        // 1. Centralizamos la validación antes de hacer nada
        if (!esValida(id, titulo, urgencia)) {
            SimpleLogger.error("Operación abortada para el ticket ID: " + id);
            return;
        }

        // 2. Aquí sabemos que titulo no es null
        String tituloLimpio = titulo.trim().toUpperCase();

        // 3. Aquí sabemos que urgencia no es 0
        int prioridad = 100 / urgencia;

        // 4. Mensaje informativo limpio
        SimpleLogger.info(
                String.format("Incidencia guardada correctamente -> ID: %d | Título: '%s' | Prioridad Calculada: %d",
                        id, tituloLimpio, prioridad));
    }

    public static void main(String[] args) {
        SimpleLogger.info("Arrancando sistema de incidencias (Versión Refactorizada)");

        // Caso 1: Falla por ID negativo
        registrarIncidencia(-1, "  Pantalla rota  ", 2);

        // Caso 2: Falla por urgencia 0
        registrarIncidencia(102, "Raton no va", 0);

        // Caso 3: Falla por null
        registrarIncidencia(103, null, 1);

        // Caso 4: Un caso bueno que debe procesarse perfectamente
        registrarIncidencia(205, " Servidor caido ", 5);

        SimpleLogger.info("Proceso finalizado.");
    }
}