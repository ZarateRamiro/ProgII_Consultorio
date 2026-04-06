package prog1.entities;

import java.util.Date;

public class Turno {

    private int id;
    private Date dia;
    private String horario;   // ej: "09:00"
    private Paciente paciente;
    private int nroConsultorio;

    public Turno() {}

    public Turno(int id, Date dia, String horario, Paciente paciente) {
        this.id = id;
        this.dia = dia;
        this.horario = horario;
        this.paciente = paciente;

    }

    // Constructor sin id (lo asigna la BD)
    public Turno(Date dia, String horario, Paciente paciente) {
        this.dia = dia;
        this.horario = horario;
        this.paciente = paciente;

    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getDia() { return dia; }
    public void setDia(Date dia) { this.dia = dia; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public int getNroConsultorio() {
        return nroConsultorio;
    }

    public void setNroConsultorio(int nroConsultorio) {
        this.nroConsultorio = nroConsultorio;
    }

    @Override
    public String toString() {
        return "Turno[" + id + "] - " + dia + " " + horario
                + " | Paciente: " + paciente
                + " | Numero del consultorio: " + nroConsultorio;
    }
}
