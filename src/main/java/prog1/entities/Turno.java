package prog1.entities;

import java.util.Date;

public class Turno {

    private int id;
    private Date dia;
    private String horario;   // ej: "09:00"
    private int nroPaciente;
    private int nroConsultorio;

    public Turno() {}

    public Turno(int id, Date dia, String horario, int nroPaciente) {
        this.id = id;
        this.dia = dia;
        this.horario = horario;
        this.nroPaciente = nroPaciente;

    }

    // Constructor sin id (lo asigna la BD)
    public Turno(Date dia, String horario, int nroPaciente) {
        this.dia = dia;
        this.horario = horario;
        this.nroPaciente = nroPaciente;

    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getDia() { return dia; }
    public void setDia(Date dia) { this.dia = dia; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public int getNroPaciente() { return nroPaciente; }
    public void setNroPaciente(int nroPaciente) { this.nroPaciente = nroPaciente; }

    public int getNroConsultorio() {
        return nroConsultorio;
    }

    public void setNroConsultorio(int nroConsultorio) {
        this.nroConsultorio = nroConsultorio;
    }

    @Override
    public String toString() {
        return "Turno[" + id + "] - " + dia + " " + horario
                + " | Paciente: " + nroPaciente
                + " | Numero del consultorio: " + nroConsultorio;
    }
}
