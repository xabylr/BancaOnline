<%-- 
    Document   : index
    Created on : 14-may-2018, 19:08:19
    Author     : Abel
--%>

<%@page import="entidad.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/WEB-INF/jspf/cargar_bootstrap.jspf"%>
        <title>Editar Usuario</title>
    </head>
    <body>
        
        <%
            Cliente cliente = (Cliente)request.getAttribute("cliente");
        %>
        <div class="container">
            <form action="../editarConfirmarServlet"><br>
                <label>Nombre:&emsp;</label><input type="text" name="nombre" size="10"><br><br>
                <label>Apellidos:&emsp;</label><input type="text" name="apellidos" size="20"><br><br>
                <label>Email:&emsp;</label><input type="text" name="email" size="20"><br><br>
                <label>Domicilio:&emsp;</label><input type="text" name="domicilio" size="30"><br><br>
                <label>Telefono:&emsp;</label><input type="text" name="telefono" size="9"><br><br>
                <input type="hidden" name="idCliente" value="<%=cliente.getDni()%>">
                <button>Aceptar</button>
            </form>
        </div>
</html>
