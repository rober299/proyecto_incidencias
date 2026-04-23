package orm;

import orm.servicio.IncidenciaService;
import orm.dto.IncidenciaResumenDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class MainJornada24 {
    public static void main(String[] args) {
        System.out.println("Arrancando sistema con Capa de Servicio y DTOs...");

        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("IncidenciasPU");
            em = emf.createEntityManager();

            // Usamos la capa de servicio
            IncidenciaService servicio = new IncidenciaService(em);

            System.out.println("\n---LISTADO DE INCIDENCIAS PENDIENTES---");
            List<IncidenciaResumenDTO> pagina1 = servicio.listarPendientesPaginadas(1, 3);

            if (pagina1.isEmpty()) {
                System.out.println("No hay incidencias abiertas en esta página.");
            } else {
                for (IncidenciaResumenDTO dto : pagina1) {
                    System.out.println(dto.toString());
                }
            }

            System.out.println("\n---CERRANDO INCIDENCIA ID: 1 ---");
            boolean cerrada = servicio.cerrarIncidencia(1, "Revisión completada por el técnico. Todo solucionado.");

            if (cerrada) {
                System.out.println("Incidencia cerrada y comentario guardado correctamente.");
            } else {
                System.out.println("No se pudo cerrar la incidencia (quizás no existe o ya estaba cerrada).");
            }

            System.out.println("\nPruebas de la Capa de Servicio finalizadas con éxito.");

        } catch (Exception e) {
            System.err.println("Error en la ejecución: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null)
                em.close();
            if (emf != null)
                emf.close();
        }
    }
}