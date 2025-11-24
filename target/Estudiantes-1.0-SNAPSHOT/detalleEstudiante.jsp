<%-- 
    Document   : detalleEstudiante.jsp
    Created on : 12/11/2025, 6:11:16 p. m.
    Author     : Ailiparra
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Detalle de Estudiante</title>
    <meta charset="UTF-8">
    <style>
        body { font-family: Arial, sans-serif; background-color: #f8f8f8; }
        .container { margin: 40px auto; padding: 30px; max-width: 400px; background: #fff; border-radius: 8px; box-shadow: 0px 2px 12px #ddd; }
        h2 { text-align: center; color: #337ab7; }
        .detalles { margin-top: 20px; }
        .detalles dt { font-weight: bold; color: #555; }
        .detalles dd { margin: 0 0 10px 0; color: #444; }
        .boton-volver {
            display: block; text-align: center; margin-top: 25px;
        }
        a.button {
            display: inline-block; padding: 10px 25px;
            background: #0073e6; color: #fff; text-decoration: none;
            border-radius: 5px; transition: background 0.2s;
        }
        a.button:hover { background: #005bb5; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Detalle del Estudiante</h2>
        <c:if test="${not empty estudiante}">
            <dl class="detalles">
                <dt>Matrícula:</dt>
                <dd>${estudiante.matricula}</dd>
                <dt>DNI:</dt>
                <dd>${estudiante.dni}</dd>
                <dt>Nombre:</dt>
                <dd>${estudiante.nombre}</dd>
                <dt>Apellido:</dt>
                <dd>${estudiante.apellido}</dd>
                <dt>Programa:</dt>
                <dd>${estudiante.programa}</dd>
                <dt>Semestre:</dt>
                <dd>${estudiante.semestre}</dd>
                <dt>Correo:</dt>
                <dd>${estudiante.correo}</dd>
            </dl>
        </c:if>
        <c:if test="${empty estudiante}">
            <div class="detalles">
                <p style="color: #d90000;"><strong>No se encontró ningún estudiante con esa matrícula.</strong></p>
            </div>
        </c:if>
        <div class="boton-volver">
            <a href="listarEstudiantes.jsp" class="button">Volver al listado</a>
        </div>
    </div>
</body>
</html>