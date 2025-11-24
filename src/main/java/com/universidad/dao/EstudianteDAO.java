/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.universidad.dao;

import com.universidad.modelo.Estudiante;
import java.sql.*;
import java.util.ArrayList;

public class EstudianteDAO {

    private Connection obtenerConexion() throws Exception {
        // Configuración JNDI usando el pool definido en context.xml
        javax.naming.Context ctx = new javax.naming.InitialContext();
        javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup("java:comp/env/jdbc/EstudiantesDB");
        return ds.getConnection();
    }

    public boolean insertarEstudiante(Estudiante est) {
        boolean resultado = false;
        try (Connection conexion = obtenerConexion()) {
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
                resultado = (filas > 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public boolean actualizarEstudiante(Estudiante est) {
        boolean resultado = false;
        try (Connection conexion = obtenerConexion()) {
            String sql = "UPDATE estudiante SET dni=?, nombre=?, apellido=?, programa=?, semestre=?, correo=?, password=? WHERE matricula=?";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setInt(1, est.getDni());
                stmt.setString(2, est.getNombre());
                stmt.setString(3, est.getApellido());
                stmt.setString(4, est.getPrograma());
                stmt.setInt(5, est.getSemestre());
                stmt.setString(6, est.getCorreo());
                stmt.setString(7, est.getPassword());
                stmt.setInt(8, est.getMatricula());
                int filas = stmt.executeUpdate();
                resultado = (filas > 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public boolean eliminarEstudiante(int matricula) {
        boolean resultado = false;
        try (Connection conexion = obtenerConexion()) {
            String sql = "DELETE FROM estudiante WHERE matricula=?";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setInt(1, matricula);
                int filas = stmt.executeUpdate();
                resultado = (filas > 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public ArrayList<Estudiante> obtenerEstudiantes() {
        ArrayList<Estudiante> lista = new ArrayList<>();
        try (Connection conexion = obtenerConexion()) {
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
                    System.out.println("Add: " + est);
                }
                System.out.println("Total en DAO: " + lista.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Estudiante obtenerEstudiantePorMatricula(int matricula) {
        Estudiante est = null;
        try (Connection conexion = obtenerConexion()) {
            String sql = "SELECT * FROM estudiante WHERE matricula=?";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setInt(1, matricula);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        est = new Estudiante(
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return est;
    }

    public int generarMatricula() {
        int nuevaMatricula = 1;
        try (Connection conexion = obtenerConexion()) {
            String sql = "SELECT MAX(matricula) AS maxMatricula FROM estudiante";
            try (PreparedStatement stmt = conexion.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nuevaMatricula = rs.getInt("maxMatricula") + 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nuevaMatricula;
    }
    public Estudiante loginEstudiante(String correo, String password) {
    String sql = "SELECT * FROM estudiante WHERE correo = ? AND password = ?";
    try (Connection conexion = obtenerConexion();
         PreparedStatement stmt = conexion.prepareStatement(sql)) {
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
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

}
