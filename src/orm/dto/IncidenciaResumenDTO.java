package orm.dto;

public class IncidenciaResumenDTO {
    private int id;
    private String titulo;
    private String estado;
    private String emailCreador;

    public IncidenciaResumenDTO(int id, String titulo, String estado, String emailCreador) {
        this.id = id;
        this.titulo = titulo;
        this.estado = estado;
        this.emailCreador = emailCreador;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Estado: " + estado + " | Creador: " + emailCreador + " | Título: " + titulo;
    }

    // Getters
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getEstado() { return estado; }
    public String getEmailCreador() { return emailCreador; }
}