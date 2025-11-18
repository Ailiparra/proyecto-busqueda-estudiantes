package com.universidad.negocio;

import com.universidad.modelo.Estudiante;
import com.universidad.modelo.ArbolABB;
import com.universidad.dao.ConexionBD;
import java.util.ArrayList;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ailiparra
 */
public class SistemaRegistro {
    private ConexionBD conexion;
    private ArbolABB arbolEstudiantes;

    // Constructor: inicializa conexión y ABB
    public SistemaRegistro() {
        conexion = new ConexionBD();
        arbolEstudiantes = new ArbolABB();
        cargarEstudiantesEnABB();
    }

    // Carga todos los estudiantes desde la BD y los inserta en el ABB
    public void cargarEstudiantesEnABB() {
        if (conexion.conectar()) {
            ArrayList<Estudiante> lista = conexion.obtenerEstudiantes();
            for (Estudiante est : lista) {
                arbolEstudiantes.insertar(est);
            }
            conexion.cerrar();
        }
    }

    // Registra un nuevo estudiante (en BD y ABB)
    public boolean registrarEstudiante(Estudiante nuevo) {
        boolean resultado = false;
        if (conexion.conectar()) {
            resultado = conexion.insertarEstudiante(nuevo);
            conexion.cerrar();
            if (resultado) {
                arbolEstudiantes.insertar(nuevo);
            }
        }
        return resultado;
    }

    // Buscar estudiante por matrícula usando ABB
    public Estudiante buscarPorMatricula(int matricula) {
        return arbolEstudiantes.buscar(matricula);
    }

    // Actualizar estudiante (BD y ABB)
    public boolean actualizarEstudiante(Estudiante actualizado) {
        boolean resultado = false;
        if (conexion.conectar()) {
            resultado = conexion.actualizarEstudiante(actualizado);
            conexion.cerrar();
            if (resultado) {
                // Opcional: recargar ABB para garantizar sincronía
                arbolEstudiantes = new ArbolABB();
                cargarEstudiantesEnABB();
            }
        }
        return resultado;
    }

    // Eliminar estudiante (en BD y ABB)
    public boolean eliminarEstudiante(int matricula) {
        boolean resultado = false;
        if (conexion.conectar()) {
            resultado = conexion.eliminarEstudiante(matricula);
            conexion.cerrar();
            if (resultado) {
                // Opcional: recargar ABB para garantizar sincronía
                arbolEstudiantes = new ArbolABB();
                cargarEstudiantesEnABB();
            }
        }
        return resultado;
    }

    // Obtiene todos los estudiantes (para listar en la vista)
    public ArrayList<Estudiante> obtenerTodos() {
        ArrayList<Estudiante> lista = new ArrayList<>();
        arbolEstudiantes.recorrerInOrden(); // Este método puede imprimir, pero aquí sugerimos devolver una lista
        // Redefinir recorrerInOrden para retornar ArrayList<Estudiante> si así lo requieres
        return lista;
    }
    
    // Genera la siguiente matrícula disponible usando la BD (retorna int)
    public int generarMatricula() {
        int nuevaMatricula = 1;
        if (conexion.conectar()) {
            nuevaMatricula = conexion.generarMatricula();
            conexion.cerrar();
    }
    return nuevaMatricula;
}
}
