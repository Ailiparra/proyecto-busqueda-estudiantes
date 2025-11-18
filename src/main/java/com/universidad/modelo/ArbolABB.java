
package com.universidad.modelo;

/**
 *
 * @author Ailiparra
 */
public class ArbolABB {
    private NodoABB raiz;

    // Constructor vacío
    public ArbolABB() {
        this.raiz = null;
    }

    // Insertar estudiante en el ABB
    public void insertar(Estudiante estudiante) {
        raiz = insertarRecursivo(raiz, estudiante);
    }

    private NodoABB insertarRecursivo(NodoABB actual, Estudiante estudiante) {
        if (actual == null) {
            return new NodoABB(estudiante);
        }

        if (estudiante.getMatricula() < actual.getEstudiante().getMatricula()) {
            actual.setIzquierdo(insertarRecursivo(actual.getIzquierdo(), estudiante));
        } else if (estudiante.getMatricula() > actual.getEstudiante().getMatricula()) {
            actual.setDerecho(insertarRecursivo(actual.getDerecho(), estudiante));
        }
        // Si la matrícula ya existe, no se inserta (alternativa: actualizar el nodo)
        return actual;
    }

    // Buscar estudiante por matrícula
    public Estudiante buscar(int matricula) {
        NodoABB resultado = buscarRecursivo(raiz, matricula);
        if (resultado != null) {
            return resultado.getEstudiante();
        }
        return null;
    }

    private NodoABB buscarRecursivo(NodoABB actual, int matricula) {
        if (actual == null) {
            return null;
        }
        if (matricula == actual.getEstudiante().getMatricula()) {
            return actual;
        } else if (matricula < actual.getEstudiante().getMatricula()) {
            return buscarRecursivo(actual.getIzquierdo(), matricula);
        } else {
            return buscarRecursivo(actual.getDerecho(), matricula);
        }
    }

    // Recorrido inorden (útil para listar estudiantes ordenados)
    public void recorrerInOrden() {
        recorrerInOrden(raiz);
    }

    private void recorrerInOrden(NodoABB nodo) {
        if (nodo != null) {
            recorrerInOrden(nodo.getIzquierdo());
            System.out.println(nodo.getEstudiante());
            recorrerInOrden(nodo.getDerecho());
        }
    }
}
