import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class SimpleLogger {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static void log(String nivel, String mensaje) {
        System.out.printf("[%s] [%-5s] %s%n", LocalDateTime.now().format(formatter), nivel, mensaje);
    }

    public static void info(String mensaje) {
        log("INFO", mensaje);
    }

    public static void error(String mensaje) {
        log("ERROR", mensaje);
    }
}

public class GestorIncidencias {
    private static void validarDatos(int id, String titulo, int urgencia) {
        if (id <= 0) {
            throw new ValidacionDatosException("El ID debe ser estrictamente positivo. Recibido: " + id);
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new ValidacionDatosException("El título de la incidencia no puede ser nulo o estar en blanco.");
        }
        if (urgencia < 1 || urgencia > 5) {
            // Reutilizamos ValidacionDatosException para salir del paso si la urgencia no
            // está en el rango correcto
            throw new ValidacionDatosException("La urgencia debe estar entre 1 y 5. Recibido: " + urgencia);
        }
    }

    public static void registrarIncidencia(int id, String titulo, int urgencia) {
        try {
            // 1. Centralizamos la validación. Si algo falla, salta directamente al 'catch'.
            validarDatos(id, titulo, urgencia);

            // 2. Si pasamos la validación, procesamos normalmente (el código ya es seguro)
            String tituloLimpio = titulo.trim().toUpperCase();
            SimpleLogger.info(
                    String.format("Incidencia guardada correctamente -> ID: %d | Título: '%s'", id, tituloLimpio));

        } catch (ValidacionDatosException e) {
            // 3. Capturamos nuestro propio error de negocio de forma elegante
            SimpleLogger.error("Operación abortada por regla de negocio: " + e.getMessage());
        } catch (Exception e) {
            // 4. Paracaídas de emergencia para cualquier otro fallo técnico inesperado
            SimpleLogger.error("Fallo técnico inesperado: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SimpleLogger.info("Arrancando sistema de incidencias (Refactor V2 con Excepciones)");

        // Falla por ID negativo (controlado por nuestra excepción)
        registrarIncidencia(-1, "  Pantalla rota  ", 2);

        // Falla por urgencia fuera de rango (controlado por nuestra excepción)
        registrarIncidencia(102, "Raton no va", 0);

        // Falla por null (controlado por nuestra excepción)
        registrarIncidencia(103, null, 1);

        // Funciona perfecto
        registrarIncidencia(205, " Servidor caido ", 5);

        SimpleLogger.info("Proceso finalizado.");
    }
}