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
        
        <%  List<String []> breadcrumb = new ArrayList<String []>();    
            breadcrumb.add(new String[] { "/BancaOnline/", "Inicio" } );
            breadcrumb.add(new String[] { "/BancaOnline/login", "Login" });
            breadcrumb.add(new String[] { "/BancaOnline/empleado/", "Empleado" });
            breadcrumb.add(new String[] { "/BancaOnline/empleado/Clientes/", "Clientes" });
            breadcrumb.add(new String[] { "#", "Editar Usuario" });
        %>
        
        
        <%
            List<Cliente> lista = (List<Cliente>)request.getAttribute("lista");
            Empleado empleado = (Empleado)session.getAttribute("empleado");
        %>
        
        
    </head>
    <body>
        
        <%
            Cliente cliente = (Cliente)request.getAttribute("cliente");
        %>
        <div class="container">
            <%@ include file="/WEB-INF/jspf/cabecera.jspf"%>
            <br/>
            <form method="post" action="editarConfirmarServlet"><br>
                <table>
                    <tr>
                        <td><label>Nombre:&emsp;</label></td>
                        <td> <input type="text" name="nombre" size="10"></td>
                    </tr>
                    
                    <tr>
                        <td><label>Apellidos:&emsp;</label></td>
                        <td><input type="text" name="apellidos" size="20"></td>
                    </tr>
                 
                    <tr>
                        <td><label>Email:&emsp;</label></td>
                        <td><input type="text" name="email" size="20"></td>
                    </tr>
                    
                    <tr>
                        <td><label>Domicilio:&emsp;</label></td>
                        <td><input type="text" name="domicilio" size="30"></td>
                    </tr>
                    
                    <tr>
                        <td><label>Telefono:&emsp;</label></td>
                        <td><input type="text" name="telefono" size="9"></td>
                    </tr>
                    
                </table>
                <br/>
                <input type="hidden" name="idCliente" value="<%=cliente.getDni()%>">
                <button>Aceptar</button>
            </form>
        </div>
    </body>
</html>
