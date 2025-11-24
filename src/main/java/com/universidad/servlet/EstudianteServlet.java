
package com.universidad.servlet;

import com.universidad.dao.ConexionBD;
import com.universidad.modelo.Estudiante;
import com.universidad.negocio.SistemaRegistro;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author Ailiparra
 */
@WebServlet(name = "EstudianteServlet", urlPatterns = {"/estudiante", "/listarEstudiantes", "/login"})
public class EstudianteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        ConexionBD conexion = new ConexionBD();

        if ("/listarEstudiantes".equals(path)) {
            if (conexion.conectar()) {
                ArrayList<Estudiante> lista = conexion.obtenerEstudiantes();
                request.setAttribute("estudiantes", lista);
                conexion.cerrar();
            }
            request.getRequestDispatcher("listarEstudiantes.jsp").forward(request, response);

        } else if ("/login".equals(path)) {
            // Muestra formulario de login
            request.getRequestDispatcher("loginEstudiante.jsp").forward(request, response);

        } else {
            // Formulario de registro
            request.getRequestDispatcher("registrarEstudiante.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        ConexionBD conexion = new ConexionBD();

        if ("/estudiante".equals(path)) {
            // Registro
            if (conexion.conectar()) {
                int matricula = conexion.generarMatricula();
                int dni = Integer.parseInt(request.getParameter("dni"));
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                String programa = request.getParameter("programa");
                int semestre = Integer.parseInt(request.getParameter("semestre"));
                String correo = request.getParameter("correo");
                String password = request.getParameter("password");

                Estudiante nuevo = new Estudiante(matricula, dni, nombre, apellido, programa, semestre, correo, password);

                boolean exito = conexion.insertarEstudiante(nuevo);
                conexion.cerrar();

                if (exito) {
                    response.sendRedirect("listarEstudiantes");
                    return;
                } else {
                    request.setAttribute("error", "Error al registrar estudiante");
                    request.getRequestDispatcher("registrarEstudiante.jsp").forward(request, response);
                    return;
                }
            }
        }
        
        else if ("/login".equals(path)) {
            // Login
            String correo = request.getParameter("correo");
            String password = request.getParameter("password");

            if (conexion.conectar()) {
                Estudiante est = conexion.loginEstudiante(correo, password);
                conexion.cerrar();
                if (est != null) {
                    // Guarda estudiante en sesión tras login
                    HttpSession sesion = request.getSession();
                    sesion.setAttribute("estudianteLogueado", est);
                    // Redirige a dashboard para evitar reenviar datos al recargar
                    response.sendRedirect("dashboardEstudiante.jsp");
                    return;
                } else {
                    request.setAttribute("error", "Correo o contraseña incorrectos");
                    request.getRequestDispatcher("loginEstudiante.jsp").forward(request, response);
                    return;
                }
            } else {
                request.setAttribute("error", "Error de conexión a la base de datos.");
                request.getRequestDispatcher("loginEstudiante.jsp").forward(request, response);
                return;
            }
        }
    }
}