package controlador;

import modelo.AcademiaDeportes;
import modelo.Admin;
import vista.AdminVista;

public class App {
    public static void main(String[] args) {
        Admin admin = Admin.getInstance("Joaquin", "300");
        AcademiaDeportes academia = new AcademiaDeportes(admin);
        AdminVista view = new AdminVista();
        Controlador controller = new Controlador(view, admin,academia);

        controller.startApp();
    }
}
