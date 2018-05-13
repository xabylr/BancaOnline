<%-- 
    Document   : index
    Created on : 13-may-2018, 17:36:55
    Author     : Jose Santos
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/WEB-INF/jspf/cargar_bootstrap.jspf"%>
        <title>JSP Page</title>
        
        <%  List<String []> breadcrumb = new ArrayList<String []>();    
            breadcrumb.add(new String[] { "/BancaOnline/", "Inicio" } );
            breadcrumb.add(new String[] { "/BancaOnline/login", "Login" });
            breadcrumb.add(new String[] { "/BancaOnline/empleado", "Empleado" });
            breadcrumb.add(new String[] { "/BancaOnline/movimientosempleado", "Movimientos" });
            breadcrumb.add(new String[] { "#", "CrearMovimiento" }); 
        %>
    </head>
    <body>
        <%@ include file="/WEB-INF/jspf/cabecera.jspf"%>
        
        <h1>Hello World!</h1>
        
        <form method="post" action="CrearMovimiento">
            
            
            <button>Crear</button>
            
        </form>
        
    </body>
</html>
