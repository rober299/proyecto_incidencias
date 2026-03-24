import java.io.*;
import java.util.*;

// Clase que representa al Usuario para exportarlo a JSON
class UsuarioJSON {
    int id;
    String nombre;
    String rol;

    public UsuarioJSON(int id, String nombre, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario [ID: " + id + ", Nombre: " + nombre + ", Rol: " + rol + "]";
    }
}

public class GestorJSON {

    // Modulo de exportacion JSON
    public static void exportar(List<UsuarioJSON> usuarios, String ruta) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
            pw.println("["); // Empieza el array JSON
            for (int i = 0; i < usuarios.size(); i++) {
                UsuarioJSON u = usuarios.get(i);
                pw.println("  {");
                pw.println("    \"id\": " + u.id + ",");
                pw.println("    \"nombre\": \"" + u.nombre + "\",");
                pw.println("    \"rol\": \"" + u.rol + "\"");
                pw.print("  }");
                if (i < usuarios.size() - 1)
                    pw.println(",");
                else
                    pw.println();
            }
            pw.println("]");
            System.out.println("JSON exportado correctamente en: " + ruta);
        } catch (IOException e) {
            System.out.println("Error crítico al exportar JSON: " + e.getMessage());
        }
    }

    // Modulo de importacion JSON
    public static List<UsuarioJSON> importar(String ruta) {
        List<UsuarioJSON> cargados = new ArrayList<>();
        System.out.println("\nIniciando lectura de JSON desde: " + ruta);

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            int id = 0;
            String nombre = "";
            String rol = "";

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

                if (linea.startsWith("\"id\":")) {
                    id = Integer.parseInt(linea.split(":")[1].replaceAll("[^0-9]", ""));
                } else if (linea.startsWith("\"nombre\":")) {
                    nombre = linea.split(":")[1].replaceAll("[\",]", "").trim();
                } else if (linea.startsWith("\"rol\":")) {
                    rol = linea.split(":")[1].replaceAll("[\",]", "").trim();
                } else if (linea.equals("}") || linea.equals("},")) {
                    // Cuando cerramos la llave, guardamos el usuario y reseteamos
                    cargados.add(new UsuarioJSON(id, nombre, rol));
                }
            }
            System.out.println("Importación JSON finalizada. " + cargados.size() + " usuarios cargados.");
        } catch (Exception e) {
            System.out.println("Error al leer fichero JSON: " + e.getMessage());
        }
        return cargados;
    }

    // Ejecución
    public static void main(String[] args) {
        String ruta = "exportaciones/usuarios.json";

        // 1. Generamos los usuarios
        List<UsuarioJSON> listaOriginal = Arrays.asList(
                new UsuarioJSON(101, "Roberto", "ADMIN"),
                new UsuarioJSON(102, "Ana", "TECNICO"));

        // 2. Serializamos (Exportar a JSON)
        System.out.println("Generando fichero JSONs");
        exportar(listaOriginal, ruta);

        // 3. Deserializamos (Consumir de JSON)
        List<UsuarioJSON> importados = importar(ruta);

        // 4. Validamos que la estructura mantiene la info necesaria
        System.out.println("\n--- RESULTADO VALIDADO EN MEMORIA ---");
        for (UsuarioJSON u : importados) {
            System.out.println(u);
        }
    }
}