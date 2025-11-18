
package com.universidad.modelo;

/**
 *
 * @author Ailiparra
 */
public class NodoABB {
    // El valor almacenado en el nodo: un estudiante
    private Estudiante estudiante;
    // Referencia al hijo izquierdo
    private NodoABB izquierdo;
    // Referencia al hijo derecho
    private NodoABB derecho;

    // Constructor con estudiante
    public NodoABB(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.izquierdo = null;
        this.derecho = null;
    }

    // Getters y Setters
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
