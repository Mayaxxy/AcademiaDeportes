package modelo;
import java.util.ArrayList;
import java.util.List;

public class Miembro {
    private String nombre;
    private String correo;
    private String id;
    private Categoria categoria;
    private List<Sesion> sesionesMiembro;

    public Miembro(String nombre, String correo, String id, Categoria categoria) {
        this.nombre = nombre;
        this.correo = correo;
        this.id = id;
        this.sesionesMiembro = new ArrayList<>();
        this.categoria = categoria;
    }

    public List<Sesion> getSesiones() {
        return sesionesMiembro;
    }
    public String inscribirSesion(Sesion sesion) {
        Deporte deporte = sesion.getDeporte();
        // Actualizar y verificar el estado de la sesión
        sesion.establecerEstado(); // Asegura que el estado esté actualizado antes de la validación
        if (sesion.getEstado().equals("Completada")) {
            System.out.println("No se puede inscribir en la sesión, ya ha sido completada.");
            return "fechapasada";
        }

        // Verificar si el miembro puede inscribirse según su categoría y el nivel de dificultad del deporte
        if (categoria == Categoria.JUVENIL && deporte.getNivelDificultad() == Dificultad.ALTO) {
            System.out.println("Miembro juvenil no puede inscribirse en deportes de nivel ALTO.");
            return "edadinsuficiente";
        } else
            sesionesMiembro.add(sesion);
        return "bienregistrada";
    }
    public void eliminarSesion(Sesion sesion) {
        if (sesionesMiembro.contains(sesion)) {
            sesionesMiembro.remove(sesion);
            System.out.println("Sesion removida satisfactoriamente.");
        } else {
            System.out.println("Sesion no encontrada.");
        }
    }

    public String getEmail(){
        return correo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setEmail(String correo) {
        this.correo = correo;
    }

    public String getId() {
        return id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
