<%-- 
    Document   : perfilEstudiante.jsp
    Created on : 23/11/2025, 8:47:52 a. m.
    Author     : jeto
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.universidad.modelo.Estudiante" %>
<%
    Estudiante est = (Estudiante) request.getAttribute("estudiante");
%>
<h2>Perfil del Estudiante</h2>
<ul>
    <li>Matrícula: <%= est.getMatricula() %></li>
    <li>DNI: <%= est.getDni() %></li>
    <li>Nombre: <%= est.getNombre() %></li>
    <li>Apellido: <%= est.getApellido() %></li>
    <li>Programa: <%= est.getPrograma() %></li>
    <li>Semestre: <%= est.getSemestre() %></li>
    <li>Correo: <%= est.getCorreo() %></li>
</ul>