
package com.universidad.modelo;

import com.universidad.modelo.Estudiante;

public class ArbolABB {

    private NodoABB raiz;

    public ArbolABB() {
        this.raiz = null;
    }

    public NodoABB getRaiz() {
        return raiz;
    }

    public void insertar(Estudiante est) {
        raiz = insertarRec(raiz, est);
    }

    // Pendiente
    private NodoABB insertarRec(NodoABB nodo, Estudiante est) {
        return nodo;
    }

    public Estudiante buscar(String matricula) {
        return buscarRec(raiz, matricula);
    }

    // Pendiente
    private Estudiante buscarRec(NodoABB nodo, String matricula) {
        return null;
    }

}
