
package com.universidad.servlet;

import com.universidad.dao.ConexionBD;
import com.universidad.modelo.Administrador;
import com.universidad.modelo.ArbolABB;
import com.universidad.modelo.Estudiante;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdministradorServlet", urlPatterns = {"/loginAdministrador", "/listar", "/buscar", "/modificar","/actualizar", "/eliminar"})
public class AdministradorServlet extends HttpServlet {

    private final String USUARIO_VALIDO = "admin";
    private final String CONTRASENA_VALIDA = "admin123";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        HttpSession sesion = request.getSession();

        if ("/loginAdministrador".equals(path)) {
            // Login de administrador
            String usuario = request.getParameter("usuario");
            String contrasena = request.getParameter("contrasena");

            if (usuario != null && contrasena != null
                    && usuario.equals(USUARIO_VALIDO)
                    && contrasena.equals(CONTRASENA_VALIDA)) {
                Administrador admin = new Administrador(usuario, contrasena);
                sesion.setAttribute("administrador", admin);

                // Opcional: inicializa ArbolABB en sesión para búsquedas en memoria
                if (sesion.getAttribute("arbolEstudiantes") == null) {
                    sesion.setAttribute("arbolEstudiantes", new ArbolABB());

                    // Alternativamente, carga estudiantes desde la BD al ABB:
                    ConexionBD conexion = new ConexionBD();
                    if (conexion.conectar()) {
                        ArrayList<Estudiante> lista = conexion.obtenerEstudiantes();
                        for (Estudiante est : lista) {
                            ((ArbolABB) sesion.getAttribute("arbolEstudiantes")).insertar(est);
                        }
                        conexion.cerrar();
                    }
                }
                response.sendRedirect("panelAdministrador.jsp");
            } else {
                request.setAttribute("error", "Usuario o contraseña incorrectos");
                request.getRequestDispatcher("loginAdministrador.jsp").forward(request, response);
            }
        }

        else if ("/listar".equals(path)) {
            // Listar todos los estudiantes desde base de datos
            ConexionBD conexion = new ConexionBD();
            ArrayList<Estudiante> lista = new ArrayList<>();
            if (conexion.conectar()) {
                lista = conexion.obtenerEstudiantes();
                conexion.cerrar();
            }
            request.setAttribute("estudiantes", lista);
            request.getRequestDispatcher("panelAdministrador.jsp").forward(request, response);
        }

        else if ("/buscar".equals(path)) {
            // Buscar estudiante por matrícula usando ABB en sesión
            String matriculaBuscar = request.getParameter("matriculaBuscar");
            ArbolABB arbol = (ArbolABB) sesion.getAttribute("arbolEstudiantes");
            List<Estudiante> estudiantes = new ArrayList<>();
            if (matriculaBuscar != null && !matriculaBuscar.isEmpty() && arbol != null) {
                try {
                    int matricula = Integer.parseInt(matriculaBuscar);
                    Estudiante est = arbol.buscar(matricula);
                    System.out.println("Resultado de búsqueda: " + est);
                    if (est != null) {
                        estudiantes.add(est);
                    } else {
                        request.setAttribute("mensaje", "No se encontró estudiante con esa matrícula.");
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("mensaje", "Matrícula inválida.");
                }
            }
            request.setAttribute("estudiantes", estudiantes);
            request.getRequestDispatcher("panelAdministrador.jsp").forward(request, response);
        }

        // PASO 1: Al hacer click en modificar desde la tabla
        else if ("/modificar".equals(path)) {
            int matricula = Integer.parseInt(request.getParameter("matricula"));
            ConexionBD conexion = new ConexionBD();
            Estudiante est = null;
            if (conexion.conectar()) {
                est = conexion.obtenerEstudiantePorMatricula(matricula); 
                conexion.cerrar();
            }
            request.setAttribute("estudiante", est);
            request.getRequestDispatcher("modificarEstudiante.jsp").forward(request, response);
            
        }

        // PASO 2: Al enviar los nuevos datos desde modificarEstudiante.jsp
        else if ("/actualizar".equals(path)) {
            int matricula = Integer.parseInt(request.getParameter("matricula"));
            String programa = request.getParameter("programa");
            int semestre = Integer.parseInt(request.getParameter("semestre"));
            String correo = request.getParameter("correo");
            String password = request.getParameter("password");

            ConexionBD conexion = new ConexionBD();
            Estudiante estOriginal = null;
            if (conexion.conectar()) {
                estOriginal = conexion.obtenerEstudiantePorMatricula(matricula);
                conexion.cerrar();
            }

            Estudiante actualizado = new Estudiante(
                matricula,
                estOriginal != null ? estOriginal.getDni() : 0,           // int, valor por defecto 0
                estOriginal != null ? estOriginal.getNombre() : "",
                estOriginal != null ? estOriginal.getApellido() : "",
                programa,
                semestre,     
                correo,
                password
            );

            boolean actualizadoOK = false;
            ConexionBD cnxEdit = new ConexionBD();
            if (cnxEdit.conectar()) {
                actualizadoOK = cnxEdit.actualizarEstudiante(actualizado);
                cnxEdit.cerrar();
            }

            if (sesion != null && sesion.getAttribute("arbolEstudiantes") != null) {
                ArbolABB arbol = (ArbolABB) sesion.getAttribute("arbolEstudiantes");
                arbol.eliminar(matricula);
                arbol.insertar(actualizado);
            }

            request.setAttribute("mensaje", actualizadoOK ? "Estudiante modificado correctamente." : "No se pudo modificar el estudiante.");

            // Recargar lista y panel
            ConexionBD cnxLoad = new ConexionBD();
            List<Estudiante> estudiantes = new ArrayList<>();
            if (cnxLoad.conectar()) {
                estudiantes = cnxLoad.obtenerEstudiantes();
                cnxLoad.cerrar();
            }
            request.setAttribute("estudiantes", estudiantes);
            request.getRequestDispatcher("panelAdministrador.jsp").forward(request, response);
            return;
        }

        else if ("/eliminar".equals(path)) {
            // 1. Recuperar la matrícula del estudiante a eliminar
            int matricula = Integer.parseInt(request.getParameter("matricula"));
            // 2. Eliminar el estudiante en la base de datos
            ConexionBD conexion = new ConexionBD();
            boolean eliminadoBD = false;
            if (conexion.conectar()) {
                eliminadoBD = conexion.eliminarEstudiante(matricula);
                conexion.cerrar();
            }
            // 3. Mensaje de retroalimentación
            if (eliminadoBD) {
                request.setAttribute("mensaje", "Estudiante eliminado correctamente.");
            } else {
                request.setAttribute("mensaje", "No se pudo eliminar el estudiante.");
            }
            // 4. Recargar listado actualizado y reenviar al panel
            List<Estudiante> estudiantes = new ArrayList<>();
            ConexionBD cnx2 = new ConexionBD();
            if (cnx2.conectar()) {
                estudiantes = cnx2.obtenerEstudiantes();
                cnx2.cerrar();
            }
            request.setAttribute("estudiantes", estudiantes);
            request.getRequestDispatcher("panelAdministrador.jsp").forward(request, response);
        }
            }

        

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        if (sesion == null || sesion.getAttribute("administrador") == null) {
            response.sendRedirect("loginAdministrador.jsp");
            return;
        }
        request.getRequestDispatcher("panelAdministrador.jsp").forward(request, response);
    }
}