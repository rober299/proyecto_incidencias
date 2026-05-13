package api.config;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import api.controladores.HealthController;

public class ServidorAPI {
    private HttpServer server;
    // Leemos el puerto desde una variable de entorno, o usamos 8082 por defecto
    private int puerto = Integer.parseInt(System.getProperty("server.port", "8082"));
    private String perfil = System.getProperty("env", "desarrollo");

    public void iniciar() {
        try {
            System.out.println("Cargando configuración...");
            System.out.println("Perfil activo: " + perfil);

            // Crear el servidor en el puerto especificado
            server = HttpServer.create(new InetSocketAddress(puerto), 0);

            // Mapear los endpoints (Rutas)
            server.createContext("/health", new HealthController());
            server.createContext("/api/v1/usuarios", new api.controladores.UsuarioController());
            server.createContext("/api/v1/incidencias", new api.controladores.IncidenciaController());

            // Ejecutor por defecto
            server.setExecutor(null);
            server.start();

            System.out.println("Servidor API arrancado y escuchando en http://localhost:" + puerto);
        } catch (Exception e) {
            System.err.println("Error al arrancar el servidor: " + e.getMessage());
        }
    }
}