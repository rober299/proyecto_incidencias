package orm;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "incidencias")
public class IncidenciaORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incidencia")
    private int idIncidencia;

    private String titulo;
    private String descripcion;
    private String estado;
    private int prioridad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_creador")
    private UsuarioORM creador;

    @Column(name = "id_categoria")
    private int idCategoria;

    public IncidenciaORM() {
    }

    // --- GETTERS EXISTENTES ---
    public int getIdIncidencia() {
        return idIncidencia;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEstado() {
        return estado;
    }

    public UsuarioORM getCreador() {
        return creador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCreador(UsuarioORM creador) {
        this.creador = creador;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
}