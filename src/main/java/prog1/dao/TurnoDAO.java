package prog1.dao;

import prog1.entities.Paciente;
import prog1.entities.Turno;
import prog1.interfaces.AdmConnexion;
import prog1.interfaces.DAO;

import java.sql.Connection;
import java.util.List;

public class TurnoDAO implements AdmConnexion, DAO<Turno, Integer> {

  private static final String SQL_INSERT =
      "INSERT INTO paciente (nombre, apellido, dni) " +
          "VALUES (?, ?, ?)";

  private static final String SQL_UPDATE =
      "UPDATE paciente SET " +
          "nombre = ?, apellido = ?, dni = ? " +
          "WHERE id = ?";

  private static final String SQL_DELETE =
      "DELETE FROM paciente WHERE id = ?";

  private static final String SQL_GETALL =
      "SELECT * FROM paciente ORDER BY nombre";

  private static final String SQL_GETBYID =
      "SELECT * FROM paciente WHERE id = ?";

  @Override
  public List<Turno> getAll() {
    return List.of();
  }

  @Override
  public void insert(Turno objeto) {

  }

  @Override
  public void update(Turno objeto) {

  }

  @Override
  public void delete(Integer id) {

  }

  @Override
  public Turno getById(Integer id) {
    return null;
  }

  @Override
  public boolean existsById(Integer id) {
    return false;
  }

  @Override
  public Connection obtenerConexion() {
    return AdmConnexion.super.obtenerConexion();
  }
}
