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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <%
            Empleado empleado = (Empleado)session.getAttribute("usuario");
        %>
        
        
        <title>Alta de usuario</title>
          
        <link rel="stylesheet" type="text/css" href="styles.css">
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        
        
    </head>
    <body>
        
        <jsp:include page="/WEB-INF/jspf/cabecera.jspf"/>
        <div class="d-flex">
            <ul class="breadcrumb list-inline mx-auto justify-content-center">
                /<li ><a href="/BancaOnline/usuario">Inicio</a></li>/
                <li ><a href="#">Alta Usuario</a></li>
            </ul>
        </div>
        
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
                        <strong>¡Precaucion!</strong> Solo se modificara el numero de oficina en caso de trabajar en una sucursal, no en la central.
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
