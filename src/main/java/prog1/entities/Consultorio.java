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
    private String medioco;

    // a) La lista de turnos se inicializa vacía en el constructor
    private List<Turno> turnos;

    public Consultorio() {
        this.turnos = new ArrayList<>();
    }

    public Consultorio(int id, int numero, String medioco) {
        this.id = id;
        this.numero = numero;
        this.medioco = medioco;
        this.turnos = new ArrayList<>();   // a) inicialización vacía
    }

    // ---------------------------------------------------------------
    // Ejercicio 1 – b) agregarTurno: crea y agrega un nuevo turno
    // ---------------------------------------------------------------
    public void agregarTurno(Date dia, String horario, Paciente paciente) {
        Turno nuevo = new Turno(dia, horario, paciente);
        turnos.add(nuevo);
    }

    // ---------------------------------------------------------------
    // Ejercicio 2 – cancelarTurnosPorDia
    // Se cancelan todos los turnos cuyo día coincida con el parámetro.
    // Se apoya en esMismoDia(Date, Date).
    // ---------------------------------------------------------------
    public void cancelarTurnosPorDia(Date dia) {
        for (Turno t : turnos) {
            if (!t.isCancelado() && esMismoDia(t.getDia(), dia)) {
                t.setCancelado(true);
            }
        }
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

    public String getMedioco() { return medioco; }
    public void setMedioco(String medioco) { this.medioco = medioco; }

    public List<Turno> getTurnos() { return turnos; }
    public void setTurnos(List<Turno> turnos) { this.turnos = turnos; }
}
