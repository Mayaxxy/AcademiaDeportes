package controlador;

import modelo.*;
import vista.AdminVista;
import vista.EntrenadorVista;
import vista.MiembroVista;
import java.time.LocalDate;
import java.util.Map;

/**
 * Clase Controlador que gestiona la lógica de la aplicación.
 * La clase Controlador gestiona la lógica de la aplicación de gestión deportiva. Coordina las interacciones
 * entre las vistas (AdminVista, EntrenadorVista, MiembroVista) y el modelo.
 * Dependiendo del rol del usuario (Admin, Miembro, Entrenador), dirige el flujo a las respectivas funcionalidades,
 * como gestión de miembros, entrenadores, sesiones y deportes. Además, maneja la autenticación del usuario y muestra
 * mensajes de error o éxito según las acciones realizadas.
 */
public class Controlador {
    private final AdminVista adminVista;
    private final EntrenadorVista entrenadorVista;
    private MiembroVista miembroVista;
    private Admin admin;
    private final AcademiaDeportes academia;


    public Controlador(AdminVista adminVista, Admin admin, AcademiaDeportes academia, EntrenadorVista entrenadorVista, MiembroVista miembroVista) {
        this.adminVista = adminVista;
        this.admin = admin;
        this.academia = academia;
        this.entrenadorVista = entrenadorVista;
        this.miembroVista = miembroVista;
    }

    /**
     * Inicia la aplicación.
     */
    public void startApp() {
        String role = adminVista.getRol();
        String id = adminVista.getUsuarioId();
        Object user = academia.authenticateUser(id);

        if (user == null) {
            adminVista.showMessage("Autenticación fallida, id no encontrado");
            startApp();
            return;
        }

        if (role.equals("Admin") && user instanceof Admin) {
            System.out.println("Rol admin detectado...");
            handleAdmin();
        } else if (role.equals("Miembro") && user instanceof Miembro miembro) {
            System.out.println("Miembro " + miembro.getNombre() + " detectado...");
            opcionesMiembro(miembro);
        } else if (role.equals("Entrenador") && user instanceof Entrenador entrenador) {
            System.out.println("Entrenador " + entrenador.getNombre() + " detectado...");
            handleEntrenador(entrenador);
        } else {
            adminVista.showMessage("Rol incorrecto, no se puede realizar la operacion");
            startApp();
        }
    }

    private void opcionesMiembro(Miembro miembro) {
        int choice = miembroVista.showMiembroMenu();

        switch (choice) {
            case 0:
                Sesion[] sesionArray = admin.getSesiones().toArray(new Sesion[0]);
                Map<String, Object> detalles = entrenadorVista.verSesion(sesionArray);
                Sesion sesionSeleccionada = (Sesion) detalles.get("sesion");
                String resultado = miembro.inscribirSesion(sesionSeleccionada);

                if (resultado.equals("fechapasada")) {
                    entrenadorVista.showMessage("La fecha de la sesión ya ha pasado, inscribe una con fecha futura");
                } else if (resultado.equals("edadinsuficiente")) {
                    entrenadorVista.showMessage("No tienes la categoría para añadir sesiones de este deporte");
                } else if (resultado.equals("bienregistrada")) {
                    entrenadorVista.showMessage("Sesión registrada");
                }

                opcionesMiembro(miembro);
                break;

            case 1:
                sesionArray = miembro.getSesiones().toArray(new Sesion[0]);
                detalles = miembroVista.eliminarSesion(sesionArray);
                sesionSeleccionada = (Sesion) detalles.get("sesion");
                miembro.eliminarSesion(sesionSeleccionada);
                miembroVista.showMessage("Sesión eliminada correctamente");
                opcionesMiembro(miembro);
                break;

            case 2:
                sesionArray = miembro.getSesiones().toArray(new Sesion[0]);
                detalles = miembroVista.verSesion(sesionArray);
                sesionSeleccionada = (Sesion) detalles.get("sesion");

                if (sesionSeleccionada != null) {
                    miembroVista.showSesionDetails(sesionSeleccionada);
                } else {
                    miembroVista.showMessage("No se encontró sesión");
                }

                opcionesMiembro(miembro);
                break;

            case 3:
                startApp();
                break;
        }
    }

    private void handleAdmin() {
        int choice = adminVista.adminMenu();

        switch (choice) {
            case 0 -> handleMiembros();
            case 1 -> handleEntrenadores();
            case 2 -> handleSesiones();
            case 3 -> handleDeportes();
            case 4 -> startApp();
            default -> startApp();

        }
    }
    private void handleEntrenador(Entrenador entrenador) {
        int choice = entrenadorVista.showEntrenadorMenu();
        if (choice == 0) {
            Sesion[] sesionArray = entrenador.getSesiones().toArray(new Sesion[0]);
            Map<String, Object> detalles = entrenadorVista.verSesion(sesionArray);
            Sesion sesionSeleccionada = (Sesion) detalles.get("sesion");
            if (sesionSeleccionada != null) {
                entrenadorVista.showSesionDetails(sesionSeleccionada);
            } else {
                entrenadorVista.showMessage("No se encontró sesión");
            }
            handleEntrenador(entrenador);
        } else if (choice == 1) {
            startApp();
        }
    }

