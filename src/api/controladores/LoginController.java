package api.controladores;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import api.seguridad.GestorTokens;

public class LoginController implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!"POST".equals(exchange.getRequestMethod())) {
            enviarRespuesta(exchange, 405, "{\"error\": \"Método no permitido\"}");
            return;
        }

        // Leemos el cuerpo de la petición de forma básica
        InputStream is = exchange.getRequestBody();
        String body = new String(is.readAllBytes(), "UTF-8");

        // Hacemos una comprobación simple y directa
        String token = null;
        String rol = null;

        if (body.contains("\"admin@empresa.com\"") && body.contains("\"1234\"")) {
            rol = "ADMIN";
            token = GestorTokens.generarToken(rol);
        } else if (body.contains("\"user@empresa.com\"") && body.contains("\"1234\"")) {
            rol = "USER";
            token = GestorTokens.generarToken(rol);
        }

        if (token != null) {
            enviarRespuesta(exchange, 200,
                    "{\"mensaje\": \"Login correcto\", \"token\": \"" + token + "\", \"rol\": \"" + rol + "\"}");
        } else {
            enviarRespuesta(exchange, 401, "{\"error\": \"Credenciales incorrectas\"}");
        }
    }

    private void enviarRespuesta(HttpExchange exchange, int code, String response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        byte[] bytes = response.getBytes("UTF-8");
        exchange.sendResponseHeaders(code, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}