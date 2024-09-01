package vista;

import modelo.Sesion;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MiembroVista {

    public int showMiembroMenu() {
        String[] options = {"Añadir sesiones","Eliminar sesiones","Ver sesiones", "Volver"};
        return JOptionPane.showOptionDialog(null, "Selecciona que quieres gestionar:",
                "Miembro menu", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
    }
    public Map<String, Object> verSesion(Sesion[] sesionArray) {
        Sesion sesion = (Sesion) JOptionPane.showInputDialog(
                null,
                "Seleccione la sesion a ver:",
                "Sesion",
                JOptionPane.QUESTION_MESSAGE,
                null,
                sesionArray,
                sesionArray[0]
        );
        Map<String, Object> sesionDetails = new HashMap<>();
        sesionDetails.put("sesion", sesion);
        return sesionDetails;
    }
    public void showSesionDetails(Sesion sesion) {
        // Crea un panel para mostrar los detalles del miembro
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2)); // 2 columnas, tantas filas como necesite

        panel.add(new JLabel("Duración:"));
        panel.add(new JLabel(String.valueOf(sesion.getDuracion())));

        panel.add(new JLabel("Fecha:"));
        panel.add(new JLabel(String.valueOf(sesion.getFecha())));

        panel.add(new JLabel("Estado:"));
        panel.add(new JLabel(sesion.getEstado()));

        panel.add(new JLabel("Deporte:"));
        panel.add(new JLabel(String.valueOf(sesion.getDeporte())));

        panel.add(new JLabel("Entrenador:"));
        panel.add(new JLabel(String.valueOf(sesion.getEntrenador())));

        // Crea un botón para cerrar la ventana
        JButton closeButton = new JButton("Cerrar");
        closeButton.addActionListener(e -> {
            // Cierra la ventana cuando se presiona el botón
            ((JDialog) SwingUtilities.getWindowAncestor(panel)).dispose();
        });

        // Agrega el botón al panel
        panel.add(new JLabel()); // Espacio vacío para alinear el botón
        panel.add(closeButton);

        // Crea y muestra el JDialog en lugar de un JFrame
        JDialog dialog = new JDialog((JFrame) null, "Detalles de la sesion", true); // 'true' para que sea modal
        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null); // Centra la ventana
        dialog.setVisible(true);
    }
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
    public Map<String, Object> eliminarSesion(Sesion[] sesionArray) {
        Sesion sesion = (Sesion) JOptionPane.showInputDialog(
                null,
                "Seleccione la sesion a eliminar:",
                "Sesion",
                JOptionPane.QUESTION_MESSAGE,
                null,
                sesionArray,
                sesionArray[0]
        );
        Map<String, Object> sesionDetails = new HashMap<>();
        sesionDetails.put("sesion", sesion);
        return sesionDetails;
    }
}
