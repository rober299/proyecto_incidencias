import java.util.ArrayList;
import java.util.List;

public class Algoritmia {

    public static class Ticket {
        int id;
        String estado;
        String fecha;

        public Ticket(int id, String estado, String fecha) {
            this.id = id;
            this.estado = estado;
            this.fecha = fecha;
        }

        @Override
        public String toString() {
            return "{id=" + id + ", estado='" + estado + "', fecha='" + fecha + "'}";
        }
    }

    // Refactorizacion 1: Retorno directo de la condición lógica
    public static boolean esUsuarioValido(String email, String password) {
        return email.contains("@") && password.length() >= 8;
    }

    // Refactorizacion 2: Simplificación de lógica
    public static String calcularPrioridad(String impacto, String urgencia) {
        if (impacto.equals("Alto")) {
            return urgencia.equals("Alta") ? "Crítica" : "Alta";
        }
        return urgencia.equals("Alta") ? "Media" : "Baja";
    }

    // Refactorizacion 3: Nombres más semánticos
    public static List<Ticket> filtrarTicketsPorEstadoYFecha(List<Ticket> tickets, String estado, String fecha) {
        List<Ticket> ticketsFiltrados = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (ticket.estado.equals(estado) && ticket.fecha.equals(fecha)) {
                ticketsFiltrados.add(ticket);
            }
        }
        return ticketsFiltrados;
    }

    // Refactorizacion 4: Separación total de Entrada/Salida
    public static void main(String[] args) {
        ejecutarPruebasUsuario();
        ejecutarPruebasPrioridad();
        ejecutarPruebasFiltrado();
    }

    private static void ejecutarPruebasUsuario() {
        System.out.println("--- Ejercicio 1: Validación ---");
        System.out.println("Prueba correcta: " + esUsuarioValido("empleado@empresa.com", "secreta123"));
    }

    private static void ejecutarPruebasPrioridad() {
        System.out.println("\n--- Ejercicio 2: Prioridad ---");
        System.out.println("Alto/Alta -> " + calcularPrioridad("Alto", "Alta"));
    }

    private static void ejecutarPruebasFiltrado() {
        System.out.println("\n--- Ejercicio 3: Filtrado ---");
        List<Ticket> mockTickets = new ArrayList<>();
        // Fechas actualizadas a 16 de marzo
        mockTickets.add(new Ticket(1, "Abierto", "2026-03-16"));
        mockTickets.add(new Ticket(2, "Cerrado", "2026-03-15"));
        mockTickets.add(new Ticket(3, "Abierto", "2026-03-16"));

        System.out.println("Filtro 'Abierto' y '2026-03-16': "
                + filtrarTicketsPorEstadoYFecha(mockTickets, "Abierto", "2026-03-16"));
    }
}