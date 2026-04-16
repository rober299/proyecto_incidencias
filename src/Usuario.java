public class Usuario {
    private int idUsuario;
    private String email;
    private String password;
    private boolean activo;
    private int idRol;

    public Usuario(String email, String password, boolean activo, int idRol) {
        this.email = email;
        this.password = password;
        this.activo = activo;
        this.idRol = idRol;
    }

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActivo() {
        return activo;
    }

    public int getIdRol() {
        return idRol;
    }
}