    private void handleDeportes(){
        int choice = adminVista.showAdminDeporteMenu();
        if (choice == 0) {
            Map<String,Object> deporteDetails = adminVista.getDeporteDetails();
            Deporte deporte = new Deporte(
                    (String) deporteDetails.get("nombre"),
                    (String) deporteDetails.get("descripcion"),
                    (Dificultad) deporteDetails.get("dificultad"));
                admin.addDeporte(deporte);
                adminVista.showMessage("Deporte " + deporte.getNombre() + " creado correctamente!");

            handleDeportes();
        } else if (choice == 1) {
            Map<String,Object> deporteDetails = adminVista.getEditDeporteDetails();
            Deporte deporte = admin.editarDeporte(
                    (String) deporteDetails.get("nombre"),
                    (String) deporteDetails.get("nombreNuevo"),
                    (String) deporteDetails.get("descripcion"),
                    (Dificultad) deporteDetails.get("dificultad"));
            if (deporte != null) {
                adminVista.showMessage("Deporte " + deporte.getNombre() + " editado correctamente!");
            } else {
                adminVista.showMessage("Error: Deporte no encontrado.");
            }
            handleDeportes();
        } else if (choice == 2) {
            String nombre = adminVista.getNameToDelete();
            if (admin.removerDeporte(nombre)) {
                adminVista.showMessage("Deporte eliminado correctamente");
            } else {
                adminVista.showMessage("No se encontró el deporte " + nombre);
            }
            handleDeportes();
        } else if (choice == 3) {
            String nombre = adminVista.getNombreToView();
            Deporte deporte = admin.encontrarDeporte(nombre);
            if (deporte != null) {
                adminVista.showDeporteDetails(deporte);
            } else {
                adminVista.showMessage("No se encontró el deporte " + nombre);
            }
            handleDeportes();
        } else if (choice == 4) {
            handleAdmin();
        } else {
            adminVista.showMessage("Opcion seleccionada no valida");
        }}
    private void handleMiembros() {
        int choice = adminVista.showAdminMiembroMenu();
        if (choice == 0) {
            Map<String,Object> miembroDetails = adminVista.getStudentDetails();
            Miembro miembro = new Miembro(
                    (String) miembroDetails.get("nombre"),
                    (String) miembroDetails.get("correo"),
                    (String) miembroDetails.get("id"),
                    (Categoria) miembroDetails.get("categoria"));
            admin.addMiembro(miembro);
                adminVista.showMessage("Miembro " + miembro.getNombre() + " creado exitosamente!");
            handleMiembros();
        } else if (choice == 1) {
            Map<String,Object> miembroDetails = adminVista.getEditStudentDetails();
            Miembro miembro = admin.editarMiembro(
                    (String) miembroDetails.get("id"),
                    (String) miembroDetails.get("nombreNuevo"),
                    (String) miembroDetails.get("correoNuevo"),
                    (String) miembroDetails.get("idNuevo"),
                    (Categoria) miembroDetails.get("categoria"));
            if (miembro != null) {
                adminVista.showMessage("Miembro " + miembro.getNombre() + " editado exitosamente!");
            } else {
                adminVista.showMessage("Error: Miembro no encontrado.");
            }
            handleMiembros();
        } else if (choice == 2) {
            String id = adminVista.getIdToDelete();
            if (admin.removerMiembro(id)) {
                adminVista.showMessage("Miembro eliminado correctamente");
            } else {
                adminVista.showMessage("No se encontró miembro con ID " + id);
            }
            handleMiembros();
        } else if (choice == 3) {
            String id = adminVista.getIdToView();
            Miembro miembro = admin.encontrarMiembro(id);
            if (miembro != null) {
                adminVista.showMiembroDetails(miembro);
            } else {
                adminVista.showMessage("No se encontró miembro con ID " + id);
            }
            handleMiembros();
        } else if (choice == 4) {
            handleAdmin();
        }
        else {
            adminVista.showMessage("No valid option selected.");
        }
    }

