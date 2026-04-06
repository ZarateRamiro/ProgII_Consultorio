package prog1.dao;

import prog1.entities.Consultorio;
import prog1.entities.Paciente;
import prog1.interfaces.AdmConnexion;
import prog1.interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultorioDAO implements AdmConnexion, DAO<Consultorio, Integer> {

  private static final String SQL_INSERT =
      "INSERT INTO consultorio (numero, descripcion) " +
          "VALUES (?, ?)";

  private static final String SQL_UPDATE =
      "UPDATE consultorio SET " +
          "numero = ?, descripcion = ? " +
          "WHERE id = ?";

  private static final String SQL_DELETE =
      "DELETE FROM consultorio WHERE id = ?";

  private static final String SQL_GETALL =
      "SELECT * FROM consultorio ORDER BY numero";

  private static final String SQL_GETBYID =
      "SELECT * FROM consultorio WHERE id = ?";
  @Override
  public List<Consultorio> getAll() {
    List<Consultorio> listaConsultorio = new ArrayList<>();

    // Uso de try-with-resources: conn, pst, y rs se cierran automáticamente.
    try (Connection conn = obtenerConexion();
         PreparedStatement pst = conn.prepareStatement(SQL_GETALL);
         ResultSet rs = pst.executeQuery()) {

      while (rs.next()) {
        Consultorio consultorio = new Consultorio();
        consultorio.setId(rs.getInt("id"));
        consultorio.setNumero(rs.getInt("numero"));
        consultorio.setMedioco(rs.getString("descripcion"));
        listaConsultorio.add(consultorio);
      }

    } catch (SQLException e) {
      System.err.println("Error al obtener todos los productos.");
      throw new RuntimeException(e);
    }
    return listaConsultorio;
  }

  @Override
  public void insert(Consultorio objeto) {

    Consultorio consultorio = objeto;

    try (Connection conn = obtenerConexion();
         // Se usa Statement.RETURN_GENERATED_KEYS para obtener el ID asignado.
         PreparedStatement pst = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

      pst.setInt(1, consultorio.getNumero());
      pst.setString(2, consultorio.getMedioco());



      pst.executeUpdate();

      // Obtener el ID que la base de datos acaba de asignar
      try (ResultSet rs = pst.getGeneratedKeys()) {
        if (rs.next()) {
          consultorio.setId(rs.getInt(1));
          System.out.println("Consultorio insertado con ID: " + consultorio.getId());
        }
      }

    } catch (SQLException e) {
      System.err.println("Error al insertar el producto.");
      throw new RuntimeException(e);
    }


  }

  @Override
  public void update(Consultorio objeto) {


    Consultorio consultorio = objeto;

    try (Connection conn = obtenerConexion();
         PreparedStatement pst = conn.prepareStatement(SQL_UPDATE)) {

      pst.setInt(1, consultorio.getNumero());
      pst.setString(2, consultorio.getMedioco());



      // Parámetro de la condición WHERE
      pst.setInt(3, consultorio.getId());

      int resultado = pst.executeUpdate();
      if (resultado == 0) {
        System.out.println("Advertencia: No se encontró consultorio con ID " + consultorio.getId() + " para actualizar.");
      }

    } catch (SQLException e) {
      System.err.println("Error al actualizar el consultorio con ID: " + consultorio.getId());
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
        System.out.println("consultorio eliminado correctamente: " + id);
      } else {
        System.out.println("Advertencia: No se encontró consultorio con ID " + id + " para eliminar.");
      }

    } catch (SQLException e) {
      System.err.println("Error al eliminar el consultorio con ID: " + id);
      throw new RuntimeException(e);
    }


  }

  @Override
  public Consultorio getById(Integer id) {

    Consultorio consultorio = null;

    try (Connection conn = obtenerConexion();
         PreparedStatement pst = conn.prepareStatement(SQL_GETBYID)) {

      pst.setInt(1, id);

      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          consultorio = new Consultorio();
          consultorio.setId(rs.getInt("id"));
          consultorio.setNumero(rs.getInt("numero de consultorio"));
          consultorio.setMedioco(rs.getString("medico"));
        }
      }

    } catch (SQLException e) {
      System.err.println("Error al obtener consultorio por ID: " + id);
      throw new RuntimeException(e);
    }

    return consultorio;
  }

  @Override
  public boolean existsById(Integer id) {
    boolean existe = false;

    try (Connection conn = obtenerConexion();
         PreparedStatement pst = conn.prepareStatement(SQL_GETBYID)) {

      pst.setInt(1, id);

      try (ResultSet rs = pst.executeQuery()) {
        // Si hay una fila, el cosnultrooi existe
        if (rs.next()) {
          existe = true;
        }
      }

    } catch (SQLException e) {
      System.err.println("Error al verificar existencia de consultorio: " + id);
      throw new RuntimeException(e);
    }

    return existe;
  }

  @Override
  public Connection obtenerConexion() {
    return AdmConnexion.super.obtenerConexion();
  }
}
