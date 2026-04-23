package orm.servicio;

import orm.IncidenciaORM;
import orm.IncidenciaRepository;
import orm.UsuarioORM;
import orm.dto.IncidenciaResumenDTO;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class IncidenciaService {
    private EntityManager em;
    private IncidenciaRepository repo;

    public IncidenciaService(EntityManager em) {
        this.em = em;
        this.repo = new IncidenciaRepository(em);
    }

    // Caso de uso: Listar pendientes (DTOs)
    public List<IncidenciaResumenDTO> listarPendientesPaginadas(int pagina, int tamanoPagina) {
        // Llamamos al nuevo método del repositorio pasándole los null a los filtros que
        // no usamos ahora
        List<IncidenciaORM> incidencias = repo.buscarConFiltrosPaginado("Abierto", null, null, null, null, null, null,
                pagina, tamanoPagina);
        List<IncidenciaResumenDTO> dtos = new ArrayList<>();

        for (IncidenciaORM inc : incidencias) {
            String email = (inc.getCreador() != null) ? inc.getCreador().getEmail() : "Sin asignar";
            dtos.add(new IncidenciaResumenDTO(inc.getIdIncidencia(), inc.getTitulo(), inc.getEstado(), email));
        }
        return dtos;
    }

    // Caso de uso: Registrar incidencia
    public boolean registrarIncidencia(String titulo, String descripcion, int idCreador) {
        try {
            em.getTransaction().begin();
            UsuarioORM creador = em.find(UsuarioORM.class, idCreador);
            if (creador == null) {
                em.getTransaction().rollback();
                return false;
            }
            IncidenciaORM nueva = new IncidenciaORM();
            nueva.setEstado("Abierto");
            em.persist(nueva);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    // Caso de uso: Asignar técnico
    public boolean asignarTecnico(int idIncidencia, int idTecnico) {
        try {
            em.getTransaction().begin();
            IncidenciaORM incidencia = em.find(IncidenciaORM.class, idIncidencia);
            UsuarioORM tecnico = em.find(UsuarioORM.class, idTecnico);
            if (incidencia == null || tecnico == null) {
                em.getTransaction().rollback();
                return false;
            }
            // Aquí se asignaría el técnico. Usamos el estado como ejemplo de modificación.
            incidencia.setEstado("En Progreso");
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    // Caso de uso: Cerrar incidencia con un comentario final
    public boolean cerrarIncidencia(int idIncidencia, String comentarioFinal) {
        try {
            em.getTransaction().begin(); // Iniciamos la transacción

            IncidenciaORM incidencia = em.find(IncidenciaORM.class, idIncidencia);
            if (incidencia == null || "Cerrado".equals(incidencia.getEstado())) {
                em.getTransaction().rollback();
                return false; // No existe o ya está cerrada
            }

            // Cambiamos el estado y añadimos comentario a la descripción
            incidencia.setEstado("Cerrado");
            incidencia.setDescripcion(incidencia.getDescripcion() + "\n[CIERRE]: " + comentarioFinal);

            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}