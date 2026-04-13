<h1>Consultorio</h1>

<!-- VER TURNOS -->
<a href="turnos">
    <button>Ver Turnos</button>
</a>

<!-- AGREGAR TURNO -->
<form action="turnos" method="post">
    <input type="hidden" name="accion" value="agregar">
    <button type="submit">Agregar Turno</button>
</form>

<!-- CANCELAR -->
<form action="turnos" method="post">
    <input type="hidden" name="accion" value="cancelar">
    <button type="submit">Cancelar Turnos de Hoy</button>
</form>