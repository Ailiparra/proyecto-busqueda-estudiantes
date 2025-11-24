<%-- 
    Document   : panelAdministrador.jsp
    Created on : 12/11/2025, 6:11:34?p. m.
    Author     : Ailiparra
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.universidad.modelo.Estudiante" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOC<!DOCTYPE html>
<html>
<head>
    <title>Panel Administrador</title>
    <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <div class="card">
        <div class="card-header bg-info text-white">
            <div class="row">
                <div class="col-md-6">
                    <h4>Buscar Estudiantes</h4>
                </div>
                <div class="col-md-6 text-right">
                    <form class="form-inline" method="post" action="buscar">
                        <input name="matriculaBuscar" class="form-control mr-2" type="text" placeholder="Matrícula">
                        <button type="submit" class="btn btn-primary">Buscar</button>
                    </form>
                    <form class="form-inline" method="post" action="listar">
                        <button type="submit" class="btn btn-success ml-2">Listar Todos</button>
                    </form>
                    <form method="get" action="index.html" class="form-inline d-inline">
                        <button type="submit" class="btn btn-danger ml-2">Salir</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="card-body">
            <c:if test="${not empty mensaje}">
                <div class="alert alert-danger">${mensaje}</div>
            </c:if>

            <table class="table table-bordered table-hover">
                <thead class="thead-light">
                    <tr>
                        <th>Matrícula</th>
                        <th>DNI</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Programa</th>
                        <th>Semestre</th>
                        <th>Correo</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="est" items="${estudiantes}">
                    <tr>
                        <td>${est.matricula}</td>
                        <td>${est.dni}</td>
                        <td>${est.nombre}</td>
                        <td>${est.apellido}</td>
                        <td>${est.programa}</td>
                        <td>${est.semestre}</td>
                        <td>${est.correo}</td>
                        <td>
                            <form method="post" action="modificar" class="d-inline">
                                <input type="hidden" name="accion" value="modificar">
                                <input type="hidden" name="matricula" value="${est.matricula}">
                                <button type="submit" class="btn btn-warning btn-sm" title="Modificar">
                                    <span>&#9998;</span>
                                </button>
                            </form>
                            <form method="post" action="eliminar" class="d-inline" onsubmit="return confirm('¿Seguro que desea eliminar este estudiante?');">
                                <input type="hidden" name="accion" value="eliminar">
                                <input type="hidden" name="matricula" value="${est.matricula}">
                                <button type="submit" class="btn btn-danger btn-sm" title="Eliminar">
                                    <span>&#128465;</span>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>