<%-- 
    Document   : modificarEstudiante.jsp
    Created on : 23/11/2025, 1:28:27 p. m.
    Author     : Ailiparra
--%>

<%@ page import="com.universidad.modelo.Estudiante" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%
    Estudiante est = (Estudiante) request.getAttribute("estudiante");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Modificar Estudiante</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <div class="card">
        <div class="card-header bg-info text-white">
            <h4>Modificar Estudiante</h4>
        </div>
        <div class="card-body">
            <form method="post" action="actualizar">
                <input type="hidden" name="accion" value="actualizar">
                <input type="hidden" name="matricula" value="<%= est.getMatricula() %>">

                <div class="form-group">
                    <label for="programa">Programa</label>
                    <input type="text" class="form-control" name="programa" id="programa" value="<%= est.getPrograma() %>" required>
                </div>
                <div class="form-group">
                    <label for="semestre">Semestre</label>
                    <input type="text" class="form-control" name="semestre" id="semestre" value="<%= est.getSemestre() %>" required>
                </div>
                <div class="form-group">
                    <label for="correo">Correo</label>
                    <input type="email" class="form-control" name="correo" id="correo" value="<%= est.getCorreo() %>" required>
                </div>
                <div class="form-group">
                    <label for="password">Contraseña</label>
                    <input type="text" class="form-control" name="password" id="password" value="<%= est.getPassword() %>" required>
                </div>
                <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                <a href="panelAdministrador.jsp" class="btn btn-secondary">Cancelar</a>
            </form>
        </div>
    </div>
</div>
</body>
</html>