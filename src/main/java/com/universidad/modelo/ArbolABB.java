
package com.universidad.modelo;

import java.util.List;
import java.util.ArrayList;
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
        System.out.println("Resultado de búsqueda: " + resultado);
        if (resultado != null) {
            return resultado.getEstudiante();
        }
        return null;
    }

    private NodoABB buscarRecursivo(NodoABB actual, int matricula) {
        if (actual == null) {
            return null;
        }
        System.out.println("Visitando nodo con matrícula: " + actual.getEstudiante().getMatricula());
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
    public List<Estudiante> listarTodos() {
        List<Estudiante> lista = new ArrayList<>();
        recorrerInOrdenAgregar(raiz, lista);
        return lista;
    }

    private void recorrerInOrdenAgregar(NodoABB nodo, List<Estudiante> lista) {
        if (nodo != null) {
            recorrerInOrdenAgregar(nodo.getIzquierdo(), lista);
            lista.add(nodo.getEstudiante());
            recorrerInOrdenAgregar(nodo.getDerecho(), lista);
    }
    }
    public boolean eliminar(int matricula) {
    NodoABB eliminado = null;
    raiz = eliminarRecursivo(raiz, matricula);
    // Opcional: puedes retornar true si se encontró y eliminó, false si no existía
    return eliminado != null;
}

    private NodoABB eliminarRecursivo(NodoABB nodo, int matricula) {
        if (nodo == null) {
            return null;
        }

        // Buscar el nodo
        if (matricula < nodo.getEstudiante().getMatricula()) {
            nodo.setIzquierdo(eliminarRecursivo(nodo.getIzquierdo(), matricula));
        } else if (matricula > nodo.getEstudiante().getMatricula()) {
            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), matricula));
        } else {
            // Nodo encontrado
            // Caso 1: Nodo sin hijos
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                return null;
            }
            // Caso 2: Nodo con un solo hijo
            else if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();
            } else if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo();
            }
            // Caso 3: Nodo con dos hijos
            else {
                // Buscar el nodo menor del subárbol derecho (sucesor inorden)
                NodoABB menorDerecha = nodo.getDerecho();
                while (menorDerecha.getIzquierdo() != null) {
                    menorDerecha = menorDerecha.getIzquierdo();
                }
                // Reemplazar el nodo actual con los datos del sucesor
                nodo.setEstudiante(menorDerecha.getEstudiante());
                // Eliminar el sucesor inorden
                nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), menorDerecha.getEstudiante().getMatricula()));
            }
        }
        return nodo;
    }
}
