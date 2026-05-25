package api.observabilidad;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProcesoResiliente {

    // Simulador de Logger estructurado con niveles de severidad
    private static void log(String nivel, String mensaje) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.printf("[%s] [%-5s] %s%n", timestamp, nivel, mensaje);
    }

    public static void main(String[] args) {
        System.out.println("=== Demo: automatizacion y resiliencia ===\n");
        log("INFO", "Iniciando proceso automatizado de sincronización...");

        // Validación previa de dependencias
        String configPath = System.getenv("CONFIG_PATH");
        if (configPath == null) {
            log("WARN", "Variable de entorno CONFIG_PATH no definida. Usando configuración por defecto.");
        }

        boolean exito = false;
        int maxReintentos = 3;
        int intentoActual = 1;
        int tiempoEsperaMs = 2000; // 2 segundos de espera entre intentos

        // Bucle de resiliencia (Reintentos limitados)
        while (intentoActual <= maxReintentos && !exito) {
            try {
                log("INFO", "Intento " + intentoActual + " de conexión al servicio externo ERP...");
                conectarServicioExterno(intentoActual); // Esto lanzará un error a propósito
                exito = true;
                log("INFO", "Sincronización completada con éxito.");
            } catch (Exception e) {
                log("WARN", "Fallo temporal detectado: " + e.getMessage());

                if (intentoActual < maxReintentos) {
                    log("INFO", "Esperando " + (tiempoEsperaMs / 1000) + " segundos antes de reintentar...");
                    try {
                        Thread.sleep(tiempoEsperaMs);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    // Mensaje de fallo accionable (te dice exactamente qué hacer)
                    log("ERROR", "Fallo definitivo tras " + maxReintentos + " intentos.");
                    log("ERROR",
                            "ACCIÓN REQUERIDA: Verificar estado del servidor destino (IP: 192.168.1.100) y comprobar reglas de firewall.");
                }
            }
            intentoActual++;
        }
        System.out.println("\n=== FIN DE LA DEMO ===");
    }

    // Método que simula un servicio externo caído para forzar el fallo controlado
    private static void conectarServicioExterno(int intento) throws Exception {
        throw new Exception("Connection refused (Connection timed out)");
    }
}