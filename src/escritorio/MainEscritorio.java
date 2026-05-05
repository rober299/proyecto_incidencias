package escritorio;

import javax.swing.SwingUtilities;

public class MainEscritorio {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VistaAdmin vista = new VistaAdmin();
            new ControladorAdmin(vista);
            vista.setVisible(true);
        });
    }
}