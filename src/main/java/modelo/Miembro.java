package modelo;
import java.util.ArrayList;
import java.util.List;

public class Miembro {
    private String nombre;
    private String correo;
    private String identificacion;
    private List<Sesion> sesiones;

    public Miembro(String nombre, String correo, String identificacion) {
        this.nombre = nombre;
        this.correo = correo;
        this.identificacion = identificacion;
        this.sesiones = new ArrayList<>();
    }

    public List<Sesion> getSesiones() {
        return sesiones;
    }

    public void eliminarSesion(Sesion sesion) {
        if (sesiones.contains(sesion)) {
            sesiones.remove(sesion);
            System.out.println("Sesion removida satisfactoriamente.");
        } else {
            System.out.println("Sesion no encontrada.");
        }
    }

    public void agregarSesion(Sesion sesion) {
        sesiones.add(sesion);
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
    public void setEmail(String correo) {
        this.correo = correo;
    }

    public String getId() {
        return identificacion;
    }
}
