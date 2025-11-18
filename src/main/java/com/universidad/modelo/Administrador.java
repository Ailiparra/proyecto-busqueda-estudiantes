
package com.universidad.modelo;

/**
 *
 * @author Ailiparra
 */
public class Administrador {

    // Atributos principales
    private String usuario;
    private String contrasena;

    // Constructor vacío
    public Administrador() { }

    // Constructor completo
    public Administrador(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // Método auxiliar para depuración (opcional)
    @Override
    public String toString() {
        return "Administrador [usuario=" + usuario + "]";
    }
}
