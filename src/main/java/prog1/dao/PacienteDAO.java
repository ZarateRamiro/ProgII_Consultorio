package prog1.dao;

import prog1.entities.Paciente;
import prog1.interfaces.AdmConnexion;
import prog1.interfaces.DAO;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO implements AdmConnexion, DAO<Paciente, Integer> {

  // cuando se usa try-with-resources, ya que la conexión se gestiona localmente.
  // private Connection conn = null;

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

  public List<Paciente> getAll() {
    List<Paciente> listaPacientes = new ArrayList<>();

    // Uso de try-with-resources: conn, pst, y rs se cierran automáticamente.
    try (Connection conn = obtenerConexion();
         PreparedStatement pst = conn.prepareStatement(SQL_GETALL);
         ResultSet rs = pst.executeQuery()) {

      while (rs.next()) {
        Paciente paciente = new Paciente();
        paciente.setId(rs.getInt("id"));
        paciente.setNombre(rs.getString("nombre"));
        paciente.setApellido(rs.getString("apellido"));
        // NOTA: Usar rs.getDouble() si 'precio' es DECIMAL/DOUBLE en la DB.
        paciente.setDni(rs.getString("DNI"));
        listaPacientes.add(paciente);
      }

    } catch (SQLException e) {
      System.err.println("Error al obtener todos los productos.");
      throw new RuntimeException(e);
    }
    return listaPacientes;
  }

  @Override
  public void insert(Paciente objeto) {

    Paciente paciente = objeto;

    try (Connection conn = obtenerConexion();
         // Se usa Statement.RETURN_GENERATED_KEYS para obtener el ID asignado.
         PreparedStatement pst = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

      pst.setString(1, paciente.getNombre());
      pst.setString(2, paciente.getApellido());
      pst.setString(3, paciente.getDni());


      pst.executeUpdate();

      // Obtener el ID que la base de datos acaba de asignar
      try (ResultSet rs = pst.getGeneratedKeys()) {
        if (rs.next()) {
          paciente.setId(rs.getInt(1));
          System.out.println("Paciente insertado con ID: " + paciente.getId());
        }
      }

    } catch (SQLException e) {
      System.err.println("Error al insertar el producto.");
      throw new RuntimeException(e);
    }


  }

  @Override
  public void update(Paciente objeto) {

    Paciente paciente = objeto;

    try (Connection conn = obtenerConexion();
         PreparedStatement pst = conn.prepareStatement(SQL_UPDATE)) {

      // Parámetros de actualización
      pst.setString(1, paciente.getNombre());
      pst.setString(2, paciente.getApellido());
      pst.setString(3, paciente.getDni());

      // Parámetro de la condición WHERE
      pst.setInt(5, paciente.getId());

      int resultado = pst.executeUpdate();
      if (resultado == 0) {
        System.out.println("Advertencia: No se encontró paciente con ID " + paciente.getId() + " para actualizar.");
      }

    } catch (SQLException e) {
      System.err.println("Error al actualizar el paciente con ID: " + paciente.getId());
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
        System.out.println("Paciente eliminado correctamente: " + id);
      } else {
        System.out.println("Advertencia: No se encontró Paciente con ID " + id + " para eliminar.");
      }

    } catch (SQLException e) {
      System.err.println("Error al eliminar el Paciente con ID: " + id);
      throw new RuntimeException(e);
    }

  }

  @Override
  public Paciente getById(Integer id) {

    Paciente paciente = null;

    try (Connection conn = obtenerConexion();
         PreparedStatement pst = conn.prepareStatement(SQL_GETBYID)) {

      pst.setInt(1, id);

      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          paciente = new Paciente();
          paciente.setId(rs.getInt("id"));
          paciente.setNombre(rs.getString("nombre"));
          paciente.setApellido(rs.getString("apellido"));
          paciente.setDni(rs.getString("DNI"));
        }
      }

    } catch (SQLException e) {
      System.err.println("Error al obtener paciente por ID: " + id);
      throw new RuntimeException(e);
    }

    return paciente;}

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
      System.err.println("Error al verificar existencia de paciente: " + id);
      throw new RuntimeException(e);
    }

    return existe;
  }

  @Override
  public Connection obtenerConexion() {
    return AdmConnexion.super.obtenerConexion();
  }
}
