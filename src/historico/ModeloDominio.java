// Entidad Usuario
class Usuario {
    private String nombre;
    private String email;
    private String rol;

    public Usuario(String nombre, String email, String rol) {
        this.nombre = nombre;
        setEmail(email);
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEmail(String email) {
        // Validación básica de datos
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Error: Formato de email inválido.");
        }
        this.email = email;
    }

    @Override
    public String toString() {
        return nombre + " (" + rol + ")";
    }
}

// Entidad Incidencia
class Incidencia {
    private int id;
    private String titulo;
    private String estado;
    private Usuario creador;
    private Usuario tecnicoAsignado;

    public Incidencia(int id, String titulo, Usuario creador) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: El titulo es obligatorio.");
        }
        this.id = id;
        this.titulo = titulo;
        this.estado = "Abierta"; // Estado por defecto al crear
        this.creador = creador;
    }

    public void asignarTecnico(Usuario tecnico) {
        this.tecnicoAsignado = tecnico;
        this.estado = "Asignada";
        System.out.println("Acción: Técnico " + tecnico.getNombre() + " asignado a la incidencia " + id);
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        System.out.println("Acción: Estado cambiado a '" + estado + "'");
    }

    public void cerrarIncidencia() {
        this.estado = "Cerrada";
        System.out.println("Acción: Incidencia " + id + " cerrada correctamente.");
    }

    public void mostrarInfo() {
        String tecnicoStr = (tecnicoAsignado != null) ? tecnicoAsignado.getNombre() : "Sin asignar";
        System.out.println("[Incidencia " + id + "] " + titulo + " | Estado: " + estado + " | Creador: "
                + creador.getNombre() + " | Técnico: " + tecnicoStr);
    }
}

public class ModeloDominio {
    public static void main(String[] args) {
        System.out.println("Inicio de escenarios de prueba");

        try {
            // Escenario A: Alta de incidencia
            System.out.println("\nEscenario 1: Alta de Incidencia");
            Usuario creador = new Usuario("Ana", "ana@empresa.com", "Empleado");
            Incidencia inc1 = new Incidencia(101, "El ordenador no enciende", creador);
            inc1.mostrarInfo();

            // Escenario B: Asignación
            System.out.println("\nEscenario 2: Asignación de Tecnico");
            Usuario tecnico = new Usuario("Roberto", "rober@empresa.com", "Soporte");
            inc1.asignarTecnico(tecnico);
            inc1.mostrarInfo();

            // Escenario C: Cierre
            System.out.println("\nEscenario 3: Cierre de Incidencia");
            inc1.cerrarIncidencia();
            inc1.mostrarInfo();

            // Escenario D: Error por datos inválidos
            System.out.println("\nEscenario 4: Prueba de error por datos inválidos");
            System.out.println("Intentando crear usuario con email 'carlos.com'");
            Usuario usuarioMalo = new Usuario("Carlos", "carlos.com", "Empleado");

        } catch (IllegalArgumentException e) {
            System.out.println("Excepción capturada con éxito: " + e.getMessage());
        }

        System.out.println("\nFinal de las pruebas");
    }
}