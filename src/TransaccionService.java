import java.sql.*;

public class TransaccionService {
    private Connection conn;

    public TransaccionService(Connection conn) {
        this.conn = conn;
    }

    // Método que intenta hacer 2 INSERTs. Si uno falla, se cancela todo.
    public boolean crearIncidenciaConAuditoria(String titulo, String desc, int idUsuario, int idCategoria,
            boolean forzarError) {
        // Bloque 1: Consultas preparadas (uso de ? para evitar Inyección SQL)
        String insertIncidencia = "INSERT INTO INCIDENCIAS (titulo, descripcion, prioridad, estado, id_creador, id_categoria) VALUES (?, ?, 3, 'Abierto', ?, ?)";
        String insertAuditoria = "INSERT INTO AUDITORIA (accion, id_usuario, id_incidencia) VALUES (?, ?, ?)";

        try {
            // Bloque 2: Iniciar transacción manual
            conn.setAutoCommit(false); // Le decimos a MySQL que espere hasta que le demos permiso nosotros para
                                       // guardar
            System.out.println("Iniciando transacción...");

            int idIncidenciaGenerado = -1;

            try (PreparedStatement stmtInc = conn.prepareStatement(insertIncidencia, Statement.RETURN_GENERATED_KEYS)) {
                stmtInc.setString(1, titulo);
                stmtInc.setString(2, desc);
                stmtInc.setInt(3, idUsuario);
                stmtInc.setInt(4, idCategoria);
                stmtInc.executeUpdate();

                try (ResultSet rs = stmtInc.getGeneratedKeys()) {
                    if (rs.next()) {
                        idIncidenciaGenerado = rs.getInt(1);
                        System.out.println(
                                "  -> Paso 1 OK: Incidencia insertada temporalmente con ID: " + idIncidenciaGenerado);
                    }
                }
            }

            // Bloque 3: Simulamos un fallo si el parámetro forzarError es true
            if (forzarError) {
                System.out.println("  -> Atencion! Simulando un fallo del servidor antes de terminar...");
                throw new SQLException("Se ha caído la red a mitad del proceso.");
            }

            // Si no hay error, seguimos con el paso 2: La auditoría
            try (PreparedStatement stmtAud = conn.prepareStatement(insertAuditoria)) {
                stmtAud.setString(1, "CREACION_TICKET_TRANSACCION");
                stmtAud.setInt(2, idUsuario);
                stmtAud.setInt(3, idIncidenciaGenerado);
                stmtAud.executeUpdate();
                System.out.println("  -> Paso 2 OK: Auditoría registrada temporalmente.");
            }

            // Si llegamos hasta aquí, todo ha ido bien. Guardamos definitivamente.
            conn.commit();
            System.out.println("Transacción completada. Datos guardados en la BD.");
            return true;

        } catch (SQLException e) {
            // Bloque 3 (Continuación): Tratamiento del error y Rollback
            System.err.println("Error controlado: " + e.getMessage());
            try {
                System.err.println("Ejecutando rollback... Deshaciendo la incidencia para no dejar datos.");
                conn.rollback(); // Cancelamos todo
            } catch (SQLException ex) {
                System.err.println("Error crítico al hacer rollback: " + ex.getMessage());
            }
            return false;
        } finally {
            // Siempre restauramos el comportamiento por defecto de la conexión
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}