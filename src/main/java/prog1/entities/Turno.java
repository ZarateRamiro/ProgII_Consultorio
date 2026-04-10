package prog1.entities;

import java.util.Date;

public class Turno {

    private int id;
    private Date dia;
    private String horario;   // ej: "09:00"
    private int id_paciente;
    private int id_consultorio;

    public Turno() {}

    public Turno(int id, Date dia, String horario, int id_paciente) {
        this.id = id;
        this.dia = dia;
        this.horario = horario;
        this.id_paciente = id_paciente;

    }

    // Constructor sin id (lo asigna la BD)
    public Turno(Date dia, String horario, int id_paciente) {
        this.dia = dia;
        this.horario = horario;
        this.id_paciente = id_paciente;

    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public java.sql.Date getDia() { return (java.sql.Date) dia; }
    public void setDia(Date dia) { this.dia = dia; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public int getId_paciente() { return id_paciente; }
    public void setId_paciente(int id_paciente) { this.id_paciente = id_paciente; }

    public int getId_consultorio() {
        return id_consultorio;
    }

    public void setId_consultorio(int id_consultorio) {
        this.id_consultorio = id_consultorio;
    }

    @Override
    public String toString() {
        return "Turno[" + id + "] - " + dia + " " + horario
                + " | Paciente: " + id_paciente
                + " | Numero del consultorio: " + id_consultorio;
    }
}
