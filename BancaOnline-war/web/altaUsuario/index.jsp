<%--    
    Document   : altaUsuario
    Created on : 22-mar-2018, 11:49:51
    Author     : Stefan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alta de usuario</title>
          
        <link rel="stylesheet" type="text/css" href="styles.css">
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        
        
    </head>
    <body>
        
        <div class="container">
         <div class="jumbotron">
          <h1>UMA BANK</h1> 
             <p>Tu dinero es nuestro y nuestro dinero tambien</p> 
              </div>
              <ul class="breadcrumb">
                     <li><a href="#">Inicio</a></li>/
                     <li>Añadir Usuario</li>
                 </ul>
        </div>
        
        <div class="container"> 
            <div class="row">
                    <h5 class="col-4">
                        Hola <%=request.getParameter("nombreUsuario")%>! <a href="#">Cerrar sesion</a>
                    </h5>
            
                    <h5 class="col-8 text-right">
                        <a href="#"><small>Atrás</small></a>
                    </h5>
            </div>
        
                    <div class="content">
                        
                                <form>
                                    <div class="form-group">
                                        <label>DNI:</label>
                                        <input type="text" class="form-control" id="dni" name="dni">
                                    </div>
                                    <div class="form-group">
                                        <label>Nombre:</label>
                                        <input type="text" class="form-control" id="nombre" name="nombre">
                                    </div>
                                    <div class="form-group">
                                        <label>Apellidos:</label>
                                        <input type="text" class="form-control" id="apellidos" name="apellidos">
                                    </div>
                                    <div class="form-group">
                                        <label>Numero de oficina:</label>
                                        <input type="text" class="form-control" id="oficina" name="oficina">
                                    </div>
                                    <div class="alert alert-warning">
                                        <strong>¡Precaucion!</strong> Solo se modificara el numero de oficina en caso de trabajar en una sucursal, no en la central.
                                    </div>
                                    <button>A&ntilde;adir</button> 
                                 </form>
                                
                        
                    </div>     
                    
    </body>
</html>
