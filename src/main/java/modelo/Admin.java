package modelo;

import java.util.ArrayList;
import java.util.List;

public class Admin {
    private static Admin instance;
    private String nombre;
    private String identificacion;
    private List<Sesion> sesiones;
    public List<Miembro> miembros;
    public List<Entrenador> entrenadores;
    private List<Deporte> deportes;

    public Admin(String nombre, String identificacion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.miembros = new ArrayList<>();
        this.entrenadores = new ArrayList<>();
        this.sesiones = new ArrayList<>();
        this.deportes = new ArrayList<>();
    }
    public static Admin getInstance(String nombre, String id) {
        if (instance == null) {
            instance = new Admin(nombre, id);
        }
        return instance;
    }
    public String getId() {
        return identificacion;
    }
    public Miembro addMiembro(String nombre, String id, String email) {
        Miembro miembro = new Miembro(nombre, id, email);
        if (!miembros.contains(miembro)) {
            miembros.add(miembro);
            return miembro;
        }
        return null; // Miembro ya existe
    }
    public void printMiembros() {
        System.out.println("Lista de miembros:");
        for (Miembro miembro : miembros) {
            System.out.println(miembro.getNombre() + " - " + miembro.getId() + " - " + miembro.getEmail());
        }
    }
    public void printEntrenadores() {
        System.out.println("Lista de entrenadores:");
        for (Entrenador entrenador : entrenadores) {
            System.out.println(entrenador.getNombre() + " - " + entrenador.getId() + " - " + entrenador.getEspecialidad());
        }
    }
    public boolean removerMiembro(String id) {
        for (Miembro miembro : miembros) {
            if (miembro.getId().equals(id)) {
                miembros.remove(miembro);
                System.out.println("Miembro " + miembro.getNombre() + " con ID " + id + " eliminado correctamente");
                return true;
            }
        }
        System.out.println("No se encontró miembro con ID " + id);
        return false;
    }
    public Miembro editarMiembro(String id, String newNombre, String newEmail) {
        Miembro miembro = encontrarMiembro(id);
        if (miembro != null) {
            miembro.setNombre(newNombre);
            miembro.setEmail(newEmail);
            return miembro;
        }
        return null;
    }
    public Entrenador editarEntrenador(String newNombre, String id, String newEspecialidad) {
        Entrenador entrenador = encontrarEntrenador(id);
        if (entrenador != null) {
            entrenador.setNombre(newNombre);
            entrenador.setEspecialidad(newEspecialidad);
            return entrenador;
        }
        return null;
    }
    public boolean removerEntrenador(String id) {
        for (Entrenador entrenador : entrenadores) {
            if (entrenador.getId().equals(id)) {
                entrenadores.remove(entrenador);
                System.out.println("Entrenador " + entrenador.getNombre() + " con ID " + id + " eliminado correctamente");
                return true;
            }
        }
        System.out.println("No se encontró miembro con ID " + id);
        return false;
    }
    public Entrenador addEntrenador(String nombre, String id, String especialidad) {
        Entrenador entrenador = new Entrenador(nombre, id, especialidad);
        this.entrenadores.add(entrenador);
        return entrenador;
    }

    public Miembro encontrarMiembro(String id) {
        for (Miembro miembro : miembros) {
            if (miembro.getId().equals(id)) {
                return miembro;
            }
        }
        return null;
    }
    public Entrenador encontrarEntrenador(String id) {
        for (Entrenador entrenador : entrenadores) {
            if (entrenador.getId().equals(id)) {
                return entrenador;
            }
        }
        return null;
    }
    public Deporte encontrarDeporte(String nombre) {
        for (Deporte deporte : deportes) {
            if (deporte.getNombre().equals(nombre)) {
                return deporte;
            }
        }
        return null;
    }
    public Deporte addDeporte(String nombre, String descripcion, Dificultad dificultad) {
        Deporte deporte = new Deporte(nombre, descripcion, dificultad);
        this.deportes.add(deporte);
        return deporte;
    }

    public Deporte editarDeporte(String nombre, String newNombre, String newDescripcion, Dificultad newDificultad) {
        Deporte deporte = encontrarDeporte(nombre);
        if (deporte != null) {
            deporte.setNombre(newNombre);
            deporte.setDescripcion(newDescripcion);
            deporte.setNivelDificultad(newDificultad);
            return deporte;
        }
        return null;
    }




}
