
package com.universidad.modelo;

import com.universidad.modelo.Estudiante;

public class ArbolABB {

    private NodoABB raiz;

    public ArbolABB() {
        this.raiz = null;
    }

    public void insertar(Estudiante estudiante) {
        raiz = insertarRecursivo(raiz, estudiante);
    }

    private NodoABB insertarRecursivo(NodoABB nodo, Estudiante estudiante) {
        if (nodo == null) {
            return new NodoABB(estudiante);
        }
        // compareTo sitve para comparar dos objectos del mismo tipo
        int comparacion = estudiante.getDni().compareTo(nodo.getEstudiante().getDni());

        if (comparacion < 0) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), estudiante));
        } else if (comparacion > 0) {
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), estudiante));
        } else {
            System.out.println("El estudiante con DNI: " + estudiante.getDni() + "ya existe");
        }

        return nodo;
    }

    public Estudiante buscar(String dni) {
        return buscarRecursivo(raiz, dni);
    }

    public Estudiante buscarRecursivo(NodoABB nodo, String dni) {
        if (nodo == null) {
            return null;
        }

        int comparacion = dni.compareTo(nodo.getEstudiante().getDni());

        if (comparacion == 0) {
            return nodo.getEstudiante();
        } else if (comparacion < 0) {
            return buscarRecursivo(nodo.getIzquierdo(), dni);
        } else {
            return buscarRecursivo(nodo.getDerecho(), dni);
        }

    }

    public void eliminar(String dni) {
        raiz = eliminarRecursivo(raiz, dni);
    }

    public NodoABB eliminarRecursivo(NodoABB nodo, String dni) {

        if (nodo == null) {
            return null;
        }

        int comparacion = dni.compareTo(nodo.getEstudiante().getDni());

        if (comparacion < 0) {

            nodo.setIzquierdo(eliminarRecursivo(nodo.getIzquierdo(), dni));
        } else if (comparacion > 0) {

            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), dni));
        } else {

            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                return null;
            }
            // Caso 2: Nodo con un solo hijo
            if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();
            }
            if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo();
            }
            // Caso 3: Nodo con dos hijos
            NodoABB sucesor = encontrarMinimo(nodo.getDerecho());
            nodo.setEstudiante(sucesor.getEstudiante());
            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), sucesor.getEstudiante().getDni()));
        }

        return nodo;
    }

    public NodoABB encontrarMinimo(NodoABB nodo) {
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo;
    }
}
