package api.excepciones;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import core.ValidacionDatosException;
import core.UsuarioNoAutorizadoException;
import core.IncidenciaNoEncontradaException;

public class ManejadorErroresGlobal {

    public static void manejar(HttpExchange exchange, Exception e) throws IOException {
        int status = 500;
        String errorType = "Error Interno del Servidor";
        String message = e.getMessage();

        // Mapeo de excepciones a códigos HTTP
        if (e instanceof ValidacionDatosException || e instanceof IllegalArgumentException) {
            status = 400;
            errorType = "Bad Request - Datos Inválidos";
        } else if (e instanceof UsuarioNoAutorizadoException) {
            status = 403;
            errorType = "Forbidden - Acceso Denegado";
        } else if (e instanceof IncidenciaNoEncontradaException) {
            status = 404;
            errorType = "Not Found - Recurso No Encontrado";
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String path = exchange.getRequestURI().getPath();

        String mensajeSeguro = message != null ? message.replace("\"", "\\\"") : "Error desconocido";

        String jsonResponse = String.format(
                "{\n" +
                        "  \"timestamp\": \"%s\",\n" +
                        "  \"status\": %d,\n" +
                        "  \"error\": \"%s\",\n" +
                        "  \"message\": \"%s\",\n" +
                        "  \"path\": \"%s\"\n" +
                        "}",
                timestamp, status, errorType, mensajeSeguro, path);

        enviarRespuesta(exchange, status, jsonResponse);
    }

    private static void enviarRespuesta(HttpExchange exchange, int code, String json) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        byte[] bytes = json.getBytes("UTF-8");
        exchange.sendResponseHeaders(code, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}