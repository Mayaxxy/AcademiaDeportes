package modelo;
import java.util.ArrayList;
import java.util.List;

public class Deporte {
    private String nombre;
    private String descripcion;
    private List<Entrenador> entrenadores;
    private Dificultad nivelDificultad;

    public Deporte(String nombre, String descripcion, Dificultad dificultad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivelDificultad = dificultad;
        this.entrenadores = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public Dificultad getNivelDificultad() {
        return nivelDificultad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setNivelDificultad(Dificultad nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    public List<Entrenador> getEntrenadores() {
        return entrenadores;
    }
    @Override
    public String toString() {
        return nombre ;
    }
}
