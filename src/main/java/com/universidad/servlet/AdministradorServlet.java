
package com.universidad.servlet;

import com.universidad.modelo.Administrador;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
/**
 *
 * @author Ailiparra
 */
@WebServlet(name = "AdministradorServlet", urlPatterns = {"/loginAdministrador"})
public class AdministradorServlet extends HttpServlet {

    // Aquí puedes establecer usuario/contraseña válidos (cambiar por consulta a BD si lo requieres)
    private final String USUARIO_VALIDO = "admin";
    private final String CONTRASENA_VALIDA = "admin123";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener credenciales del formulario de login
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        // Validar credenciales
        if (usuario != null && contrasena != null
                && usuario.equals(USUARIO_VALIDO)
                && contrasena.equals(CONTRASENA_VALIDA)) {
            // Inicio de sesión exitoso
            Administrador admin = new Administrador(usuario, contrasena);
            HttpSession sesion = request.getSession(true);
            sesion.setAttribute("administrador", admin);
            response.sendRedirect("panelAdministrador.jsp");
        } else {
            // Credenciales inválidas
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("loginAdministrador.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige a la vista de login si accede por GET
        response.sendRedirect("loginAdministrador.jsp");
    }
}