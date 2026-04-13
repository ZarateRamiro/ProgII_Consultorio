package prog1.dao;

import prog1.entities.Paciente;
import prog1.entities.Turno;
import prog1.interfaces.AdmConnexion;
import prog1.interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurnoDAO implements AdmConnexion, DAO<Turno, Integer> {

  private static final String SQL_INSERT =
      "INSERT INTO turno (dia, horario, id_paciente, id_consultorio) " +
          "VALUES (?, ?, ?, ?)";

  private static final String SQL_UPDATE =
      "UPDATE turno SET " +
          "dia = ?, horario = ?, id_paciente = ?, id_consultorio = ? " +
          "WHERE id = ?";

  private static final String SQL_DELETE =
      "DELETE FROM turno WHERE id = ?";

  private static final String SQL_GETALL =
      "SELECT * FROM turno ORDER BY dia";

  private static final String SQL_GETBYID =
      "SELECT * FROM turno WHERE id = ?";

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
        turno.setDia(rs.getDate("dia"));
        turno.setHorario(rs.getString("horario"));
        turno.setId_paciente(rs.getInt("id_paciente"));
        turno.setId_consultorio(rs.getInt("id_consultorio"));
        listaTurno.add(turno);
      }

    } catch (SQLException e) {
      System.err.println("Error al obtener todos los turnos.");
      throw new RuntimeException(e);
    }
    return listaTurno;
  }

  @Override
  public void insert(Turno objeto) {

    Turno turno = objeto;

    try (Connection conn = obtenerConexion();
         // Se usa Statement.RETURN_GENERATED_KEYS para obtener el ID asignado.
         PreparedStatement pst = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

      pst.setDate(1, turno.getDia());
      pst.setString(2, turno.getHorario());
      pst.setInt(3,turno.getId_paciente());
      pst.setInt(4,turno.getId_consultorio());

      pst.executeUpdate();

      // Obtener el ID que la base de datos acaba de asignar
      try (ResultSet rs = pst.getGeneratedKeys()) {
        if (rs.next()) {
          turno.setId(rs.getInt(1));
          System.out.println("turno insertado con ID: " + turno.getId());
        }
      }

    } catch (SQLException e) {
      System.err.println("Error al insertar el turno.");
      throw new RuntimeException(e);
    }

  }

  @Override
  public void update(Turno objeto) {
    Turno turno = objeto;

    try (Connection conn = obtenerConexion();
         PreparedStatement pst = conn.prepareStatement(SQL_UPDATE)) {

      // Parámetros de actualización
      pst.setDate(1, turno.getDia());
      pst.setString(2, turno.getHorario());
      pst.setInt(3,turno.getId_paciente());
      pst.setInt(4,turno.getId_consultorio());


      // Parámetro de la condición WHERE
      pst.setInt(5, turno.getId());

      int resultado = pst.executeUpdate();
      if (resultado == 0) {
        System.out.println("Advertencia: No se encontró turno con ID " + turno.getId() + " para actualizar.");
      }

    } catch (SQLException e) {
      System.err.println("Error al actualizar el turno con ID: " + turno.getId());
      throw new RuntimeException(e);
    }


  }

  @Override
  public void delete(Integer id) {

    try (Connection conn = obtenerConexion();
         PreparedStatement pst = conn.prepareStatement(SQL_DELETE)) {

      pst.setInt(1, id);

      int resultado = pst.executeUpdate();
      if (resultado == 1) {
        System.out.println("Turno eliminado correctamente: " + id);
      } else {
        System.out.println("Advertencia: No se encontró Turno con ID " + id + " para eliminar.");
      }

    } catch (SQLException e) {
      System.err.println("Error al eliminar el Turno con ID: " + id);
      throw new RuntimeException(e);
    }


  }

  @Override
  public Turno getById(Integer id) {

    Turno turno = null;

    try (Connection conn = obtenerConexion();
         PreparedStatement pst = conn.prepareStatement(SQL_GETBYID)) {

      pst.setInt(1, id);

      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          turno = new Turno();
          turno.setDia(rs.getDate("dia"));
          turno.setHorario(rs.getString("horario"));
          turno.setId_paciente(rs.getInt("id_paciente"));
          turno.setId_consultorio(rs.getInt("id_consultorio"));
        }
      }

    } catch (SQLException e) {
      System.err.println("Error al obtener turno por ID: " + id);
      throw new RuntimeException(e);
    }

    return turno;
  }

  @Override
  public boolean existsById(Integer id) {

    boolean existe = false;

    try (Connection conn = obtenerConexion();
         PreparedStatement pst = conn.prepareStatement(SQL_GETBYID)) {

      pst.setInt(1, id);

      try (ResultSet rs = pst.executeQuery()) {
        // Si hay una fila, el producto existe
        if (rs.next()) {
          existe = true;
        }
      }

    } catch (SQLException e) {
      System.err.println("Error al verificar existencia de turno: " + id);
      throw new RuntimeException(e);
    }

    return existe;
  }

  @Override
  public Connection obtenerConexion() {
    return AdmConnexion.super.obtenerConexion();
  }
}
