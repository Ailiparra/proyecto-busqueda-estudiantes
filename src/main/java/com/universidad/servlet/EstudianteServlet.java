
package com.universidad.servlet;

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
@WebServlet(name = "EstudianteServlet", urlPatterns = {"/estudiante"})
public class EstudianteServlet extends HttpServlet {

    private SistemaRegistro sistema;

    @Override
    public void init() throws ServletException {
        sistema = new SistemaRegistro(); // Inicializa ABB y conexión BD
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Listar todos los estudiantes por defecto
        ArrayList<Estudiante> lista = sistema.obtenerTodos();
        request.setAttribute("estudiantes", lista);
        request.getRequestDispatcher("listarEstudiantes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("registrar".equals(accion)) {
            // Registrar nuevo estudiante desde formulario
            // (Valida que todos los campos requeridos están presentes)
            int matricula = sistema.generarMatricula();
            String dni = request.getParameter("dni");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String programa = request.getParameter("programa");
            int semestre = Integer.parseInt(request.getParameter("semestre"));
            String correo = request.getParameter("correo");
            Estudiante nuevo = new Estudiante(matricula, dni, nombre, apellido, programa, semestre, correo);

            boolean exito = sistema.registrarEstudiante(nuevo);
            if (exito) {
                request.setAttribute("mensaje", "Estudiante registrado con éxito");
            } else {
                request.setAttribute("error", "Error al registrar estudiante");
            }
            doGet(request, response);
        }
        else if ("buscar".equals(accion)) {
            // Buscar estudiante por matrícula desde formulario
            int matriculaBuscada = Integer.parseInt(request.getParameter("matricula"));
            Estudiante encontrado = sistema.buscarPorMatricula(matriculaBuscada);
            request.setAttribute("estudiante", encontrado);
            request.getRequestDispatcher("detalleEstudiante.jsp").forward(request, response);
        }
        // Puedes agregar más acciones aquí (actualizar, eliminar, etc.)
    }
}