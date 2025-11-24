<%-- 
    Document   : loginAdministrador.jsp
    Created on : 12/11/2025, 6:10:43 p. m.
    Author     : Ailiparra
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ingreso Administrador</title>
    <meta charset="UTF-8">
    <style>
        body { font-family: Arial, sans-serif; background-color: #f7f7f7; }
        .container { margin: 60px auto; padding: 30px; max-width: 350px; background: #fff; border-radius: 8px; box-shadow: 0px 2px 10px #bbb; }
        h2 { text-align: center; color: #444; }
        form { margin-top: 20px; }
        input[type="text"], input[type="password"] { width: 100%; padding: 10px; margin: 10px 0; border: 1px solid #ccc; border-radius: 5px; }
        input[type="submit"] { width: 100%; padding: 10px; background: #0073e6; color: #fff; border: none; border-radius: 5px; cursor: pointer; }
        input[type="submit"]:hover { background: #005bb5; }
        .error { color: #d90000; text-align: center; margin-top: 10px; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Ingreso Administrador</h2>
        <form method="post" action="loginAdministrador">
            <input type="text" name="usuario" placeholder="Usuario" required>
            <input type="password" name="contrasena" placeholder="Contraseña" required>
            <input type="submit" value="Iniciar Sesión">
        </form>
        <% 
            String error = (String) request.getAttribute("error");
            if (error != null) { 
        %>
            <div class="error"><%= error %></div>
        <% } %>
    </div>
</body>
</html>