<%-- 
    Document   : dashboardEstudiante.jsp
    Created on : 23/11/2025, 9:00:26 a. m.
    Author     : Ailiparra
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.universidad.modelo.Estudiante" %>

<%
    // Recupera el estudiante desde sesión (tras login o registro)
    Estudiante est = (Estudiante) session.getAttribute("estudianteLogueado");
    // Si también lo recibes por request, alternativamente:
    // Estudiante est = (Estudiante) request.getAttribute("estudiante");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard Estudiante</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f7f7f7; }
        .dashboard { max-width: 500px; margin: 60px auto; background: #fff; border-radius: 10px; box-shadow: 0 2px 8px #ddd; padding: 28px; text-align: center; }
        .matricula { font-size: 2em; color: #0060e6; margin: 20px 0 16px; }
        .label { font-weight: bold; color: #444; }
    </style>
</head>
<body>
    <div class="dashboard">
        <h2>¡Bienvenido al Dashboard, <%= est.getNombre() %>!</h2>
        <p class="label">Tu número de matrícula es:</p>
        <div class="matricula">
            <%= est.getMatricula() %>
        </div>
        <hr>
        <p>Recuerda este número para realizar trámites y consultas en la Universidad.</p>
        <!-- Puedes agregar botones o más información relevante aquí -->
    </div>
</body>
<style>
.boton-salir-custom {
    background: #0000ee;
    color: white;
    font-size: 2em;
    font-weight: bold;
    border: 3px solid #000;
    border-radius: 40px;
    padding: 20px 40px;
    text-align: center;
    display: inline-block;
    box-shadow: 1px 2px 10px #eee;
    margin-top: 30px;
    transition: background 0.2s, color 0.2s;
}
.boton-salir-custom:hover {
    background: #2222ff;
    color: #f2f2f2;
}
</style>

<div style="width: 100%; display: flex; justify-content: center;">
    <a href="index.html" class="boton-salir-custom">Salir</a>
</div>
</html>