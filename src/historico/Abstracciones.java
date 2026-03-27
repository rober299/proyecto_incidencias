import java.util.ArrayList;
import java.util.List;

interface Exportable {
    String exportarDatos();
}

interface Notificable {
    void enviarMensaje(String mensaje);
}

abstract class UsuarioBase implements Exportable, Notificable {
    protected String nombre;
    protected String email;

    public UsuarioBase(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract String getRolEspecifico();

    @Override
    public void enviarMensaje(String mensaje) {
        System.out.println("[Notificación para " + this.email + "] -> " + mensaje);
    }
}

class Tecnico extends UsuarioBase {
    private String especialidad;

    public Tecnico(String nombre, String email, String especialidad) {
        super(nombre, email);
        this.especialidad = especialidad;
    }

    @Override
    public String getRolEspecifico() {
        return "Técnico (" + especialidad + ")";
    }

    @Override
    public String exportarDatos() {
        return "{ \"tipo\": \"Técnico\", \"nombre\": \"" + nombre + "\", \"especialidad\": \"" + especialidad + "\" }";
    }
}

class Cliente extends UsuarioBase {
    private String empresa;

    public Cliente(String nombre, String email, String empresa) {
        super(nombre, email);
        this.empresa = empresa;
    }

    @Override
    public String getRolEspecifico() {
        return "Cliente (" + empresa + ")";
    }

    @Override
    public String exportarDatos() {
        return "{ \"tipo\": \"Cliente\", \"nombre\": \"" + nombre + "\", \"empresa\": \"" + empresa + "\" }";
    }
}

public class Abstracciones {
    public static void main(String[] args) {
        System.out.println("Inicio de demostracion de polimorfismo\n");

        // 1. Creamos una lista genérica de la clase abstracta
        List<UsuarioBase> listaUsuarios = new ArrayList<>();

        // 2. Añadimos objetos de distintos tipos concretos a la misma lista
        listaUsuarios.add(new Tecnico("Roberto", "rober@empresa.com", "Redes y Sistemas"));
        listaUsuarios.add(new Cliente("Ana", "ana@cliente.com", "Wilky"));

        // 3. Iteramos la lista. El mismo bucle ejecuta comportamientos distintos según
        // el objeto real.
        System.out.println("Exportando datos");
        for (UsuarioBase usuario : listaUsuarios) {
            System.out.println(usuario.exportarDatos());
        }

        System.out.println("\nEnviando notificaciones");
        for (UsuarioBase usuario : listaUsuarios) {
            // Llama al método común heredado de la clase madre
            usuario.enviarMensaje("El sistema se actualizará esta noche.");
        }

        System.out.println("\nDemostración acabada");
    }
}