    private void handleEntrenadores() {
        int choice = adminVista.showAdminEntrenadorMenu();

        if (choice == 0) {
            String[] entrenadorDetails = adminVista.getEntrenadorDetails();
            Entrenador entrenador = new Entrenador(entrenadorDetails[0], entrenadorDetails[1], entrenadorDetails[2]);
            if (entrenador.getId() != null) {
                admin.addEntrenador(entrenador);
                adminVista.showMessage("Entrenador " + entrenador.getNombre() + " creado exitosamente!");
                admin.printEntrenadores();
            } else {
                adminVista.showMessage("Error: Entrenador ya existe.");
            }
            handleEntrenadores();
        } else if (choice == 1) {
            String[] entrenadorDetails = adminVista.getEditEntrenadorDetails();
            Entrenador entrenador = admin.editarEntrenador(entrenadorDetails[0], entrenadorDetails[1], entrenadorDetails[2]);
            if (entrenador != null) {
                adminVista.showMessage("Entrenador " + entrenador.getNombre() + " editado exitosamente!");
            } else {
                adminVista.showMessage("Error: Entrenador no encontrado.");
            }
            handleEntrenadores();
        } else if (choice == 2) {
            String id = adminVista.getIdToDelete();
            if (admin.removerEntrenador(id)) {
                adminVista.showMessage("Entrenador eliminado correctamente");
            } else {
                adminVista.showMessage("No se encontró entrenador con ID " + id);
            }
            handleEntrenadores();
        }
        else if (choice == 3) {
            String id = adminVista.getIdToView();
            Entrenador entrenador = admin.encontrarEntrenador(id);
            if (entrenador != null) {
                adminVista.showEntrenadorDetails(entrenador);
            } else {
                adminVista.showMessage("No se encontró entrenador con ID " + id);
            }
            handleEntrenadores();
        } else if (choice == 4) {
            handleAdmin();
        }
        else {
            adminVista.showMessage("No valid option selected.");
        }
    }

    private void handleSesiones () {
        int choice = adminVista.showAdminSesionMenu();

        if (choice == 0) {
            Deporte[] deportesArray = admin.getDeportes().toArray(new Deporte[0]); // Obtener deportes como array
            Entrenador[] entrenadoresArray = admin.getEntrenadores().toArray(new Entrenador[0]); // Obtener entrenadores como array

            // Pasar los arrays a la vista
            Map<String, Object> detalles = adminVista.getSesionDetails(deportesArray, entrenadoresArray);

            // Verificación de la especialidad del entrenador en el controlador
            Deporte deporteSeleccionado = (Deporte) detalles.get("deporte");
            Entrenador entrenadorSeleccionado = (Entrenador) detalles.get("entrenador");

            if (entrenadorSeleccionado.getEspecialidad().equalsIgnoreCase(deporteSeleccionado.getNombre())) {
                Sesion sesion = new Sesion(
                        (int) detalles.get("duracion"),
                        (LocalDate) detalles.get("fecha"),
                        deporteSeleccionado,
                        entrenadorSeleccionado);
                var sesiones = entrenadorSeleccionado.getSesiones();
                admin.addSesion(sesion);
                admin.addSesionEntrenador(sesion, sesiones);
                entrenadorSeleccionado.printSesiones();
                sesion.establecerEstado();
                adminVista.showMessage("Sesion creada exitosamente!");
            } else {
                // Mostrar error si la especialidad del entrenador no coincide con el deporte
                adminVista.showMessage("La especialidad del entrenador no coincide con el deporte seleccionado.");
            }
            handleSesiones();
        } else if (choice == 1) {
            Deporte[] deportesArray = admin.getDeportes().toArray(new Deporte[0]); // Obtener deportes como array
            Entrenador[] entrenadoresArray = admin.getEntrenadores().toArray(new Entrenador[0]); // Obtener entrenadores como array

            Map<String, Object> sesionDetails = adminVista.getEditSesionDetails(deportesArray, entrenadoresArray);
            Deporte newDeporteSeleccionado = (Deporte) sesionDetails.get("newDeporte");
            Entrenador newEntrenadorSeleccionado = (Entrenador) sesionDetails.get("newEntrenador");
            Sesion sesion = admin.editarSesion(
                    (int) sesionDetails.get("duracion"),
                    (int) sesionDetails.get("newDuracion"),
                    (LocalDate) sesionDetails.get("newFecha"),
                    newDeporteSeleccionado,
                    newEntrenadorSeleccionado);
            if (sesion != null) {
                var sesiones = newEntrenadorSeleccionado.getSesiones();
                admin.addSesionEntrenador(sesion, sesiones);
                newEntrenadorSeleccionado.printSesiones();
                sesion.establecerEstado();
                adminVista.showMessage("Sesion editada satisfactoriamente");
            } else {
                adminVista.showMessage("Error: Sesion no encontrada.");
            }
            handleSesiones();
        } else if (choice == 2) {
            Sesion[] sesionArray = admin.getSesiones().toArray(new Sesion[0]);
            Map<String, Object> detalles = adminVista.deleteSesion(sesionArray);
            Sesion sesionSeleccionado = (Sesion) detalles.get("sesion");
            if (admin.removerSesion(sesionSeleccionado)) {
                adminVista.showMessage("Sesion eliminada correctamente");
            } else {
                adminVista.showMessage("No se encontró sesion ");
            }
            handleSesiones();
        } else if (choice == 3) {
            Sesion[] sesionArray = admin.getSesiones().toArray(new Sesion[0]);
            Map<String, Object> detalles = adminVista.verSesion(sesionArray);
            Sesion sesionSeleccionada = (Sesion) detalles.get("sesion");
            if (sesionSeleccionada != null) {
                    adminVista.showSesionDetails(sesionSeleccionada);
            } else {
                adminVista.showMessage("No se encontró sesión");
            }
            handleSesiones();
        } else if (choice == 4) {
            handleAdmin();
        } else {
            adminVista.showMessage("Opcion seleccionada no valida");
            handleAdmin();
            }
        }
}





