public class LogicaNegocio {

    // 1. Cálculo de prioridad
    public static int calcularPrioridad(int urgencia, int impacto) {
        if (urgencia <= 0 || impacto <= 0) {
            throw new IllegalArgumentException("La urgencia y el impacto deben ser mayores que 0.");
        }
        return urgencia * impacto; // El bug inicial era que sumaba (urgencia + impacto)
    }

    // 2. Cambio de estado
    public static String cambiarEstado(String estadoActual, String nuevoEstado) {
        if (estadoActual.equals("CERRADA") && nuevoEstado.equals("EN_PROGRESO")) {
            throw new IllegalStateException("Un ticket cerrado no puede reabrirse directamente sin revisión.");
        }
        return nuevoEstado;
    }

    // 3. Validación de usuario
    public static void validarUsuario(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío.");
        }
    }

    // 4. Asignación de técnico
    public static void asignarTecnico(String rol) {
        if (rol.equals("CLIENTE")) {
            throw new SecurityException("Un cliente no puede ser asignado como técnico de la incidencia.");
        }
    }
}