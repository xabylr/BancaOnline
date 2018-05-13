<%-- 
    Document   : index
    Created on : 08-abr-2018, 17:18:48
    Author     : Abel
--%>

<%@page import="sesion.IbanCC"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="sesion.ClienteFacade"%>
<%@page import="entidad.Cuentacorriente"%>
<%@page import="entidad.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/cargar_bootstrap.jspf"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <%
            Cliente cliente = (Cliente)request.getAttribute("cliente");
            List<Movimiento> movimientos = (List<Movimiento>)request.getAttribute("movimientos");
        %>
        
        <title>Movimientos de un usuario</title>
        
        <link rel="stylesheet" type="text/css" href="styles.css">

        <%  List<String []> breadcrumb = new ArrayList<String []>();    
            breadcrumb.add(new String[] { "/BancaOnline/", "Inicio" } );
            breadcrumb.add(new String[] { "/BancaOnline/login", "Login" });
            breadcrumb.add(new String[] { "/BancaOnline/empleado", "Empleado" });
            breadcrumb.add(new String[] { "#", "Movimientos" }); 
        %>
    </head>
    <body>
        
<%@ include file="/WEB-INF/jspf/cabecera.jspf"%>
<%@ include file="/WEB-INF/jspf/movimientos.jspf"%>
    
<form method="post" action="empleado/crearmovimiento">
    
    <input type="hidden" name="idCliente" value="<%=cliente.getDni()%>"/>
    <button>Crear Movimiento</button>
    
</form>

    </body>
</html>
