package core;

public class EstadoInvalidoException extends RuntimeException {
    public EstadoInvalidoException(String estadoActual, String estadoNuevo) {
        super("Transición de estado no permitida: de '" + estadoActual + "' a '" + estadoNuevo + "'");
    }
}