
package com.universidad.modelo;

/**
 *
 * @author Ailiparra
 */
public class Estudiante {
    // Atributos principales
    private int matricula;
    private String dni;
    private String nombre;
    private String apellido;
    private String programa;
    private int semestre;
    private String correo;

    // Constructor vacío
    public Estudiante() { }

    // Constructor completo
    public Estudiante(int matricula, String dni, String nombre, String apellido, String programa, int semestre, String correo) {
        this.matricula = matricula;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.programa = programa;
        this.semestre = semestre;
        this.correo = correo;
    }

    // Getters y Setters
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Método toString (opcional, útil para depuración)
    @Override
    public String toString() {
        return "Estudiante [matricula=" + matricula + ", dni=" + dni + 
               ", nombre=" + nombre + ", apellido=" + apellido +
               ", programa=" + programa + ", semestre=" + semestre +
               ", correo=" + correo + "]";
    }
}

