package api.controladores;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

public class UsuarioController implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        // Configuramos la respuesta siempre como JSON
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");

        try {
            if ("GET".equals(method)) {
                if (path.matches(".*/usuarios/\\d+")) {
                    // Simula GET /usuarios/{id} -> Conecta con UsuarioService.obtenerPorId()
                    enviarRespuesta(exchange, 200,
                            "{\"id\": 1, \"nombre\": \"Rober\", \"rol\": \"Admin\", \"email\": \"rober@empresa.com\"}");
                } else {
                    // Simula GET /usuarios -> Conecta con UsuarioService.obtenerTodos()
                    enviarRespuesta(exchange, 200,
                            "[{\"id\": 1, \"nombre\": \"Rober\"}, {\"id\": 2, \"nombre\": \"Ana\"}]");
                }
            } else if ("POST".equals(method)) {
                // Simula creación
                enviarRespuesta(exchange, 201, "{\"mensaje\": \"Usuario creado correctamente\", \"id\": 3}");
            } else if ("DELETE".equals(method)) {
                // Simula borrado
                enviarRespuesta(exchange, 204, "");
            } else {
                // Método no soportado
                enviarRespuesta(exchange, 405,
                        "{\"error\": \"Método HTTP no permitido en este endpoint\", \"codigo\": 405}");
            }
        } catch (Exception e) {
            // Error de servidor
            enviarRespuesta(exchange, 500,
                    "{\"error\": \"Error interno del servidor\", \"detalle\": \"" + e.getMessage() + "\"}");
        }
    }

    private void enviarRespuesta(HttpExchange exchange, int code, String response) throws IOException {
        byte[] bytes = response.getBytes("UTF-8");
        // Si es 204, la longitud debe ser -1 (sin cuerpo)
        exchange.sendResponseHeaders(code, code == 204 ? -1 : bytes.length);
        if (code != 204) {
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        }
    }
}