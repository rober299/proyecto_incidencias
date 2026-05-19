package api.controladores;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.sql.Connection;

import core.Usuario;
import core.UsuarioDAOImpl;
import core.ConexionBD;
import core.ValidacionDatosException;
import core.UsuarioNoAutorizadoException;

import api.dtos.ValidadorDTO;
import api.excepciones.ManejadorErroresGlobal;

public class UsuarioController implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
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
                            // Lanzamos excepción para que la pille el Manejador Global
                            throw new ValidacionDatosException("Usuario no encontrado");
                        }
                    } else {
                        // GET /usuarios (lista general)
                        enviarRespuesta(exchange, 200,
                                "[{\"id\": 1, \"nombre\": \"Rober\"}, {\"id\": 2, \"nombre\": \"Ana\"}]");
                    }
                } else if ("POST".equals(method)) {
                    // solo el admin puede crear usuarios
                    if (!"ADMIN".equals(rolUsuario)) {
                        throw new UsuarioNoAutorizadoException("crear usuarios", rolUsuario);
                    }

                    // Leemos el body para validar
                    InputStream is = exchange.getRequestBody();
                    String body = new String(is.readAllBytes(), "UTF-8");

                    // Extraemos los datos a lo bruto para validar
                    String emailRecibido = extraerValorJSON(body, "email");
                    String passwordRecibida = extraerValorJSON(body, "password");

                    // APLICAMOS LAS VALIDACIONES DEL BLOQUE 1
                    ValidadorDTO.validarEmail(emailRecibido);
                    ValidadorDTO.validarPassword(passwordRecibida);

                    enviarRespuesta(exchange, 201, "{\"mensaje\": \"Usuario creado correctamente\", \"id\": 3}");
                } else if ("DELETE".equals(method)) {
                    // solo el admin puede borrar usuarios
                    if (!"ADMIN".equals(rolUsuario)) {
                        // Lanzamos excepción para que la pille el Manejador Global
                        throw new UsuarioNoAutorizadoException("borrar usuarios", rolUsuario);
                    }

                    if (path.matches(".*/usuarios/\\d+")) {
                        String[] partes = path.split("/");
                        int idBorrar = Integer.parseInt(partes[partes.length - 1]);

                        // Borrado real en la base de datos
                        boolean borrado = usuarioDAO.eliminarLogico(idBorrar);

                        if (borrado) {
                            enviarRespuesta(exchange, 204, "");
                        } else {
                            // Lanzamos excepción para que la pille el Manejador Global
                            throw new ValidacionDatosException("Usuario no encontrado para borrar");
                        }
                    } else {
                        // Lanzamos excepción para que la pille el Manejador Global
                        throw new ValidacionDatosException("ID no especificado");
                    }
                } else {
                    enviarRespuesta(exchange, 405,
                            "{\"error\": \"Método HTTP no permitido en este endpoint\", \"codigo\": 405}");
                }
            }
        } catch (Exception e) {
            ManejadorErroresGlobal.manejar(exchange, e);
        }
    }

    // Utilidad rápida para extraer valores del JSON sin usar librerías externas
    private String extraerValorJSON(String json, String clave) {
        String patron = "\"" + clave + "\":\\s*\"([^\"]+)\"";
        java.util.regex.Matcher m = java.util.regex.Pattern.compile(patron).matcher(json);
        if (m.find())
            return m.group(1);
        return null; // Devolvemos null si no viene el campo, para que el validador salte
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