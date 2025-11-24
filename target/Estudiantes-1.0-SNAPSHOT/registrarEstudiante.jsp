<%-- 
    Document   : RegistrarEstudiante.jsp
    Created on : 12/11/2025, 6:01:20 p. m.
    Author     : Ailiparra
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Íconos FontAwesome para consistencia visual -->
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
        .register-container {
            background: rgba(0,0,0,0.2);
            width: 340px;
            padding: 32px 24px 16px 24px;
            border-radius: 10px;
            box-shadow: 0 8px 22px rgba(41,51,71,0.18);
        }
        .register-container h2 {
            text-align: center;
            color: #fff;
            font-size: 2rem;
            margin-bottom: 21px;
            font-weight: 600;
        }
        .input-group {
            display: flex;
            align-items: center;
            margin-bottom: 16px;
        }
        .input-group i {
            color: #ffc107;
            margin-right: 8px;
            font-size: 1.08rem;
            width: 22px;
            text-align: center;
        }
        .input-group input[type="text"],
        .input-group input[type="email"],
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
            margin-bottom: 19px;
            color: #fff;
            font-size: 0.97rem;
        }
        .show-password input[type="checkbox"] {
            margin-right: 4px;
        }
        .register-btn {
            width: 100%;
            padding: 11px 0;
            background: #6d0707;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 1.09rem;
            font-weight: 600;
            cursor: pointer;
            margin-top: 5px;
            box-shadow: 0 3px 7px rgba(109,7,7,0.13);
            transition: background 0.18s;
        }
        .register-btn:hover {
            background: #a12020;
        }
        .login-link {
            text-align: center;
            margin-top: 14px;
            color: #fff;
            font-size: 1rem;
        }
        .login-link a {
            color: #ffc107;
            text-decoration: none;
            font-weight: 600;
        }
        .login-link a:hover {
            text-decoration: underline;
            color: #e3aa00;
        }
        @media (max-width: 400px) {
            .register-container {
                padding: 13px 3vw 7px 3vw;
                width: 97vw;
            }
        }
    </style>
</head>
<body>
    <form class="register-container" autocomplete="off" method="post" action="estudiante">
        <h2>Registro</h2>
        <% String error = (String) request.getAttribute("error");
           String mensaje = (String) request.getAttribute("mensaje");
           if (error != null) { %>
              <div style="background:#c0392b;color:#fff;padding:9px;border-radius:6px;margin-bottom:10px;text-align:center;">
                  <%= error %>
              </div>
        <% } else if (mensaje != null) { %>
              <div style="background:#27ae60;color:#fff;padding:9px;border-radius:6px;margin-bottom:10px;text-align:center;">
                  <%= mensaje %>
              </div>
        <% } %>
        <div class="input-group">
            <i class="fas fa-user"></i>
            <input type="text" placeholder="Nombre" name="nombre" required>
        </div>
        <div class="input-group">
            <i class="fas fa-user"></i>
            <input type="text" placeholder="Apellido" name="apellido" required>
        </div>
        <div class="input-group">
            <i class="fas fa-user"></i>
            <input type="text" placeholder="DNI" name="dni" required>
        </div>
        <div class="input-group">
            <i class="fas fa-user"></i>
            <input type="text" placeholder="Programa" name="programa" required>
        </div>
        <div class="input-group">
            <i class="fas fa-user"></i>
            <input type="text" placeholder="Semestre" name="semestre" required>
        </div>
        <div class="input-group">
            <i class="fas fa-envelope"></i>
            <input type="email" placeholder="Correo electrónico" name="correo" required>
        </div>
        <div class="input-group">
            <i class="fas fa-lock"></i>
            <input type="password" placeholder="Password" name="password" id="password" required>
        </div>
        <div class="input-group">
            <i class="fas fa-lock"></i>
            <input type="password" placeholder="Confirmar password" name="confirm_password" id="confirm_password" required>
        </div>
        <input type="hidden" name="accion" value="registrar">
        <div class="show-password">
            <input type="checkbox" id="mostrar" onclick="togglePassword()">Mostrar contraseñas
        </div>
        <button type="submit" class="register-btn">Registrar</button>
        <div class="login-link">
            ¿Ya tienes una cuenta? <a href="loginEstudiante.jsp">Iniciar sesión</a>
        </div>
    </form>
    <script>
    function togglePassword() {
        var pass = document.getElementById("password");
        var conf = document.getElementById("confirm_password");
        pass.type = pass.type === "password" ? "text" : "password";
        conf.type = conf.type === "password" ? "text" : "password";
    }
    </script>
</body>
</html>
