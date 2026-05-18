package api.seguridad;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;

public class FiltroAutenticacion extends Filter {

    @Override
    public String description() {
        return "Filtro para validar tokens Bearer";
    }

    @Override
    public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
        String authHeader = exchange.getRequestHeaders().getFirst("Authorization");

        // Si no hay cabecera o no empieza por Bearer, puerta (401)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            enviarError(exchange, 401, "No autorizado. Falta el token de seguridad.");
            return;
        }

        String token = authHeader.substring(7);

        // Validamos si el token es correcto y no ha caducado
        if (!GestorTokens.validarToken(token)) {
            enviarError(exchange, 401, "Token inválido o expirado.");
            return;
        }

        // Extraemos el rol del token y lo guardamos en la petición
        String rolUsuario = GestorTokens.obtenerRol(token);
        exchange.setAttribute("rol", rolUsuario);

        // Si todo está bien, dejamos que la petición continúe hacia el controlador
        chain.doFilter(exchange);
    }

    private void enviarError(HttpExchange exchange, int code, String mensaje) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        String jsonError = "{\"error\": \"Acceso denegado\", \"mensaje\": \"" + mensaje + "\"}";
        byte[] bytes = jsonError.getBytes("UTF-8");
        exchange.sendResponseHeaders(code, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}