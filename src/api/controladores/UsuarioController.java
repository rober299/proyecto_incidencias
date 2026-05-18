package api.controladores;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;

import core.Usuario;
import core.UsuarioDAOImpl;
import core.ConexionBD;

public class UsuarioController implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        // Obtenemos el rol que nos ha pasado el filtro
        String rolUsuario = (String) exchange.getAttribute("rol");

        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");

        // Conexión real con el DAO
        try (Connection conexion = ConexionBD.obtenerConexion()) {
            UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl(conexion);

            if ("GET".equals(method)) {
                if (path.matches(".*/usuarios/\\d+")) {
                    // Extraemos el ID
                    String[] partes = path.split("/");
                    int idBuscado = Integer.parseInt(partes[partes.length - 1]);

                    // Conexión real: obtenemos el usuario de la BD
                    Usuario usuarioReal = usuarioDAO.obtenerPorId(idBuscado);

                    if (usuarioReal != null) {
                        // Mantenemos tu estructura de email y nombre
                        String jsonResponse = "{\"id\": " + usuarioReal.getIdUsuario() +
                                ", \"nombre\": \"Usuario Real\", \"email\": \"" + usuarioReal.getEmail() + "\"}";
                        enviarRespuesta(exchange, 200, jsonResponse);
                    } else {
                        enviarRespuesta(exchange, 404, "{\"error\": \"Usuario no encontrado\"}");
                    }
                } else {
                    // GET /usuarios (lista general)
                    enviarRespuesta(exchange, 200,
                            "[{\"id\": 1, \"nombre\": \"Rober\"}, {\"id\": 2, \"nombre\": \"Ana\"}]");
                }
            } else if ("POST".equals(method)) {
                // solo el admin puede crear usuarios
                if (!"ADMIN".equals(rolUsuario)) {
                    enviarRespuesta(exchange, 403, "{\"error\": \"Prohibido. Solo los ADMIN pueden crear usuarios.\"}");
                    return;
                }
                enviarRespuesta(exchange, 201, "{\"mensaje\": \"Usuario creado correctamente\", \"id\": 3}");
            } else if ("DELETE".equals(method)) {
                // solo el admin puede borrar usuarios
                if (!"ADMIN".equals(rolUsuario)) {
                    enviarRespuesta(exchange, 403,
                            "{\"error\": \"Prohibido. Solo los ADMIN pueden borrar usuarios.\"}");
                    return;
                }

                if (path.matches(".*/usuarios/\\d+")) {
                    String[] partes = path.split("/");
                    int idBorrar = Integer.parseInt(partes[partes.length - 1]);

                    // Borrado real en la base de datos
                    boolean borrado = usuarioDAO.eliminarLogico(idBorrar);

                    if (borrado) {
                        enviarRespuesta(exchange, 204, "");
                    } else {
                        enviarRespuesta(exchange, 404, "{\"error\": \"Usuario no encontrado para borrar\"}");
                    }
                } else {
                    enviarRespuesta(exchange, 400, "{\"error\": \"ID no especificado\"}");
                }
            } else {
                enviarRespuesta(exchange, 405,
                        "{\"error\": \"Método HTTP no permitido en este endpoint\", \"codigo\": 405}");
            }
        } catch (Exception e) {
            enviarRespuesta(exchange, 500,
                    "{\"error\": \"Error interno del servidor\", \"detalle\": \"" + e.getMessage() + "\"}");
        }
    }

    private void enviarRespuesta(HttpExchange exchange, int code, String response) throws IOException {
        byte[] bytes = response.getBytes("UTF-8");
        exchange.sendResponseHeaders(code, code == 204 ? -1 : bytes.length);
        if (code != 204) {
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        }
    }
}