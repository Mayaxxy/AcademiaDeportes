package vista;

import modelo.Dificultad;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class AdminVista {

    public String getRol() {
        String[] options = {"Admin", "Miembro", "Entrenador"};
        return (String) JOptionPane.showInputDialog(null, "Selecciona tu rol:",
                "Rol del usuario", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }
    public String[] getStudentDetails() {
        String name = JOptionPane.showInputDialog("Ingrese el nombre del miembro:");
        String id = JOptionPane.showInputDialog("Ingrese el correo del miembro:");
        String email = JOptionPane.showInputDialog("Ingrese la identificacion del miembro:");
        return new String[] {name, id, email};
    }

    public String getUsuarioId() {
        return JOptionPane.showInputDialog("Ingrese su identificacion:");
    }

    public String[] getEntrenadorDetails() {
        String name = JOptionPane.showInputDialog("Enter coach name:");
        String id = JOptionPane.showInputDialog("Enter coach ID:");
        String especialidad = JOptionPane.showInputDialog("Enter coach especialidad:");
        return new String[] {name, id, especialidad};
    }

    public int showAdminMiembroMenu() {
        String[] options = {"Crear Miembro", "Editar Miembro", "Remover Miembro"};
        return JOptionPane.showOptionDialog(null, "Select an action:",
                "Admin Miembros Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
    }
    public int showAdminEntrenadorMenu() {
        String[] options = {"Crear Entrenador", "Editar Entrenador", "Remover Entrenador"};
        return JOptionPane.showOptionDialog(null, "Select an action:",
                "Admin Entrenadores Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
    }
    public int showAdminDeporteMenu() {
        String[] options = {"Crear Deporte", "Editar Deporte", "Remover Deporte", "Buscar Deporte"};
        return JOptionPane.showOptionDialog(null, "Select an action:",
                "Admin Entrenadores Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
    }

    public int adminMenu() {
        String[] options = {"Miembros", "Entrenadores", "Sesiones", "Deportes"};
        return JOptionPane.showOptionDialog(null, "Selecciona que quieres gestionar:",
                "Admin menu", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public String[] getEditStudentDetails() {
        String id = JOptionPane.showInputDialog("Enter the ID of the student to edit:");
        String newName = JOptionPane.showInputDialog("Enter new name:");
        String newEmail = JOptionPane.showInputDialog("Enter new email:");
        return new String[]{id, newName, newEmail};
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
        String id = JOptionPane.showInputDialog("Enter the ID of the entrenador to edit:");
        String newName = JOptionPane.showInputDialog("Enter new name:");
        String newEspecialidad = JOptionPane.showInputDialog("Enter new especialidad:");
        return new String[]{newName, id, newEspecialidad};
    }
    public String getIdToDelete() {
        return JOptionPane.showInputDialog("Enter the ID to delete:");
    }

}
