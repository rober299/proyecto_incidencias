public class Incidencia {
    private int idIncidencia;
    private String titulo;
    private String descripcion;
    private int prioridad;
    private String estado;
    private int idCreador;
    private int idCategoria;

    public Incidencia(String titulo, String descripcion, int prioridad, String estado, int idCreador, int idCategoria) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.idCreador = idCreador;
        this.idCategoria = idCategoria;
    }

    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public int getIdCreador() {
        return idCreador;
    }

    public int getIdCategoria() {
        return idCategoria;
    }
}