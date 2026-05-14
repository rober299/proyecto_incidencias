package api.controladores;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

// Importamos tu servicio real
import orm.servicio.IncidenciaService;

public class IncidenciaController implements HttpHandler {

    // Conectamos con la capa de servicio
    private IncidenciaService incidenciaService = new IncidenciaService(null);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");

        try {
            if ("GET".equals(method)) {
                // Mantenemos tus ejemplos pero preparados para el servicio
                enviarRespuesta(exchange, 200,
                        "[{\"id\": 101, \"titulo\": \"Fallo VPN\", \"estado\": \"ABIERTO\"}, {\"id\": 102, \"titulo\": \"Ratón roto\", \"estado\": \"CERRADO\"}]");
            } else if ("POST".equals(method)) {
                // Mantenemos tu lógica de validación
                boolean bodyVacioSimulado = false;

                if (bodyVacioSimulado) {
                    enviarRespuesta(exchange, 400,
                            "{\"error\": \"Petición inválida\", \"mensaje\": \"El título de la incidencia es obligatorio.\"}");
                } else {
                    enviarRespuesta(exchange, 201,
                            "{\"mensaje\": \"Incidencia registrada con éxito\", \"id\": 103, \"estado\": \"NUEVO\"}");
                }
            } else if ("PUT".equals(method)) {
                if (path.matches(".*/incidencias/\\d+")) {
                    enviarRespuesta(exchange, 200, "{\"mensaje\": \"Incidencia actualizada correctamente\"}");
                } else {
                    // Mantenemos tu mensaje específico de error
                    enviarRespuesta(exchange, 404,
                            "{\"error\": \"Recurso no encontrado\", \"mensaje\": \"Debe especificar un ID para actualizar.\"}");
                }
            } else {
                enviarRespuesta(exchange, 405, "{\"error\": \"Método HTTP no permitido\", \"codigo\": 405}");
            }
        } catch (Exception e) {
            enviarRespuesta(exchange, 500, "{\"error\": \"Error interno\"}");
        }
    }

    private void enviarRespuesta(HttpExchange exchange, int code, String response) throws IOException {
        byte[] bytes = response.getBytes("UTF-8");
        exchange.sendResponseHeaders(code, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}