package escritorio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaAdmin extends JFrame {
    private JTable tablaIncidencias;
    private DefaultTableModel modeloTabla;
    private JButton btnEnProgreso;
    private JButton btnCerrar;

    public VistaAdmin() {
        setTitle("Panel Interno de Administración IT");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Título superior
        JLabel lblTitulo = new JLabel("Gestión Rápida de Incidencias", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo, BorderLayout.NORTH);

        // Tabla central
        String[] columnas = { "ID", "Título", "Estado", "Prioridad" };
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaIncidencias = new JTable(modeloTabla);
        tablaIncidencias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tablaIncidencias), BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel();
        btnEnProgreso = new JButton("Marcar 'En Progreso'");
        btnCerrar = new JButton("Cerrar Incidencia");
        panelBotones.add(btnEnProgreso);
        panelBotones.add(btnCerrar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public JTable getTablaIncidencias() {
        return tablaIncidencias;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JButton getBtnEnProgreso() {
        return btnEnProgreso;
    }

    public JButton getBtnCerrar() {
        return btnCerrar;
    }
}