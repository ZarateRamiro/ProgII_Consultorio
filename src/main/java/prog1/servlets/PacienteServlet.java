package prog1.servlets;

import prog1.dao.PacienteDAO;
import prog1.entities.Paciente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/paciente")
public class PacienteServlet extends HttpServlet {

  private PacienteDAO dao = new PacienteDAO();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    try {
      // 🔥 CREAR PACIENTE DE PRUEBA
      Paciente p = new Paciente();
      p.setNombre("Rami");
      p.setTelefono(123456789);
      p.setDni("12345678");

      // 🔥 INSERTAR EN DB
      dao.insert(p);

      out.println("<h2>Paciente insertado correctamente</h2>");
      out.println("<p>ID generado: " + p.getNroPaciente() + "</p>");

    } catch (Exception e) {
      out.println("<h2>Error</h2>");
      out.println("<p>" + e.getMessage() + "</p>");
      e.printStackTrace(out);
    }
  }
}