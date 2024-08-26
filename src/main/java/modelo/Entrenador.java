package modelo;

import java.util.ArrayList;
import java.util.List;

public class Entrenador {
    private String nombre;
    private String identificacion;
    private String especialidad;
    private List<Sesion> sesiones;

    public Entrenador(String nombre, String identificacion, String especialidad) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.sesiones = new ArrayList<>();
        this.especialidad = especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return identificacion;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<Sesion> getSesiones() {
        return sesiones;
    }
    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
