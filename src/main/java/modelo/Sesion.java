package modelo;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public class Sesion {
    private int duracion;
    private String estado;
    private LocalDate fecha;
    private Deporte deporte;
    private Entrenador entrenador;

    public Sesion(int duracion, LocalDate fecha, Deporte deporte, Entrenador entrenador) {
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

    public ChronoLocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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
            estado = "Completada";
        } else {
            estado = "Programada";
        }
    }
    @Override
    public String toString() {
        return fecha.toString() +" "+ deporte ;
    }

}
