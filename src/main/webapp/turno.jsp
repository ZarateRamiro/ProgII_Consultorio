<h1>Gestión de Turnos</h1>

<h2>Agregar Turno</h2>

<form action="turnos" method="post">

    <input type="hidden" name="accion" value="agregar">

    <label>Fecha:</label>
    <input type="date" name="dia" required><br><br>

    <label>Horario:</label>
    <input type="time" name="horario" required><br><br>

    <label>ID Paciente:</label>
    <input type="number" name="id_paciente" required><br><br>

    <label>ID Consultorio:</label>
    <input type="number" name="id_consultorio" required><br><br>

    <button type="submit">Guardar Turno</button>
</form>

<hr>

<h2>Cancelar Turnos por Fecha</h2>

<form action="turnos" method="post">
    <input type="hidden" name="accion" value="cancelar">

    <label>Fecha:</label>
    <input type="date" name="dia" required>

    <button type="submit">Cancelar</button>
</form>

<hr>

<a href="turnos">Ver Turnos</a>