package api.batch;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class ExportadorBatch {

    public static void main(String[] args) {
        System.out.println("=== Iniciando proceso batch de exportacion ===");

        // Parámetros externos: ruta de salida
        String rutaSalida = (args.length > 0) ? args[0] : "exportaciones/batch/reporte_incidencias.csv";

        File archivo = new File(rutaSalida);
        archivo.getParentFile().mkdirs();

        int totalLeidos = 0;
        int totalExportados = 0;
        int totalErrores = 0;

        // Simulamos los datos extraídos
        List<String[]> datosExtraidos = Arrays.asList(
                new String[] { "101", "Fallo VPN", "ABIERTO" },
                new String[] { "102", "Ratón roto", "CERRADO" },
                new String[] { "103", "", "EN_PROGRESO" } // Falla a propósito
        );

        System.out.println("[SISTEMA] Leyendo registros nocturnos (Simulado)...");

        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            writer.println("ID,TITULO,ESTADO,FECHA_EXPORTACION");
            String fechaHoy = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            for (String[] fila : datosExtraidos) {
                totalLeidos++;
                String id = fila[0];
                String titulo = fila[1];
                String estado = fila[2];

                // Si el título viene vacío, lo descartamos
                if (titulo == null || titulo.trim().isEmpty()) {
                    System.out.println("[WARN] Registro ID " + id + " inválido (sin título). Descartado.");
                    totalErrores++;
                    continue;
                }

                writer.println(id + "," + titulo + "," + estado + "," + fechaHoy);
                System.out.println("[INFO] Exportado correctamente -> ID " + id + ": " + titulo);
                totalExportados++;
            }

            System.out.println("\n=== RESUMEN DE EJECUCIÓN BATCH ===");
            System.out.println("Total registros leídos: " + totalLeidos);
            System.out.println("Total exportados con éxito: " + totalExportados);
            System.out.println("Total ignorados (errores de validación): " + totalErrores);
            System.out.println("Ruta del archivo generado: " + archivo.getAbsolutePath());
            System.out.println("========================================\n");

        } catch (Exception e) {
            System.err.println("[CRÍTICO] Error fatal en el proceso batch: " + e.getMessage());
        }
    }
}