package escritorio;

import javax.swing.*;

public class ControladorAdmin {
    private VistaAdmin vista;

    public ControladorAdmin(VistaAdmin vista) {
        this.vista = vista;
        cargarDatosSimulados();
        inicializarEventos();
    }

    private void cargarDatosSimulados() {
        vista.getModeloTabla().addRow(new Object[] { 101, "Fallo conexión VPN", "Abierto", "Alta" });
        vista.getModeloTabla().addRow(new Object[] { 102, "Impresora sin tóner", "Abierto", "Baja" });
        vista.getModeloTabla().addRow(new Object[] { 103, "Caída del servidor", "En Progreso", "Crítica" });
    }

    private void inicializarEventos() {
        vista.getBtnEnProgreso().addActionListener(e -> cambiarEstado("En Progreso"));
        vista.getBtnCerrar().addActionListener(e -> cambiarEstado("Cerrado"));
    }

    private void cambiarEstado(String nuevoEstado) {
        int filaSeleccionada = vista.getTablaIncidencias().getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(vista,
                    "Por favor, selecciona una incidencia de la tabla primero.",
                    "Aviso de Validación",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        vista.getModeloTabla().setValueAt(nuevoEstado, filaSeleccionada, 2);
        JOptionPane.showMessageDialog(vista,
                "Estado actualizado a: " + nuevoEstado,
                "Operación Exitosa",
                JOptionPane.INFORMATION_MESSAGE);
    }
}