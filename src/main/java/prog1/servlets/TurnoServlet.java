package prog1.servlets;

import prog1.dao.TurnoDAO;
import prog1.entities.Consultorio;
import prog1.entities.Turno;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;


@WebServlet("/turnos")
public class TurnoServlet extends HttpServlet {

  private TurnoDAO dao = new TurnoDAO();

  // 🔍 VER TURNOS
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    List<Turno> lista = dao.getAll();

    request.setAttribute("turnos", lista);

    request.getRequestDispatcher("listaTurnos.jsp").forward(request, response);
  }

  // 💾 AGREGAR / CANCELAR
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    String accion = request.getParameter("accion");

    try {

      if ("agregar".equals(accion)) {

        // 📥 obtener datos del formulario
        String diaStr = request.getParameter("dia");
        String horario = request.getParameter("horario");
        int idPaciente = Integer.parseInt(request.getParameter("id_paciente"));
        int idConsultorio = Integer.parseInt(request.getParameter("id_consultorio"));

        // 🔄 convertir fecha
        java.sql.Date dia = java.sql.Date.valueOf(diaStr);

        // 🧱 crear objeto
        Turno t = new Turno();
        t.setDia(dia);
        t.setHorario(horario);
        t.setId_paciente(idPaciente);
        t.setId_consultorio(idConsultorio);

        dao.insert(t);
      }

      else if ("cancelar".equals(accion)) {

        String diaStr = request.getParameter("dia");
        java.sql.Date dia = java.sql.Date.valueOf(diaStr);

        // 🔥 eliminar todos los turnos de ese día
        List<Turno> lista = dao.getAll();

        for (Turno t : lista) {
          if (t.getDia().equals(dia)) {
            dao.delete(t.getId());
          }
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    response.sendRedirect("turnos");
  }
}