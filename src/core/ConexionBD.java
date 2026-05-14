package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {
    public static Connection obtenerConexion() {
        Properties props = new Properties();
        // Le indicamos la ruta exacta donde tienes tu archivo
        try (FileInputStream fis = new FileInputStream("config/app.properties")) {
            props.load(fis);
            return DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"),
                    props.getProperty("db.password"));
        } catch (IOException | SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}