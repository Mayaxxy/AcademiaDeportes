package vista;

import modelo.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class AdminVista {

    public String getRol() {
        String[] options = {"Admin", "Miembro", "Entrenador"};
        return (String) JOptionPane.showInputDialog(null, "Selecciona tu rol:",
                "Rol del usuario", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }
    public Map<String, Object> getStudentDetails() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del miembro:");
        String correo = JOptionPane.showInputDialog("Ingrese el correo del miembro:");
        String id = JOptionPane.showInputDialog("Ingrese el id del usuario:");
        Categoria categoria = (Categoria) JOptionPane.showInputDialog(null, "Seleccione la categoria:", "Categoria", JOptionPane.QUESTION_MESSAGE, null, Categoria.values(), Categoria.values()[0]);
        Map<String, Object> miembroDetails = new HashMap<>();
        miembroDetails.put("nombre", nombre);
        miembroDetails.put("correo", correo);
        miembroDetails.put("id", id);
        miembroDetails.put("categoria", categoria);
        return miembroDetails;
    }

    public String getUsuarioId() {
        return JOptionPane.showInputDialog("Ingrese su identificacion:");
    }

    public String[] getEntrenadorDetails() {
        String name = JOptionPane.showInputDialog("Ingrese el nombre del entrenador:");
        String id = JOptionPane.showInputDialog("Ingrese el ID del entrenador:");
        String especialidad = JOptionPane.showInputDialog("Ingrese la especialidad del entrenador:");
        return new String[] {name, id, especialidad};
    }

    public int showAdminMiembroMenu() {
        String[] options = {"Crear Miembro", "Editar Miembro", "Remover Miembro", "Ver Miembro", "Volver"};
        return JOptionPane.showOptionDialog(null, "Seleccione una acción:",
                "Admin Miembros Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
    }
    public int showAdminEntrenadorMenu() {
        String[] options = {"Crear Entrenador", "Editar Entrenador", "Remover Entrenador", "Ver Entrenador", "Volver"};
        return JOptionPane.showOptionDialog(null, "Selecciones una acción:",
                "Admin Entrenadores Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
    }
    public int showAdminDeporteMenu() {
        String[] options = {"Crear Deporte", "Editar Deporte", "Remover Deporte", "Ver Deporte", "Volver"};
        return JOptionPane.showOptionDialog(null, "Seleccione una acción:",
                "Admin Deportes Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
    }
    public int showAdminSesionMenu() {
        String[] options = {"Crear Sesion", "Editar Sesion", "Remover Sesion", "Ver Sesion", "Volver"};
        return JOptionPane.showOptionDialog(null, "Seleccione una acción:",
                "Admin Sesiones Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
    }

    public int adminMenu() {
        String[] options = {"Miembros", "Entrenadores", "Sesiones", "Deportes", "Volver"};
        return JOptionPane.showOptionDialog(null, "Selecciona que quieres gestionar:",
                "Admin menu", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public Map<String, Object> getEditStudentDetails() {
        String id = JOptionPane.showInputDialog("Ingrese el id del miembro a editar:");
        String newNombre= JOptionPane.showInputDialog("Ingrese el nuevo nombre del miembro:");
        String correo = JOptionPane.showInputDialog("Ingrese el nuevo correo del miembro:");
        String id2 = JOptionPane.showInputDialog("Ingrese el nuevo id del miembro:");
        Categoria categoria = (Categoria) JOptionPane.showInputDialog(null, "Seleccione la nueva categoria:", "Categoria", JOptionPane.QUESTION_MESSAGE, null, Categoria.values(), Categoria.values()[0]);

        Map<String, Object> miembroDetails = new HashMap<>();
        miembroDetails.put("id", id);
        miembroDetails.put("nombreNuevo", newNombre);
        miembroDetails.put("correo", correo);
        miembroDetails.put("id2", id2);
        miembroDetails.put("categoria", categoria);
        return miembroDetails;
    }
    public void showMiembroDetails(Miembro miembro) {
        // Crea un panel para mostrar los detalles del miembro
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2)); // 2 columnas, tantas filas como necesite

        // Agrega etiquetas y campos de texto para mostrar la información
        panel.add(new JLabel("ID:"));
        panel.add(new JLabel(miembro.getId()));

        panel.add(new JLabel("Nombre:"));
        panel.add(new JLabel(miembro.getNombre()));

        panel.add(new JLabel("Correo:"));
        panel.add(new JLabel(miembro.getEmail()));

        panel.add(new JLabel("Categoria:"));
        panel.add(new JLabel(String.valueOf(miembro.getCategoria())));

        panel.add(new JLabel("Sesiones:"));
        panel.add(new JLabel(String.valueOf(miembro.getSesiones())));

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
        JDialog dialog = new JDialog((JFrame) null, "Detalles del miembro", true); // 'true' para que sea modal
        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null); // Centra la ventana
        dialog.setVisible(true);
    }
    public void showDeporteDetails(Deporte deporte) {
        // Crea un panel para mostrar los detalles del miembro
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2)); // 2 columnas, tantas filas como necesite

        panel.add(new JLabel("Nombre:"));
        panel.add(new JLabel(deporte.getNombre()));

        panel.add(new JLabel("Descripcion:"));
        panel.add(new JLabel(deporte.getDescripcion()));

        panel.add(new JLabel("Entrenadores:"));
        panel.add(new JLabel(String.valueOf(deporte.getEntrenadores())));

        panel.add(new JLabel("Dificultad:"));
        panel.add(new JLabel(String.valueOf(deporte.getNivelDificultad())));

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
        JDialog dialog = new JDialog((JFrame) null, "Detalles del deporte", true); // 'true' para que sea modal
        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null); // Centra la ventana
        dialog.setVisible(true);
    }

    public Map<String, Object> getEditDeporteDetails() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del deporte a editar:");
        String newNombre= JOptionPane.showInputDialog("Ingrese el nuevo nombre del deporte:");
        String descripcion = JOptionPane.showInputDialog("Ingrese la nueva descripción del deporte:");
        Dificultad dificultad = (Dificultad) JOptionPane.showInputDialog(null, "Seleccione la nueva dificultad:", "Dificultad", JOptionPane.QUESTION_MESSAGE, null, Dificultad.values(), Dificultad.values()[0]);

        Map<String, Object> deporteDetails = new HashMap<>();
        deporteDetails.put("nombre", nombre);
        deporteDetails.put("nombreNuevo", newNombre);
        deporteDetails.put("descripcion", descripcion);
        deporteDetails.put("dificultad", dificultad);

        return deporteDetails;
    }

    public Map<String, Object> getDeporteDetails() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del deporte:");
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripción del deporte:");
        Dificultad dificultad = (Dificultad) JOptionPane.showInputDialog(null, "Seleccione la dificultad:", "Dificultad", JOptionPane.QUESTION_MESSAGE, null, Dificultad.values(), Dificultad.values()[0]);

        Map<String, Object> deporteDetails = new HashMap<>();
        deporteDetails.put("nombre", nombre);
        deporteDetails.put("descripcion", descripcion);
        deporteDetails.put("dificultad", dificultad);

        return deporteDetails;
    }
    public String[] getEditEntrenadorDetails() {
        String id = JOptionPane.showInputDialog("Ingrese el ID del entrenador:");
        String newName = JOptionPane.showInputDialog("Ingrese el nuevo nombre del entrenador:");
        String newEspecialidad = JOptionPane.showInputDialog("ingrese la nueva especialidad:");
        return new String[]{newName, id, newEspecialidad};
    }
    public void showEntrenadorDetails(Entrenador entrenador) {
        // Crea un panel para mostrar los detalles del miembro
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2)); // 2 columnas, tantas filas como necesite

        panel.add(new JLabel("ID:"));
        panel.add(new JLabel(entrenador.getId()));

        panel.add(new JLabel("Nombre:"));
        panel.add(new JLabel(entrenador.getNombre()));

        panel.add(new JLabel("Especialidad:"));
        panel.add(new JLabel(entrenador.getEspecialidad()));

        panel.add(new JLabel("Sesiones:"));
        panel.add(new JLabel(String.valueOf(entrenador.getSesiones())));

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
        JDialog dialog = new JDialog((JFrame) null, "Detalles del entrenador", true); // 'true' para que sea modal
        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null); // Centra la ventana
        dialog.setVisible(true);
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
    public Map<String, Object> getSesionDetails(Deporte[] deportesArray, Entrenador[] entrenadoresArray) {
        // Selección del deporte
        Deporte deporte = (Deporte) JOptionPane.showInputDialog(
                null,
                "Seleccione el deporte:",
                "Deporte",
                JOptionPane.QUESTION_MESSAGE,
                null,
                deportesArray,
                deportesArray[0]
        );

        // Selección del entrenador
        Entrenador entrenador = (Entrenador) JOptionPane.showInputDialog(
                null,
                "Seleccione el entrenador:",
                "Entrenador",
                JOptionPane.QUESTION_MESSAGE,
                null,
                entrenadoresArray,
                entrenadoresArray[0]
        );

        // Entrada de la duración
        int duracion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la duración (en minutos):"));

        // Entrada de la fecha
        String fechaString = JOptionPane.showInputDialog("Ingrese la fecha (formato YYYY-MM-DD):");
        LocalDate fecha = LocalDate.parse(fechaString, DateTimeFormatter.ISO_LOCAL_DATE);

        // Almacenar detalles en un Map
        Map<String, Object> sesionDetails = new HashMap<>();
        sesionDetails.put("deporte", deporte);
        sesionDetails.put("entrenador", entrenador);
        sesionDetails.put("duracion", duracion);
        sesionDetails.put("fecha", fecha);

        return sesionDetails;
    }
    public Map<String, Object> getEditSesionDetails(Deporte[] deportesArray, Entrenador[] entrenadoresArray) {
        int duracion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la duración (en minutos):"));
        int newDuracion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva duración (en minutos):"));
        String fechaString = JOptionPane.showInputDialog("Ingrese la nueva fecha (formato YYYY-MM-DD):");
        LocalDate newFecha = LocalDate.parse(fechaString, DateTimeFormatter.ISO_LOCAL_DATE);
        Deporte newDeporte = (Deporte) JOptionPane.showInputDialog(
                null,
                "Seleccione el nuevo deporte:",
                "Deporte",
                JOptionPane.QUESTION_MESSAGE,
                null,
                deportesArray,
                deportesArray[0]
        );
        Entrenador newEntrenador = (Entrenador) JOptionPane.showInputDialog(
                null,
                "Seleccione el nuevo entrenador:",
                "Entrenador",
                JOptionPane.QUESTION_MESSAGE,
                null,
                entrenadoresArray,
                entrenadoresArray[0]
        );
        Map<String, Object> sesionDetails = new HashMap<>();
        sesionDetails.put("duracion", duracion);
        sesionDetails.put("newDuracion", newDuracion);
        sesionDetails.put("newFecha", newFecha);
        sesionDetails.put("newDeporte", newDeporte);
        sesionDetails.put("newEntrenador", newEntrenador);

        return sesionDetails;
    }


    public String getIdToDelete() {
        return JOptionPane.showInputDialog("Ingrese el ID a eliminar");
    }

    public Map<String, Object> deleteSesion(Sesion[] sesionArray) {
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
    public String getNameToDelete() {
        return JOptionPane.showInputDialog("Ingrese el nombre a eliminar:");
    }
    public String getIdToView() {
        return JOptionPane.showInputDialog("Ingrese el ID para ver:");
    }

    public String getNombreToView() {
        return JOptionPane.showInputDialog("Ingrese el nombre para ver:");
    }


}
