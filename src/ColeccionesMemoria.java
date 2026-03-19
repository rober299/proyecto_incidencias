import java.util.*;
import java.util.function.Predicate;

// Modelos de datos básicos
class Categoria {
    String nombre;

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    // Para que el Set detecte duplicados reales
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(nombre, categoria.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
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

    public String getNombre() {
        return nombre;
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

    public String getEstado() {
        return estado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public Usuario getTecnico() {
        return tecnico;
    }

    @Override
    public String toString() {
        return "[Incidencia " + id + "] " + titulo + " | Estado: " + estado + " | Prioridad: " + prioridad;
    }
}

public class ColeccionesMemoria {

    // Utilidad genérica para filtrar listas de cualquier tipo
    public static <T> List<T> filtrarListaGen(List<T> lista, Predicate<T> condicion) {
        List<T> resultado = new ArrayList<>();
        for (T elemento : lista) {
            if (condicion.test(elemento)) {
                resultado.add(elemento);
            }
        }
        return resultado;
    }

    public static void main(String[] args) {

        // Carga inicial de datos de prueba
        Usuario tec1 = new Usuario("Roberto", "Soporte N1");
        Usuario tec2 = new Usuario("Ana", "Soporte N2");

        // Set para las categorías para que no se nos cuelen repetidas
        Set<Categoria> categorias = new HashSet<>();
        categorias.add(new Categoria("Hardware"));
        categorias.add(new Categoria("Software"));
        categorias.add(new Categoria("Software")); // lo ignora correctamente

        // Map para el inventario, así podemos buscar por código del tiron
        Map<String, Activo> inventario = new HashMap<>();
        inventario.put("PC-01", new Activo("PC-01", "Portátil Dell"));

        // Listas normales para lo demás mantiene el orden de llegada
        List<Incidencia> incidencias = new ArrayList<>();
        incidencias.add(new Incidencia(101, "Pantalla rota", "Abierta", "Media", tec1));
        incidencias.add(new Incidencia(102, "Caída de servidor", "En Progreso", "Alta", tec2));
        incidencias.add(new Incidencia(103, "Ratón no funciona", "Cerrada", "Baja", tec1));
        incidencias.add(new Incidencia(104, "Teclado sin pilas", "Abierta", "Baja", tec1));

        System.out.println("Datos cargados en memoria\n");

        System.out.println("Categorías (Guardadas en un Set):");
        for (Categoria c : categorias) {
            System.out.println(" - " + c);
        }

        System.out.println("\nInventario de Activos:");
        for (Map.Entry<String, Activo> entry : inventario.entrySet()) {
            System.out.println(" - Clave: " + entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("Pruebas de operaciones\n");

        // 1. Sacamos las incidencias
        System.out.println("Incidencias de Roberto");
        List<Incidencia> incRoberto = filtrarListaGen(incidencias, i -> i.getTecnico().getNombre().equals("Roberto"));
        for (Incidencia i : incRoberto)
            System.out.println(i);

        // 2. Agrupamos y contamos incidencias según su estado
        System.out.println("\nConteo por estado");
        Map<String, Integer> conteoEstados = new HashMap<>();
        for (Incidencia i : incidencias) {
            conteoEstados.put(i.getEstado(), conteoEstados.getOrDefault(i.getEstado(), 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : conteoEstados.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // 3. Ordenamos la lista por prioridad (Alta, luego Media, luego Baja)
        System.out.println("\nOrdenado por prioridad");
        List<Incidencia> incidenciasOrdenadas = new ArrayList<>(incidencias);
        incidenciasOrdenadas.sort((i1, i2) -> {
            // Les damos un peso numérico rápido para poder comparar
            int peso1 = i1.getPrioridad().equals("Alta") ? 1 : (i1.getPrioridad().equals("Media") ? 2 : 3);
            int peso2 = i2.getPrioridad().equals("Alta") ? 1 : (i2.getPrioridad().equals("Media") ? 2 : 3);
            return Integer.compare(peso1, peso2);
        });
        for (Incidencia i : incidenciasOrdenadas)
            System.out.println(i);
    }
}