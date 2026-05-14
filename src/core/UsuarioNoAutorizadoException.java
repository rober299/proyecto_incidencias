package core;

public class UsuarioNoAutorizadoException extends RuntimeException {
    public UsuarioNoAutorizadoException(String accion, String rolUsuario) {
        super("Acceso denegado. Un usuario con rol '" + rolUsuario + "' no tiene permisos para: " + accion);
    }
}