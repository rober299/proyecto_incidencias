package orm;

import orm.servicio.IncidenciaService;
import orm.dto.IncidenciaResumenDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class MainJornada25_Integracion {
    public static void main(String[] args) {
        System.out.println("=====================================================");
        System.out.println("INICIANDO PRUEBAS DE INTEGRACIÓN EN ENTORNO AISLADO");
        System.out.println("=====================================================");

        // Usamos la unidad de persistencia de PRUEBAS, no la de producción.
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("IncidenciasTestPU");
            em = emf.createEntityManager();
            IncidenciaService servicio = new IncidenciaService(em);

            int pruebasPasadas = 0;

            // Escenario 1: Alta de nueva incidencia en base de datos de pruebas
            System.out.println("\n[TEST 1] Registrar nueva incidencia completa...");
            // ID 1 es 'test_admin@empresa.com' (creado en nuestro script semilla)
            boolean creada = servicio.registrarIncidencia("Error en Servidor Prueba", "El servidor no responde", 1);
            if (creada) {
                System.out.println("ÉXITO: Incidencia insertada correctamente.");
                pruebasPasadas++;
            } else {
                System.out.println("FALLO: No se pudo insertar la incidencia.");
            }

            // Escenario 2: Búsqueda y listado paginado
            System.out.println("\n[TEST 2] Buscar incidencias abiertas...");
            List<IncidenciaResumenDTO> pendientes = servicio.listarPendientesPaginadas(1, 10);
            if (pendientes.size() == 2) {
                System.out.println("ÉXITO: Se encontraron 2 incidencias abiertas, como se esperaba.");
                pruebasPasadas++;
            } else {
                System.out.println("FALLO: Se esperaban 2 incidencias, pero se encontraron " + pendientes.size());
            }

            // Escenario 3: Cambio de estado (Asignar Técnico)
            System.out.println("\n[TEST 3] Cambiar estado asignando técnico...");
            // Asignamos la incidencia con ID 1 al técnico con ID 2
            boolean asignada = servicio.asignarTecnico(1, 2);
            if (asignada) {
                System.out.println("ÉXITO: Estado de la incidencia 1 actualizado a 'En Progreso'.");
                pruebasPasadas++;
            } else {
                System.out.println("FALLO: Error al asignar técnico.");
            }

            // Escenario 4: Cierre final y verificación en BD
            System.out.println("\n[TEST 4] Cerrar incidencia con comentario...");
            boolean cerrada = servicio.cerrarIncidencia(1, "Solucionado durante el test de integración.");
            if (cerrada) {
                System.out.println("ÉXITO: Incidencia cerrada y comentario añadido.");
                pruebasPasadas++;
            } else {
                System.out.println("FALLO: No se pudo cerrar la incidencia.");
            }

            System.out.println("\n=====================================================");
            System.out.println("RESULTADO FINAL: " + pruebasPasadas + "/4 Pruebas superadas.");
            System.out.println("=====================================================");

        } catch (Exception e) {
            System.err.println("ERROR DURANTE LA INTEGRACIÓN: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null)
                em.close();
            if (emf != null)
                emf.close();
        }
    }
}