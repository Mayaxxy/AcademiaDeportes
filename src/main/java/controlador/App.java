package controlador;

import modelo.AcademiaDeportes;
import modelo.Admin;
import vista.AdminVista;
import vista.EntrenadorVista;
import vista.MiembroVista;

public class App {
    private Admin admin;
    private AcademiaDeportes academia;
    private AdminVista adminVista;
    private EntrenadorVista entrenadorVista;
    private MiembroVista miembroVista;
    private Controlador controller;

    public App() {
        admin = Admin.getInstance("Joaquin", "300");
        academia = new AcademiaDeportes(admin);
        adminVista = new AdminVista();
        entrenadorVista = new EntrenadorVista();
        miembroVista = new MiembroVista();
        controller = new Controlador(adminVista, admin, academia, entrenadorVista, miembroVista);
        startApp();
    }

    /**
     * Inicia la aplicación llamando al método `startApp().
     */
    public void startApp() {
        controller.startApp(); // o controller.start() dependiendo de su implementación
    }

    /**
     * Crea una nueva instancia de la clase `App`, lo que desencadena la inicialización
     * de todos los componentes y el inicio de la aplicación.
     */
    public static void main(String[] args) {
        new App();
    }
}