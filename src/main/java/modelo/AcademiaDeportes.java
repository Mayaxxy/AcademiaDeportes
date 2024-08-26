package modelo;

import java.util.ArrayList;
import java.util.List;

public class AcademiaDeportes {
    private Admin admin;
    public AcademiaDeportes(Admin admin) {
        this.admin = admin;

    }

    public Object authenticateUser(String id) {
        if (admin.getId().equals(id)) {
            return admin;
        }
        for (Miembro miembro : admin.miembros) {
            if (miembro.getId().equals(id)) {
                return miembro;
            }
        }
        for (Entrenador entrenador : admin.entrenadores) {
            if (entrenador.getId().equals(id)) {
                return entrenador;
            }
        }

        return null; // Si no se encuentra ninguna coincidencia, devolver null
    }

}