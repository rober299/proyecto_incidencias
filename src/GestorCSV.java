import java.io.*;
import java.util.*;

// Clase básica que representa nuestra entidad para el CSV
class IncidenciaCSV {
    int id;
    String titulo;
    int urgencia;
    String estado;

    public IncidenciaCSV(int id, String titulo, int urgencia, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.urgencia = urgencia;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "[ID: " + id + "] " + titulo + " | Urgencia: " + urgencia + " | Estado: " + estado;
    }
}

public class GestorCSV {

    // Módulo de exportación
    public static void exportar(List<IncidenciaCSV> lista, String ruta) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
            // 1. Escribimos la cabecera obligatoria
            pw.println("ID,Titulo,Urgencia,Estado");

            // 2. Escribimos los datos
            for (IncidenciaCSV inc : lista) {
                pw.println(inc.id + "," + inc.titulo + "," + inc.urgencia + "," + inc.estado);
            }
            System.out.println("Exportación CSV completada con éxito en: " + ruta);
        } catch (IOException e) {
            System.out.println("Error al exportar: " + e.getMessage());
        }
    }

    // Modulo de importacion con control de errores
    public static List<IncidenciaCSV> importar(String ruta) {
        List<IncidenciaCSV> cargadas = new ArrayList<>();
        System.out.println("\nIniciando importación desde: " + ruta);

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            boolean primeraLinea = true;
            int numLinea = 0;

            while ((linea = br.readLine()) != null) {
                numLinea++;

                // Nos saltamos la cabecera
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                String[] datos = linea.split(",");

                // Control de errores 1: Faltan columnas
                if (datos.length != 4) {
                    System.out.println("Línea " + numLinea + " ignorada: Faltan columnas: " + linea);
                    continue;
                }

                // Control de errores 2: Conversión de tipos
                try {
                    int id = Integer.parseInt(datos[0].trim());
                    String titulo = datos[1].trim();
                    int urgencia = Integer.parseInt(datos[2].trim());
                    String estado = datos[3].trim();

                    cargadas.add(new IncidenciaCSV(id, titulo, urgencia, estado));
                } catch (NumberFormatException e) {
                    System.out.println("Línea " + numLinea + " ignorada: Formato numérico inválido: " + linea);
                }
            }
            System.out
                    .println("Importación finalizada. " + cargadas.size() + " registros válidos cargados en memoria.");
        } catch (IOException e) {
            System.out.println("Error al leer el fichero CSV: " + e.getMessage());
        }
        return cargadas;
    }

    // Ejecución para generar evidencias
    public static void main(String[] args) {
        String rutaLimpio = "exportaciones/incidencias.csv";
        String rutaSucio = "exportaciones/incidencias_defectuosas.csv";

        // 1. Exportamos un fichero limpio para demostrar la exportación
        List<IncidenciaCSV> datosLimpios = Arrays.asList(
                new IncidenciaCSV(1, "Pantalla rota", 3, "ABIERTA"),
                new IncidenciaCSV(2, "Raton no va", 1, "CERRADA"));
        exportar(datosLimpios, rutaLimpio);

        // 2. Simulamos la creación de un fichero realizado por un usuario
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaSucio))) {
            pw.println("ID,Titulo,Urgencia,Estado"); // Línea 1 (Cabecera)
            pw.println("10,Teclado roto,2,ABIERTA"); // Línea 2 (OK)
            pw.println("XX,Fallo servidor,5,CRITICA"); // Línea 3 (Falla: ID no numérico)
            pw.println("12,Falta un campo,1"); // Línea 4 (Falla: Faltan columnas)
            pw.println("13,Virus detectado,cinco,ABIERTA"); // Línea 5 (Falla: Urgencia no numérica)
            pw.println("14,Cable pelado,2,EN_PROGRESO"); // Línea 6 (OK)
        } catch (Exception e) {
        }

        // 3. Importamos el fichero sucio para demostrar que toleramos registros
        // incorrectos
        List<IncidenciaCSV> resultado = importar(rutaSucio);

        System.out.println("\n--- RESULTADO FINAL EN MEMORIA ---");
        for (IncidenciaCSV inc : resultado) {
            System.out.println(inc);
        }
    }
}