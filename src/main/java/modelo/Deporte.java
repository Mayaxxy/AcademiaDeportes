package modelo;

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

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }
        public void setNivelDificultad(Dificultad nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

}
