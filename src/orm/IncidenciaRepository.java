package orm;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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
}