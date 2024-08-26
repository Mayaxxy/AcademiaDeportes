package controlador;
import modelo.*;
import vista.AdminVista;

import java.util.Map;

public class Controlador {
    private AdminVista view;
    private Admin admin;
    private AcademiaDeportes academia;

    public Controlador(AdminVista view, Admin admin, AcademiaDeportes academia) {
        this.view = view;
        this.admin = admin;
        this.academia = academia;
    }

    public void startApp() {
        String role = view.getRol();
        String id = view.getUsuarioId();
        Object user = academia.authenticateUser(id);

        if (user == null) {
            view.showMessage("Authentication failed: ID not found.");
            return;
        }

        if (role.equals("Admin") && user instanceof Admin)
        {
            System.out.println("Admin role detected, showing menu...");
            handleAdmin();
        } else if (role.equals("Student") && user instanceof Miembro) {
            handleStudent();
        } else if (role.equals("Coach") && user instanceof Entrenador) {
            handleCoach();
        } else {
            view.showMessage("Role mismatch or invalid selection.");
        }
        switch (role) {
            case "Admin" -> handleAdmin();
            case "Miembro" -> handleStudent();
            case "Entrenador" -> handleCoach();
            default -> view.showMessage("Rol seleccionado invalido.");
        }
    }

    private void handleAdmin() {
        int choice = view.adminMenu();

        switch (choice) {
            case 0 -> handleMiembros();
            case 1 -> handleEntrenadores();
            case 2 -> handleCoach();
            case 3 -> handleDeportes();
            default -> view.showMessage("Rol seleccionado invalido.");
        }
    }
    private void handleDeportes(){
        int choice = view.showAdminDeporteMenu();
        if (choice == 0) {
            Map<String,Object> deporteDetails = view.getDeporteDetails();
            Deporte deporte = admin.addDeporte(
                    (String) deporteDetails.get("nombre"),
                    (String) deporteDetails.get("descripcion"),
                    (Dificultad) deporteDetails.get("dificultad"));
            if (deporte != null) {
                view.showMessage("Deporte " + deporte.getNombre() + " created successfully!");
            } else {
                view.showMessage("Error: El deporte ya existe.");
            }
        } else if (choice == 1) {
            Map<String,Object> deporteDetails = view.getEditDeporteDetails();
            Deporte deporte = admin.editarDeporte(
                    (String) deporteDetails.get("nombre"),
                    (String) deporteDetails.get("nombreNuevo"),
                    (String) deporteDetails.get("descripcion"),
                    (Dificultad) deporteDetails.get("dificultad"));
            if (deporte != null) {
                view.showMessage("Deporte " + deporte.getNombre() + " edited successfully!");
            } else {
                view.showMessage("Error: Deporte no encontrado.");
            }
        }
    }
    private void handleMiembros() {
        int choice = view.showAdminMiembroMenu();

        if (choice == 0) {
            String[] studentDetails = view.getStudentDetails();
            Miembro miembro = admin.addMiembro(studentDetails[0], studentDetails[1], studentDetails[2]);
            if (miembro != null) {
                view.showMessage("Miembro " + miembro.getNombre() + " created successfully!");
                admin.printMiembros();
            } else {
                view.showMessage("Error: Miembro ya existe.");
            }
        } else if (choice == 1) {
            String[] studentDetails = view.getEditStudentDetails();
            Miembro miembro = admin.editarMiembro(studentDetails[0], studentDetails[1], studentDetails[2]);
            if (miembro != null) {
                view.showMessage("Miembro " + miembro.getNombre() + " edited successfully!");
            } else {
                view.showMessage("Error: Miembro no encontrado.");
            }
        } else if (choice == 2) {
            String id = view.getIdToDelete();
            if (admin.removerMiembro(id)) {
                view.showMessage("Miembro eliminado correctamente");
            } else {
                view.showMessage("No se encontró miembro con ID " + id);
            }
        }
        else {
            view.showMessage("No valid option selected.");
        }
    }
    private void handleEntrenadores() {
        int choice = view.showAdminEntrenadorMenu();

        if (choice == 0) {
            String[] entrenadorDetails = view.getEntrenadorDetails();
            Entrenador entrenador = admin.addEntrenador(entrenadorDetails[0], entrenadorDetails[1], entrenadorDetails[2]);
            if (entrenador != null) {
                view.showMessage("Entrenador " + entrenador.getNombre() + " created successfully!");
                admin.printEntrenadores();
            } else {
                view.showMessage("Error: Entrenador ya existe.");
            }
        } else if (choice == 1) {
            String[] entrenadorDetails = view.getEditEntrenadorDetails();
            Entrenador entrenador = admin.editarEntrenador(entrenadorDetails[0], entrenadorDetails[1], entrenadorDetails[2]);
            if (entrenador != null) {
                view.showMessage("Entrenador " + entrenador.getNombre() + " edited successfully!");
            } else {
                view.showMessage("Error: Entrenador no encontrado.");
            }
        } else if (choice == 2) {
            String id = view.getIdToDelete();
            if (admin.removerEntrenador(id)) {
                view.showMessage("Entrenador eliminado correctamente");
            } else {
                view.showMessage("No se encontró entrenador con ID " + id);
            }
        }
        else {
            view.showMessage("No valid option selected.");
        }
    }

    private void handleStudent() {
        view.showMessage("Welcome, Student! You can view your sessions.");
        // Lógica adicional para estudiantes aquí
    }

    private void handleCoach() {
        view.showMessage("Welcome, Coach! You can manage your sessions.");
        // Lógica adicional para entrenadores aquí
    }
}



