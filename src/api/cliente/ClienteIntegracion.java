package api.cliente;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ClienteIntegracion {

    private static final String BASE_URL = "http://localhost:8082/api/v1";
    private static String tokenAuth = "";

    // Cliente con Timeout configurado (evita bloqueos infinitos)
    private static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(3))
            .build();

    public static void main(String[] args) {
        System.out.println("=== INICIANDO CLIENTE DE INTEGRACIÓN ===");

        // 1. Probar Login Correcto
        tokenAuth = hacerLogin("admin@empresa.com", "1234");

        // 2. Probar Login Incorrecto (Gestionando error 4xx)
        hacerLogin("falso@empresa.com", "claveMala");

        // 3. Obtener Incidencias usando el Token
        obtenerIncidencias();

        // 4. Crear Usuario/Incidencia (Serializando JSON)
        crearRecursoSeguro();

        // 5. Simular una caída del servidor y probar Reintentos
        probarReintentos();

        System.out.println("\n=== FIN DE LA EJECUCIÓN DEL CLIENTE ===");
    }

    private static String hacerLogin(String email, String password) {
        System.out.println("\n[CLIENTE] Intentando hacer login con: " + email);

        // BLOQUE 3: Serialización manual a JSON
        String jsonCuerpo = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonCuerpo))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // BLOQUE 3: Deserialización (Extrayendo el token del JSON)
                String tokenExtraido = extraerValorJSON(response.body(), "token");
                System.out.println("  -> [ÉXITO 200] Token obtenido: " + tokenExtraido);
                return tokenExtraido;
            } else {
                // BLOQUE 2: Gestión de errores sin bloquear el flujo
                System.out.println(
                        "  -> [ERROR " + response.statusCode() + "] Respuesta del servidor: " + response.body());
            }
        } catch (Exception e) {
            System.out.println("  -> [CRÍTICO] Fallo en la comunicación: " + e.getMessage());
        }
        return null;
    }

    private static void obtenerIncidencias() {
        System.out.println("\n[CLIENTE] Solicitando lista de incidencias...");
        if (tokenAuth == null || tokenAuth.isEmpty())
            return;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/incidencias"))
                .header("Authorization", "Bearer " + tokenAuth)
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("  -> [STATUS " + response.statusCode() + "] JSON Recibido: " + response.body());
        } catch (Exception e) {
            System.out.println("  -> [CRÍTICO] " + e.getMessage());
        }
    }

    private static void crearRecursoSeguro() {
        System.out.println("\n[CLIENTE] Intentando crear recurso POST...");
        String jsonNuevo = "{\"email\": \"nuevo.cliente@empresa.com\", \"password\": \"segura123\"}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/usuarios"))
                .header("Authorization", "Bearer " + tokenAuth)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonNuevo))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("  -> [STATUS " + response.statusCode() + "] " + response.body());
        } catch (Exception e) {
            System.out.println("  -> [CRÍTICO] " + e.getMessage());
        }
    }

    // Sistema de reintentos simples y Timeout
    private static void probarReintentos() {
        System.out.println("\n[CLIENTE] Probando reintentos hacia un puerto caído (8099)...");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8099/api/v1/timeout")) // Puerto falso
                .timeout(Duration.ofSeconds(1)) // Timeout súper corto
                .GET()
                .build();

        int maxReintentos = 3;
        for (int i = 1; i <= maxReintentos; i++) {
            try {
                System.out.println("  -> Intento " + i + " de " + maxReintentos + "...");
                client.send(request, HttpResponse.BodyHandlers.ofString());
                break; // Si funciona, salimos del bucle
            } catch (Exception e) {
                System.out.println("     [Fallo] " + e.getMessage());
                if (i == maxReintentos) {
                    System.out.println("  -> [ABORTADO] No se pudo conectar tras " + maxReintentos + " intentos.");
                }
            }
        }
    }

    // Herramienta de deserialización básica usando Regex
    private static String extraerValorJSON(String json, String clave) {
        String patron = "\"" + clave + "\":\\s*\"([^\"]+)\"";
        java.util.regex.Matcher m = java.util.regex.Pattern.compile(patron).matcher(json);
        if (m.find())
            return m.group(1);
        return "";
    }
}