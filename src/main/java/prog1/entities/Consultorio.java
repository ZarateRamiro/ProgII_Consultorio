package prog1.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Ejercicio 1: Clase Consultorio con lista de turnos.
 * Ejercicio 2: Método cancelarTurnosPorDia para cancelar todos los turnos de un día.
 */
public class Consultorio {

    private int id;
    private int numero;
    private String medico;

    // a) La lista de turnos se inicializa vacía en el constructor
    private List<Turno> turnos;

    public Consultorio() {
        this.turnos = new ArrayList<>();
    }

    public Consultorio(int id, int numero, String medico) {
        this.id = id;
        this.numero = numero;
        this.medico = medico;
        this.turnos = new ArrayList<>();   // a) inicialización vacía
    }

    // ---------------------------------------------------------------
    // Ejercicio 1 – b) agregarTurno: crea y agrega un nuevo turno
    // ---------------------------------------------------------------
    public void agregarTurno(Date dia, String horario, int nroPaciente) {
        Turno nuevo = new Turno(dia, horario, nroPaciente);
        turnos.add(nuevo);
    }

    public void cancelarTurnosPorDia(Date fecha) {
        turnos.removeIf(turno -> esMismoDia(turno.getDia(), fecha));
    }



    /**
     * Devuelve true si dia1 y dia2 representan el mismo día calendario
     * (ignora la parte horaria).
     */
    public boolean esMismoDia(Date dia1, Date dia2) {
        if (dia1 == null || dia2 == null) return false;
        // Comparamos año, mes y día usando Calendar
        java.util.Calendar c1 = java.util.Calendar.getInstance();
        java.util.Calendar c2 = java.util.Calendar.getInstance();
        c1.setTime(dia1);
        c2.setTime(dia2);
        return c1.get(java.util.Calendar.YEAR)  == c2.get(java.util.Calendar.YEAR)
            && c1.get(java.util.Calendar.MONTH) == c2.get(java.util.Calendar.MONTH)
            && c1.get(java.util.Calendar.DAY_OF_MONTH) == c2.get(java.util.Calendar.DAY_OF_MONTH);
    }

    // ---------------------------------------------------------------
    // Getters / Setters
    // ---------------------------------------------------------------
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getMedico() { return medico; }
    public void setMedico(String medico) { this.medico = medico; }

    public List<Turno> getTurnos() { return turnos; }
    public void setTurnos(List<Turno> turnos) { this.turnos = turnos; }

    @Override
    public String toString() {
        return "Consultorio{" +
            "id=" + id +
            ", numero=" + numero +
            ", medico='" + medico + '\'' +
            ", turnos=" + turnos +
            '}';
    }
}
