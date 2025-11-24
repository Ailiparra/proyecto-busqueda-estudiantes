<%-- 
    Document   : index.jsp
    Created on : 12/11/2025, 6:00:56 p. m.
    Author     : Ailiparra
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sistema de Gestión de Estudiantes</title>
    <meta charset="UTF-8">
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f4; }
        .container { margin: 50px auto; padding: 30px; max-width: 400px; background: #fff; border-radius: 8px; box-shadow: 0px 2px 8px #ccc; }
        h2 { text-align: center; color: #333; }
        .option { margin: 20px 0; text-align: center; }
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
        <h2>Sistema de Gestión de Estudiantes</h2>
        <div class="option">
            <a href="loginAdministrador.jsp" class="button">Ingreso Administrador</a>
        </div>
        <div class="option">
            <a href="loginEstudiante.jsp" class="button">Ingreso Estudiante</a>
        </div>
    </div>
</body>
</html>