public class PruebaErrores {

    // Simulamos un logger básico
    private static void logError(String tipo, Exception e) {
        System.out.println("[LOG INTERNO - " + tipo + "] Traza técnica: " + e.getMessage());
    }

    private static void mostrarUsuario(String mensaje) {
        System.out.println("-> [PANTALLA USUARIO]: Error  " + mensaje + "\n");
    }

    // Simulacion logica del negocio
    public static void validarTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new ValidacionDatosException("El título de la incidencia no puede estar vacío.");
        }
    }

    public static void cerrarIncidencia(String id, String estadoActual) {
        if (!estadoActual.equals("EN_PROGRESO")) {
            throw new EstadoInvalidoException(estadoActual, "CERRADA");
        }
    }

    public static void asignarTecnico(String rolUsuario) {
        if (rolUsuario.equals("CLIENTE")) {
            throw new UsuarioNoAutorizadoException("Asignar técnico", rolUsuario);
        }
    }

    // Ejecucion de las pruebas
    public static void main(String[] args) {
        System.out.println("Prueba de errores\n");

        // Prueba 1: Disparar ValidacionDatosException
        try {
            System.out.println("Prueba 1: Intentando crear incidencia sin título...");
            validarTitulo("   ");
        } catch (ValidacionDatosException e) {
            logError("ERROR_VALIDACION", e);
            mostrarUsuario("Por favor, rellena el título para continuar.");
        }

        // Prueba 2: Disparar EstadoInvalidoException
        try {
            System.out.println("Prueba 2: Intentando cerrar incidencia recién abierta...");
            cerrarIncidencia("INC-001", "ABIERTA");
        } catch (EstadoInvalidoException e) {
            logError("ERROR_ESTADO", e);
            mostrarUsuario("No puedes cerrar una incidencia que no está en progreso.");
        }

        // Prueba 3: Disparar UsuarioNoAutorizadoException
        try {
            System.out.println("Prueba 3: Cliente intentando asignarse una avería...");
            asignarTecnico("CLIENTE");
        } catch (UsuarioNoAutorizadoException e) {
            logError("SEGURIDAD", e);
            mostrarUsuario("No tienes permisos suficientes para realizar esta acción.");
        }
    }
}