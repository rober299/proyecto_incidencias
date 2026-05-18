package api.config;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

import api.controladores.HealthController;
import api.controladores.LoginController;
import api.controladores.UsuarioController;
import api.controladores.IncidenciaController;
import api.seguridad.FiltroAutenticacion;

public class ServidorAPI {
    private HttpServer server;
    private int puerto = Integer.parseInt(System.getProperty("server.port", "8082"));
    private String perfil = System.getProperty("env", "desarrollo");

    public void iniciar() {
        try {
            System.out.println("Cargando configuración...");
            System.out.println("Perfil activo: " + perfil);

            server = HttpServer.create(new InetSocketAddress(puerto), 0);

            // 1. Rutas publicas (no necesitan token)
            server.createContext("/health", new HealthController());
            server.createContext("/api/v1/login", new LoginController());

            // 2. Rutas privadas (Protegidas por el filtro)
            FiltroAutenticacion filtro = new FiltroAutenticacion();

            HttpContext contextUsuarios = server.createContext("/api/v1/usuarios", new UsuarioController());
            contextUsuarios.getFilters().add(filtro);

            HttpContext contextIncidencias = server.createContext("/api/v1/incidencias", new IncidenciaController());
            contextIncidencias.getFilters().add(filtro);

            server.setExecutor(null);
            server.start();

            System.out.println("Servidor API arrancado y escuchando en http://localhost:" + puerto);
            System.out.println("Rutas protegidas activadas.");
        } catch (Exception e) {
            System.err.println("Error al arrancar el servidor: " + e.getMessage());
        }
    }
}