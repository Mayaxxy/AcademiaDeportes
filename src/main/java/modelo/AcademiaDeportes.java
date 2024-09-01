package modelo;



public class AcademiaDeportes {
    private Admin admin;

    public AcademiaDeportes(Admin admin) {
        this.admin = admin;

    }

    /**
     * Autentica a un usuario basado en su ID.
     *
     * @param id El ID del usuario que se desea autenticar.
     * @return El objeto correspondiente al usuario autenticado (Admin, Miembro o Entrenador) si se encuentra;
     * de lo contrario, devuelve null.
     */
    public Object authenticateUser(String id) {
        // Verifica si el ID coincide con el del administrador
        if (admin.getId().equals(id)) {
            return admin;
        }

        // Busca el miembro con el ID proporcionado
        for (Miembro miembro : admin.miembros) {
            if (miembro.getId().equals(id)) {
                return miembro;
            }
        }

        // Busca el entrenador con el ID proporcionado
        for (Entrenador entrenador : admin.entrenadores) {
            if (entrenador.getId().equals(id)) {
                return entrenador;
            }
        }

        // Devuelve null si no se encuentra ninguna coincidencia
        return null;
    }
}