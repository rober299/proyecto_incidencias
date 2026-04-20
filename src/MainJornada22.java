import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainJornada22 {
    public static void main(String[] args) {
        try (Connection conn = ConexionBD.obtenerConexion()) {
            if (conn != null) {
                TransaccionService servicio = new TransaccionService(conn);

                int idUsuarioPrueba = 1;
                int idCategoriaPrueba = 1;

                System.out.println(" Escenario 1: Transacción exitosa");
                System.out.println("================================================");
                servicio.crearIncidenciaConAuditoria("Ticket Normal", "Todo funciona bien", idUsuarioPrueba,
                        idCategoriaPrueba, false);

                System.out.println(" Escenario 2: Prueba de rollback (fallo forzado)");
                System.out.println("================================================");

                int conteoAntes = contarIncidencias(conn);
                System.out.println("Incidencias en la base de datos ANTES del fallo: " + conteoAntes);

                // Le pasamos 'true' para que falle
                servicio.crearIncidenciaConAuditoria("Ticket Peligroso", "Esto va a fallar", idUsuarioPrueba,
                        idCategoriaPrueba, true);

                int conteoDespues = contarIncidencias(conn);
                System.out.println("Incidencias en la base de datos DESPUÉS del fallo: " + conteoDespues);

                if (conteoAntes == conteoDespues) {
                    System.out.println("Éxito: El Rollback funcionó perfectamente. La BD está intacta.");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método auxiliar para contar incidencias y demostrar el "antes y después"
    private static int contarIncidencias(Connection conn) {
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM INCIDENCIAS")) {
            if (rs.next())
                return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}