<%-- 
    Document   : index
    Created on : 06-may-2018, 20:05:38
    Author     : Jose Santos
--%>

<%@page import="sesion.ClienteFacade"%>
<%@page import="java.util.List"%>
<%@page import="entidad.Cliente"%>
<%@page import="entidad.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
            List<Cliente> lista = (List<Cliente>)session.getAttribute("lista");
            Empleado empleado = (Empleado)session.getAttribute("empleado");
        %>
        
        <title>Banca Online</title>
    </head>
    <body>
        <h1>Clientes</h1>
        
    <table class="table table-bordered">
    <thead>
      <tr>
        <th>DNI</th>
        <th>Nombre</th>
        <th>Apellidos</th>
        <th>Cuenta</th>
      </tr>
    </thead>
    <tbody>
        
        
        <%
           for(Cliente c : lista){ 
        %>
                
            <tr>
                <td><%=c.getDni()%></td>
                <td><%=c.getNombre()%></td>
                <td><%=c.getApellidos()%></td>
                <td><%=c.getCuenta()%></td>
            </tr>   
        
        
        <%    
           }  
        %>
        </table>
        
    </body>
</html>
