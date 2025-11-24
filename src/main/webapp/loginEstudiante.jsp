<%-- 
    Document   : loginEstudiante.jsp
    Created on : 12/11/2025, 6:10:21 p. m.
    Author     : jeto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inicio sesión</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Íconos FontAwesome para ejemplo rápido -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body {
            background: radial-gradient(circle, #364f6b 70%, #3f4c6b 100%);
            min-height: 100vh;
            margin: 0;
            font-family: 'Montserrat', Arial, sans-serif;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .login-container {
            background: rgba(0,0,0,0.2);
            width: 320px;
            padding: 30px 24px 18px 24px;
            border-radius: 10px;
            box-shadow: 0 8px 20px rgba(41,51,71,0.19);
        }
        .login-container h2 {
            text-align: center;
            color: #fff;
            font-size: 2rem;
            margin-bottom: 22px;
            font-weight: 600;
        }
        .input-group {
            display: flex;
            align-items: center;
            margin-bottom: 17px;
        }
        .input-group i {
            color: #ffc107;
            margin-right: 8px;
            font-size: 1.1rem;
            width: 22px;
            text-align: center;
        }
        .input-group input[type="text"], 
        .input-group input[type="password"] {
            flex: 1;
            padding: 8px 6px;
            border: none;
            border-radius: 4px;
            font-size: 1rem;
            outline: none;
            background: #fff;
            color: #222;
        }
        .show-password {
            margin-bottom: 20px;
            color: #fff;
            font-size: 0.97rem;
        }
        .show-password input[type="checkbox"] {
            margin-right: 4px;
        }
        .login-btn {
            width: 100%;
            padding: 11px 0;
            background: #6d0707;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 1.11rem;
            font-weight: 600;
            cursor: pointer;
            margin-top: 6px;
            box-shadow: 0 3px 7px rgba(109,7,7,0.13);
            transition: background 0.18s;
        }
        .login-btn:hover {
            background: #a12020;
        }
        .register-link {
            text-align: center;
            margin-top: 14px;
            color: #fff;
            font-size: 1rem;
        }
        .register-link a {
            color: #ffc107;
            text-decoration: none;
            font-weight: 600;
        }
        .register-link a:hover {
            text-decoration: underline;
            color: #e3aa00;
        }
        @media (max-width: 400px) {
            .login-container {
                padding: 14px 3vw 10px 3vw;
                width: 97vw;
            }
        }
    </style>
</head>
<body>
    <form class="login-container" autocomplete="off" method="post" action="login">
        <h2>Inicio de Sesión</h2>
        <div class="input-group">
            <i class="fas fa-key"></i>
            <input type="text" placeholder="Ingresar usuario" name="correo" required>
        </div>
        <div class="input-group">
            <i class="fas fa-lock"></i>
            <input type="password" placeholder="Ingresar password" name="password" id="password" required>
        </div>
        <div class="show-password">
            <input type="checkbox" id="mostrar" onclick="togglePassword()">Mostrar contraseña
        </div>
        <button type="submit" class="login-btn">Iniciar sesión</button>
        <div class="register-link">
            ¿Necesitas una cuenta? <a href="registrarEstudiante.jsp">Registrar</a>
        </div>
    </form>

    <script>
    function togglePassword() {
        var pass = document.getElementById("password");
        pass.type = pass.type === "password" ? "text" : "password";
    }
    </script>
</body>
</html>

