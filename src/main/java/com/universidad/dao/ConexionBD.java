
package com.universidad.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.universidad.modelo.Estudiante;

/**
 *
 * @author Ailiparra
 */
public class ConexionBD {
    private Connection conexion;

    // Establece la conexión usando el pool del contenedor
    public boolean conectar() {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/EstudiantesDB");
            conexion = ds.getConnection();
            return true;
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            conexion = null;
            return false;
        }
    }
    
    // Entrega la referencia a la conexión activa
    public Connection getConexion() {
        return conexion;
    }

    // Inserta un nuevo estudiante en la BD
    public boolean insertarEstudiante(Estudiante est) {
        String sql = "INSERT INTO estudiante (matricula, dni, nombre, apellido, programa, semestre, correo, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, est.getMatricula());
            stmt.setInt(2, est.getDni());
            stmt.setString(3, est.getNombre());
            stmt.setString(4, est.getApellido());
            stmt.setString(5, est.getPrograma());
            stmt.setInt(6, est.getSemestre());
            stmt.setString(7, est.getCorreo());
            stmt.setString(8, est.getPassword());
            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Actualiza datos de un estudiante existente
    public boolean actualizarEstudiante(Estudiante est) {
        String sql = "UPDATE estudiante SET dni = ?, nombre = ?, apellido = ?, programa = ?, semestre = ?, correo = ? WHERE matricula = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, est.getDni());
            stmt.setString(2, est.getNombre());
            stmt.setString(3, est.getApellido());
            stmt.setString(4, est.getPrograma());
            stmt.setInt(5, est.getSemestre());
            stmt.setString(6, est.getCorreo());
            stmt.setInt(7, est.getMatricula());
            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Elimina un estudiante por matrícula
    public boolean eliminarEstudiante(int matricula) {
        String sql = "DELETE FROM estudiante WHERE matricula = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, matricula);
            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Obtiene todos los estudiantes de la BD
    public ArrayList<Estudiante> obtenerEstudiantes() {
        ArrayList<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiante";
        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Estudiante est = new Estudiante(
                    rs.getInt("matricula"),
                    rs.getInt("dni"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("programa"),
                    rs.getInt("semestre"),
                    rs.getString("correo"),
                    rs.getString("password")
                );
                lista.add(est);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public Estudiante loginEstudiante(String correo, String password) {
        String sql = "SELECT * FROM estudiante WHERE correo = ? AND password = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, correo);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Estudiante(
                        rs.getInt("matricula"),
                        rs.getInt("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("programa"),
                        rs.getInt("semestre"),
                        rs.getString("correo"),
                        rs.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Obtiene una consulta de estudiante por matrícula específica
    public Estudiante obtenerEstudiantePorMatricula(int matricula) {
        String sql = "SELECT * FROM estudiante WHERE matricula = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, matricula);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Estudiante(
                        rs.getInt("matricula"),
                        rs.getInt("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("programa"),
                        rs.getInt("semestre"),
                        rs.getString("correo"),
                        rs.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Genera una matrícula nueva automáticamente (max matrícula + 1)
    public int generarMatricula() {
        String sql = "SELECT MAX(matricula) AS maxMatricula FROM estudiante";
        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("maxMatricula") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    // Cierra la conexión a la BD
    public void cerrar() {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
