package api.controladores;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

public class HealthController implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Solo aceptamos peticiones GET
        if ("GET".equals(exchange.getRequestMethod())) {
            // Respuesta JSON
            String response = "{\"status\": \"UP\", \"entorno\": \"desarrollo\", \"mensaje\": \"Backend de Gestión IT operativo\"}";

            // Configurar cabeceras
            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");

            // Enviar código 200 OK y la longitud de la respuesta
            byte[] bytes = response.getBytes("UTF-8");
            exchange.sendResponseHeaders(200, bytes.length);

            // Escribir el cuerpo de la respuesta
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        } else {
            // Método no permitido (405)
            exchange.sendResponseHeaders(405, -1);
        }
    }
}