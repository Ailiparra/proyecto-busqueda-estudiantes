
package com.universidad.dao;

import com.universidad.modelo.Administrador;
import java.sql.*;

/**
 *
 * @author jeto
 */
public class AdministradorDAO {
    // Valida las credenciales de un administrador en la base de datos
    public boolean validarAdministrador(String usuario, String contrasena) {
        ConexionBD conexionBD = new ConexionBD();
        boolean esValido = false;
        if (conexionBD.conectar()) {
            String sql = "SELECT * FROM administrador WHERE usuario = ? AND contrasena = ?";
            try (PreparedStatement stmt = conexionBD.getConexion().prepareStatement(sql)) {
                stmt.setString(1, usuario);
                stmt.setString(2, contrasena);
                try (ResultSet rs = stmt.executeQuery()) {
                    esValido = rs.next(); // Si hay resultado, las credenciales son correctas
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conexionBD.cerrar();
            }
        }
        return esValido;
    }

    // Inserta un nuevo administrador (opcional, por si quieres crear nuevos admins)
    public boolean insertarAdministrador(Administrador admin) {
        ConexionBD conexionBD = new ConexionBD();
        boolean resultado = false;
        if (conexionBD.conectar()) {
            String sql = "INSERT INTO administrador (usuario, contrasena) VALUES (?, ?)";
            try (PreparedStatement stmt = conexionBD.getConexion().prepareStatement(sql)) {
                stmt.setString(1, admin.getUsuario());
                stmt.setString(2, admin.getContrasena());
                int filas = stmt.executeUpdate();
                resultado = (filas > 0);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conexionBD.cerrar();
            }
        }
        return resultado;
    }

    // Obtiene un administrador por usuario (opcional, útil para gestión)
    public Administrador obtenerAdministradorPorUsuario(String usuario) {
        ConexionBD conexionBD = new ConexionBD();
        Administrador admin = null;
        if (conexionBD.conectar()) {
            String sql = "SELECT * FROM administrador WHERE usuario = ?";
            try (PreparedStatement stmt = conexionBD.getConexion().prepareStatement(sql)) {
                stmt.setString(1, usuario);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        admin = new Administrador(
                            rs.getString("usuario"),
                            rs.getString("contrasena")
                        );
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conexionBD.cerrar();
            }
        }
        return admin;
    }
}
