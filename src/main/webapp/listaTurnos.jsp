<%@ page import="java.util.List" %>
<%@ page import="prog1.entities.Turno" %>

<h1>Lista de Turnos</h1>

<%
    List<Turno> turnos = (List<Turno>) request.getAttribute("turnos");

    if (turnos == null || turnos.isEmpty()) {
%>
        <p>No hay turnos</p>
<%
    } else {
        for (Turno t : turnos) {
%>
            <p>
                ID: <%= t.getId() %> |
                Fecha: <%= t.getDia() %> |
                Hora: <%= t.getHorario() %> |
                Paciente: <%= t.getId_paciente() %> |
                Consultorio: <%= t.getId_consultorio() %>
            </p>
<%
        }
    }
%>
<br>
<a href="index.jsp">Volver</a>
<br>
<a href="turno.jsp">agregar turno</a>