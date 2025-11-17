package com.universidad.modelo;

public class Estudiante {

    private String matricula;
    private String dni;
    private String nombre;
    private String apellido;
    private String programa;
    private int semestre;
    private String correo;

    public Estudiante(String matricula, String dni, String nombre, String apellido, String programa, int semestre,
            String correo) {
        this.matricula = matricula;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.programa = programa;
        this.correo = correo;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    @Override
    public String toString() {
        return "Matricula: " + matricula + " | DNI: " + dni + " | Nombre: " + nombre + " " + apellido + " | Programa: "
                + programa + " | Semestre: " + semestre + " | Correo: " + correo;
    }

}
