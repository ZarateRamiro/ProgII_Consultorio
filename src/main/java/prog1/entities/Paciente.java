package prog1.entities;

public class Paciente {

    private int nroPaciente;
    private String nombre;
    private int telefono;
    private String dni;

    public Paciente() {}

    public Paciente(int nroPaciente, String nombre, int telefono, String dni) {
        this.nroPaciente = nroPaciente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.dni = dni;
    }

    public int getNroPaciente() { return nroPaciente; }
    public void setNroPaciente(int nroPaciente) { this.nroPaciente = nroPaciente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getTelefono() { return telefono; }
    public void setTelefono(int telefono) { this.telefono = this.telefono; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    @Override
    public String toString() {
        return telefono + ", " + nombre + " (DNI: " + dni + ")";
    }
}
