// 1. Error genérico de validación (campos vacíos, nulos, formatos raros)
class ValidacionDatosException extends RuntimeException {
    public ValidacionDatosException(String mensaje) {
        super(mensaje);
    }
}

// 2. Error cuando se busca una incidencia que no existe en la base de datos
class IncidenciaNoEncontradaException extends RuntimeException {
    public IncidenciaNoEncontradaException(String idIncidencia) {
        super("No se ha encontrado ninguna incidencia con el ID: " + idIncidencia);
    }
}

// 3. Error cuando se intenta una transición de estado prohibida
class EstadoInvalidoException extends RuntimeException {
    public EstadoInvalidoException(String estadoActual, String estadoNuevo) {
        super("Transición de estado no permitida: de '" + estadoActual + "' a '" + estadoNuevo + "'");
    }
}

// 4. Error de seguridad / permisos
class UsuarioNoAutorizadoException extends RuntimeException {
    public UsuarioNoAutorizadoException(String accion, String rolUsuario) {
        super("Acceso denegado. Un usuario con rol '" + rolUsuario + "' no tiene permisos para: " + accion);
    }
}