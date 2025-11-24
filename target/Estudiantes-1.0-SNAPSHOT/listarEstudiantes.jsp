<%-- 
    Document   : listarEstudiantes.jsp
    Created on : 12/11/2025, 6:11:00 p. m.
    Author     : Ailiparra
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Listado de Estudiantes</title>
    <meta charset="UTF-8">
    <style>
        body { font-family: Arial, sans-serif; background-color: #f7f7f7; }
        .container { margin: 40px auto; padding: 20px; max-width: 950px; background: #fff; border-radius: 8px; box-shadow: 0px 2px 10px #ddd; }
        h2 { text-align: center; color: #444; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 10px; border: 1px solid #bbb; text-align: center; }
        th { background: #0073e6; color: #fff; }
        tr:nth-child(even) { background: #f2f6fc; }
      
.centro-contenedor {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
}

.boton-salir-custom {
    background: #0a23e7;               /* Azul fuerte */
    color: #fff;                       /* Texto blanco */
    font-size: 2em;                    /* Texto grande */
    font-weight: bold;
    border: 4px solid #fff;            /* Borde blanco. Cambia por #0a23e7 si quieres un borde azul */
    border-radius: 45px;               /* Mucho redondeo */
    padding: 22px 80px;                /* Tamaño grande */
    text-align: center;
    display: inline-block;
    box-shadow: 0px 2px 12px #5557;    /* Sombra suave */
    margin: 32px auto 0 auto;
    transition: background 0.2s, color 0.2s;
    cursor: pointer;
    text-decoration: none;
}

.boton-salir-custom:hover {
    background: #2222ff;
    color: #f2f2f2;
}
    </style>
</head>
<body>
    <div class="container">
        <h2>Listado de Estudiantes</h2>
        <table>
            <thead>
                <tr>
                    <th>Matrícula</th>
                    <th>DNI</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Programa</th>
                    <th>Semestre</th>
                    <th>Correo</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="e" items="${estudiantes}">
                    <tr>
                        <td>${e.matricula}</td>
                        <td>${e.dni}</td>
                        <td>${e.nombre}</td>
                        <td>${e.apellido}</td>
                        <td>${e.programa}</td>
                        <td>${e.semestre}</td>
                        <td>${e.correo}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="centro-contenedor">
    <a href="index.html" class="boton-salir-custom">Salir</a>
    </div>
</body>
</html>