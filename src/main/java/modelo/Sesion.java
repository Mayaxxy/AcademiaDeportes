package modelo;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;

public class Sesion {
    private int duracion;
    private String estado;
    private ChronoLocalDate fecha;
    private Deporte deporte;
    private Entrenador entrenador;

    public Sesion(ChronoLocalDate fecha, int duracion, Deporte deporte, Entrenador entrenador) {
        this.fecha = fecha;
        this.duracion = duracion;
        this.estado = "Programada"; // Inicialmente, una sesión está programada
        this.deporte = deporte;
        this.entrenador = entrenador;
    }


    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ChronoLocalDate getFecha() {
        return fecha;
    }

    public void setFecha(ChronoLocalDate fecha) {
        this.fecha = fecha;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    // establecer el estado de la sesion
    public void establecerEstado() {
        LocalDate fechaActual = LocalDate.now();
        if (fechaActual.isAfter(fecha) || fechaActual.isEqual(fecha)) {
            estado = "Programado";
        } else {
            estado = "Completado";
        }
    }
    // validacion de un solo deporte
    public void validarDeporte(Deporte deporte) {
        if (this.deporte == null) {
            this.deporte = deporte;
        }
    }
    // validacion de un unico entrenador
    public void validarEntrenador(Entrenador entrenador) {
        if (this.entrenador == null) {
            this.entrenador = entrenador;

        }
    }
}
