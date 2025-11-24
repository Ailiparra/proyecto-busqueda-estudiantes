
package com.universidad.modelo;

/**
 *
 * @author Ailiparra
 */
public class Estudiante {
    // Atributos principales
    private int matricula;
    private int dni;
    private String nombre;
    private String apellido;
    private String programa;
    private int semestre;
    private String correo;
    private String password;

    // Constructor vacío
    public Estudiante() { }

    // Constructor completo
    public Estudiante(int matricula, int dni, String nombre, String apellido, String programa, int semestre, String correo, String password) {
        this.matricula = matricula;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.programa = programa;
        this.semestre = semestre;
        this.correo = correo;
        this.password = password;
    }

    public Estudiante(int matricula, String programa, int semestre, String correo, String password) {
        this.matricula = matricula;
        this.programa = programa;
        this.semestre = semestre;
        this.correo = correo;
        this.password = password;
    }
    

    // Getters y Setters
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
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
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Método toString (opcional, útil para depuración)
    @Override
    public String toString() {
        return "Estudiante [matricula=" + matricula + ", dni=" + dni + 
               ", nombre=" + nombre + ", apellido=" + apellido +
               ", programa=" + programa + ", semestre=" + semestre +
               ", correo=" + correo + ",+]";
        
    }
}

