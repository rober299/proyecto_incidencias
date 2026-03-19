import java.util.*;

// Clases basicas para el modelo de datos simulado
class Categoria {
    String nombre;

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

class Activo {
    String codigo;
    String tipo;

    public Activo(String codigo, String tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo + " [" + codigo + "]";
    }
}

class Usuario {
    String nombre;
    String rol;

    public Usuario(String nombre, String rol) {
        this.nombre = nombre;
        this.rol = rol;
    }

    @Override
    public String toString() {
        return nombre + " (" + rol + ")";
    }
}

class Incidencia {
    int id;
    String titulo;
    String estado;
    String prioridad;
    Usuario tecnico;

    public Incidencia(int id, String titulo, String estado, String prioridad, Usuario tecnico) {
        this.id = id;
        this.titulo = titulo;
        this.estado = estado;
        this.prioridad = prioridad;
        this.tecnico = tecnico;
    }

    @Override
    public String toString() {
        return "Incidencia " + id + " - " + titulo + " | Prioridad: " + prioridad;
    }
}

// Dataset y colecciones
public class ColeccionesMemoria {
    public static void main(String[] args) {
        System.out.println("Dataset simulado en memoria");

        // Set: Usado para categorias porque no queremos duplicados.
        Set<Categoria> categorias = new HashSet<>();
        categorias.add(new Categoria("Hardware"));
        categorias.add(new Categoria("Software"));
        categorias.add(new Categoria("Redes"));
        categorias.add(new Categoria("Redes")); // intento de duplicado

        // Map: Usado para Activos para búsqueda rápida por su codigo
        Map<String, Activo> inventario = new HashMap<>();
        inventario.put("PC-01", new Activo("PC-01", "Portátil Dell"));
        inventario.put("SW-99", new Activo("SW-99", "Licencia Windows"));

        // List: Usada para usuarios e incidencias porque importa el orden de inserción
        // y puede haber repetidos.
        List<Usuario> usuarios = new ArrayList<>();
        Usuario tec1 = new Usuario("Roberto", "Soporte N1");
        Usuario tec2 = new Usuario("Ana", "Soporte N2");
        usuarios.add(tec1);
        usuarios.add(tec2);

        List<Incidencia> incidencias = new ArrayList<>();
        incidencias.add(new Incidencia(101, "Pantalla rota", "Abierta", "Media", tec1));
        incidencias.add(new Incidencia(102, "Caída de servidor", "En Progreso", "Crítica", tec2));
        incidencias.add(new Incidencia(103, "Ratón no funciona", "Cerrada", "Baja", tec1));

        // Recorremos las colecciones
        System.out.println("\nCategorías cargadas (Set):");
        for (Categoria c : categorias)
            System.out.println("  - " + c);

        System.out.println("\nInventario cargado (Map):");
        for (Map.Entry<String, Activo> entry : inventario.entrySet()) {
            System.out.println("  - Clave: " + entry.getKey() + " -> Valor: " + entry.getValue());
        }

        System.out.println("\nIncidencias cargadas (List):");
        for (Incidencia i : incidencias)
            System.out.println("  - " + i);
    }
}