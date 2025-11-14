package com.universidad.modelo;

import com.universidad.modelo.Estudiante;

public class NodoABB {
    private Estudiante estudiante;
    private NodoABB izquierdo;
    private NodoABB derecho;

    public NodoABB(Estudiante estudiante, NodoABB izquierdo, NodoABB derecho) {
        this.estudiante = estudiante;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public NodoABB getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoABB izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoABB getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoABB derecho) {
        this.derecho = derecho;
    }

    

}
