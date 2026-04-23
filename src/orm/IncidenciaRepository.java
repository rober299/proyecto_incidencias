package orm;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Date;

public class IncidenciaRepository {

    private EntityManager em;

    public IncidenciaRepository(EntityManager em) {
        this.em = em;
    }

    // Consulta frecuente 1: Buscar por estado
    public List<IncidenciaORM> buscarPorEstado(String estado) {
        String jpql = "SELECT i FROM IncidenciaORM i WHERE i.estado = :est";
        TypedQuery<IncidenciaORM> query = em.createQuery(jpql, IncidenciaORM.class);
        query.setParameter("est", estado);
        return query.getResultList();
    }

    // Consulta frecuente 2: Buscar por técnico (creador)
    public List<IncidenciaORM> buscarPorTecnico(int idTecnico) {
        String jpql = "SELECT i FROM IncidenciaORM i WHERE i.creador.idUsuario = :idTec";
        TypedQuery<IncidenciaORM> query = em.createQuery(jpql, IncidenciaORM.class);
        query.setParameter("idTec", idTecnico);
        return query.getResultList();
    }

    // Búsqueda avanzada con todos los Filtros y Paginación
    public List<IncidenciaORM> buscarConFiltrosPaginado(String estado, Integer prioridad, String textoLibre,
            Integer idCategoria, Integer idTecnico, Date fechaInicio, Date fechaFin, int pagina, int tamanoPagina) {
        StringBuilder jpql = new StringBuilder("SELECT i FROM IncidenciaORM i WHERE 1=1");

        // Aplicación dinámica de filtros
        if (estado != null && !estado.isEmpty())
            jpql.append(" AND i.estado = :est");
        if (prioridad != null)
            jpql.append(" AND i.prioridad = :prio");
        if (idCategoria != null)
            jpql.append(" AND i.idCategoria = :cat");
        if (idTecnico != null)
            jpql.append(" AND i.creador.idUsuario = :idTec");
        if (textoLibre != null && !textoLibre.isEmpty())
            jpql.append(" AND (i.titulo LIKE :texto OR i.descripcion LIKE :texto)");

        jpql.append(" ORDER BY i.idIncidencia DESC");

        TypedQuery<IncidenciaORM> query = em.createQuery(jpql.toString(), IncidenciaORM.class);

        // Asignación de parámetros
        if (estado != null && !estado.isEmpty())
            query.setParameter("est", estado);
        if (prioridad != null)
            query.setParameter("prio", prioridad);
        if (idCategoria != null)
            query.setParameter("cat", idCategoria);
        if (idTecnico != null)
            query.setParameter("idTec", idTecnico);
        if (textoLibre != null && !textoLibre.isEmpty())
            query.setParameter("texto", "%" + textoLibre + "%");

        // Paginación
        query.setFirstResult((pagina - 1) * tamanoPagina);
        query.setMaxResults(tamanoPagina);

        return query.getResultList();
    }
}