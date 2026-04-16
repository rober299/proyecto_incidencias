import java.sql.Connection;
import java.sql.SQLException;

public class MainPruebas {
    public static void main(String[] args) {
        try (Connection conn = ConexionBD.obtenerConexion()) {
            if (conn != null) {
                System.out.println("Conexión a la base de datos establecida correctamente.");

                UsuarioDAO usuarioDAO = new UsuarioDAOImpl(conn);
                IncidenciaDAO incidenciaDAO = new IncidenciaDAOImpl(conn);

                // 1. Probar INSERT Usuario
                Usuario nuevoUser = new Usuario("java_dao2@empresa.com", "hash123", true, 2);
                nuevoUser = usuarioDAO.insertar(nuevoUser);
                System.out.println("Usuario insertado con ID: " + nuevoUser.getIdUsuario());

                // 2. Probar SELECT
                Usuario leido = usuarioDAO.obtenerPorId(nuevoUser.getIdUsuario());
                if (leido != null) {
                    System.out.println("Usuario leído: " + leido.getEmail() + " | Activo: " + leido.isActivo());
                }

                // 3. Probar INSERT Incidencia
                Incidencia nuevaInci = new Incidencia("Fallo en BD", "Prueba desde Java", 1, "Abierto",
                        nuevoUser.getIdUsuario(), 1);
                nuevaInci = incidenciaDAO.insertar(nuevaInci);
                System.out.println("Incidencia insertada con ID: " + nuevaInci.getIdIncidencia());

                // 4. Probar BORRADO LÓGICO
                boolean borrado = usuarioDAO.eliminarLogico(nuevoUser.getIdUsuario());
                System.out.println("Borrado lógico ejecutado: " + borrado);
                Usuario userBorrado = usuarioDAO.obtenerPorId(nuevoUser.getIdUsuario());
                System.out.println("   -> Estado activo del usuario ahora: " + userBorrado.isActivo());

            } else {
                System.out.println("Fallo en la conexión.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}