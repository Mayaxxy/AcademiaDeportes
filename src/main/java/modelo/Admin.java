package modelo;

import java.time.LocalDate;
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
    public Miembro addMiembro(Miembro miembro) {
        if (!miembros.contains(miembro)) {
            miembros.add(miembro);
            return miembro;
        }
        return null; // Miembro ya existe
    }
    public Sesion addSesion(Sesion sesion) {
        if (!sesiones.contains(sesion)) {
            sesiones.add(sesion);
            return sesion;
        }
        return null; // Sesion ya existe
    }
    public Sesion addSesionEntrenador(Sesion sesion, List<Sesion> sesiones) {
        if (!sesiones.contains(sesion)) {
            sesiones.add(sesion);
            return sesion;
        }
        return null; // Sesion ya existe
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
    public Miembro editarMiembro(String id, String newNombre, String newEmail, String newId, Categoria newCategoria) {
        Miembro miembro = encontrarMiembro(id);
        if (miembro != null) {
            miembro.setNombre(newNombre);
            miembro.setEmail(newEmail);
            miembro.setId(newId);
            miembro.setCategoria(newCategoria);
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
    public Entrenador addEntrenador(Entrenador entrenador) {
        if (!entrenadores.contains(entrenador)) {
            entrenadores.add(entrenador);
            return entrenador;
        }
        return null; // Miembro ya existe
    }
//encontrar un miembro por id
    public Miembro encontrarMiembro(String id) {
        for (Miembro miembro : miembros) {
            if (miembro.getId().equals(id)) {
                return miembro;
            }
        }
        return null;
    }
    //encuentra un entrenador por id
    public Entrenador encontrarEntrenador(String id) {
        for (Entrenador entrenador : entrenadores) {
            if (entrenador.getId().equals(id)) {
                return entrenador;
            }
        }
        return null;
    }
    //encuentra un deporte por nombre
    public Deporte encontrarDeporte(String nombre) {
        for (Deporte deporte : deportes) {
            if (deporte.getNombre().equalsIgnoreCase(nombre)) {
                return deporte;
            }
        }
        return null;
    }
    //encuentra una sesion por duracion
    public Sesion encontrarSesion(int duracion) {
        for (Sesion sesion : sesiones) {
            if (sesion.getDuracion() ==(duracion)) {
                return sesion;
            }
        }
        return null;
    }
    public Deporte addDeporte(Deporte deporte) {
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
    public Sesion editarSesion(int duracion, int newDuracion, LocalDate newFecha, Deporte newDeporte, Entrenador newEntrenador) {
        Sesion sesion = encontrarSesion(duracion);
        if (sesion != null) {
            sesion.setDuracion(newDuracion);
            sesion.setFecha(newFecha);
            sesion.setDeporte(newDeporte);
            sesion.setEntrenador(newEntrenador);
            return sesion;
        }
        return null;
    }
    public boolean removerSesion(Sesion sesion) {
        for (Sesion sesion1 : sesiones) {
            if (sesion1.equals(sesion)) {
                sesiones.remove(sesion);
                System.out.println("Sesion eliminada correctamente");
                return true;
            }
        }
        System.out.println("No se encontró sesion");
        return false;
    }
    public boolean removerDeporte(String nombre) {
        for (Deporte deporte : deportes) {
            if (deporte.getNombre().equalsIgnoreCase(nombre)) {
                deportes.remove(deporte);
                System.out.println("Deporte " + deporte.getNombre() + "" + nombre + " eliminado correctamente");
                return true;
            }
        }
        System.out.println("No se encontró deporte " + nombre);
        return false;
    }
    public List<Deporte> getDeportes() {
        return deportes;
    }
    public List<Entrenador> getEntrenadores() {
        return entrenadores;
    }
    public List<Sesion> getSesiones() {
        return sesiones;
    }




}
