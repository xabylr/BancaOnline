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
    
    
    <body  style="background-color: #fff; background-image: none">
        
<%@ include file="/WEB-INF/jspf/cabecera.jspf"%>
<%@ include file="/WEB-INF/jspf/movimientos.jspf"%>
<div class="container">
    <div class="row d-flex justify-content-center">
        <form method="post" action="datosCrearMovimiento">
            <input type="hidden" name="idCliente" value="<%=cliente.getDni()%>"/>
            <button class="btn-primary">Crear Movimiento</button>
        </form>
    </div>
        
</div>
    </body>
</html>
