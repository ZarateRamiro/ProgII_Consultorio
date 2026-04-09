package prog1.dao;

import prog1.entities.Paciente;
import prog1.entities.Turno;
import prog1.interfaces.AdmConnexion;
import prog1.interfaces.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    List<Turno> listaTurno = new ArrayList<>();

    // Uso de try-with-resources: conn, pst, y rs se cierran automáticamente.
    try (Connection conn = obtenerConexion();
         PreparedStatement pst = conn.prepareStatement(SQL_GETALL);
         ResultSet rs = pst.executeQuery()) {

      while (rs.next()) {
        Turno turno = new Turno();
        turno.setId(rs.getInt("id"));
        turno.setNombre(rs.getString("nombre"));
        turno.setApellido(rs.getString("apellido"));
        // NOTA: Usar rs.getDouble() si 'precio' es DECIMAL/DOUBLE en la DB.
        paciente.setDni(rs.getString("DNI"));
        listaTurno.add(turno);
      }

    } catch (SQLException e) {
      System.err.println("Error al obtener todos los productos.");
      throw new RuntimeException(e);
    }
    return listaTurno;;
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
