<%--    
    Document   : altaUsuario
    Created on : 22-mar-2018, 11:49:51
    Author     : Stefan
--%>

<%@page import="entidad.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/cargar_bootstrap.jspf"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <%
            Empleado empleado = (Empleado)session.getAttribute("usuario");
        %>
        
        
        <title>Alta de usuario</title>
          
        <link rel="stylesheet" type="text/css" href="styles.css">
 
        <%  List<String []> breadcrumb = new ArrayList<String []>();    
            breadcrumb.add(new String[] { "/BancaOnline/", "Inicio" } );
            breadcrumb.add(new String[] { "/BancaOnline/empleado", "Empleado" });
            breadcrumb.add(new String[] { "#", "Alta Usuario" });
 
        %>
        
        
    </head>
    <body>
        
        <%@ include file="/WEB-INF/jspf/cabecera.jspf"%>
        
        <div class="container"> 
            <form method="post" action="../AnadirUsuario">
                <div class="row">
                    <div class="form-group col-6 mx-auto">
                        <label>DNI:</label>
                        <input type="text" class="form-control" id="dni" name="dni">
                    </div>
                </div>
                
                <div class="row">
                    <div class="form-group col-6 mx-auto">
                        <label>Nombre:</label>
                        <input type="text" class="form-control" id="nombre" name="nombre">
                    </div>
                 </div>
               
                <div class="row">
                    <div class="form-group col-6 mx-auto">
                        <label>Apellidos:</label>
                        <input type="text" class="form-control" id="apellidos" name="apellidos">
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-6 mx-auto">
                        <label>Numero de oficina:</label>
                        <input type="text" class="form-control" id="oficina" name="oficina">
                    </div>
                </div>
                            
                <div class="row">
                    <div class="alert alert-warning col-6 mx-auto">
                        <strong>¡Precaución!</strong> Solo se modificará el número de oficina en caso de trabajar en una sucursal, no en la central.
                    </div>
                </div>
                
                <div class="row">
                    <button class="col-3 mx-auto"><b>A&ntilde;adir</b></button>
                </div>
                 
             </form>

        </div> 
        
        
        <form method="post" action="../VerClientes">
            <button>Ver Clientes</button>
        </form>
        
        
    </body>
</html>
