package orm;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class MainJornada23 {
    public static void main(String[] args) {
        System.out.println("Iniciando el motor ORM (Hibernate 5)...");

        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            // Arrancamos la conexión manualmente
            emf = Persistence.createEntityManagerFactory("IncidenciasPU");
            em = emf.createEntityManager();

            IncidenciaRepository repo = new IncidenciaRepository(em);

            System.out.println("\nMotor arrancado. Ejecutando consultas JPQL...\n");

            System.out.println("---BUSCANDO INCIDENCIAS ABIERTAS ---");
            List<IncidenciaORM> abiertas = repo.buscarPorEstado("Abierto");
            for (IncidenciaORM inc : abiertas) {
                System.out.println("ID: " + inc.getIdIncidencia() + " | Título: " + inc.getTitulo());
            }

            System.out.println("\n---BUSCANDO INCIDENCIAS DEL TÉCNICO ID: 1 ---");
            List<IncidenciaORM> delTecnico = repo.buscarPorTecnico(1);
            for (IncidenciaORM inc : delTecnico) {
                System.out.println("ID: " + inc.getIdIncidencia() + " | Creador: " + inc.getCreador().getEmail()
                        + " | Título: " + inc.getTitulo());
            }

            System.out.println("\nPruebas ORM finalizadas con éxito.");

        } catch (Exception e) {
            System.err.println("Error al arrancar Hibernate: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}