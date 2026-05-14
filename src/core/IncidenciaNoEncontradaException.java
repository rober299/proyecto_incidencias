package core;

public class IncidenciaNoEncontradaException extends RuntimeException {
    public IncidenciaNoEncontradaException(String idIncidencia) {
        super("No se ha encontrado ninguna incidencia con el ID: " + idIncidencia);
    }
}
