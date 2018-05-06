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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <%
            Cliente cliente = (Cliente)session.getAttribute("cliente");
            Cuentacorriente cuenta = cliente.getCuenta();
        %>
        
        <title>Movimientos de un usuario</title>
        
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
             <li><a href="#">Usuario</a></li>/
             <li>Movimientos</li>
            </ul>
</div>
        
        <div class="container"> 
            <div class="row">
                    <h5 class="col-3">
                        Usuario: <%=cliente.getNombre() + " " + cliente.getApellidos()%>
                    </h5>
                    
                    <h5 class="col-4">
                        IBAN: <%=123%>
                    </h5>
            
                    <h5 class="col-5 text-right">
                        <a href="#"><small>Atrás</small></a>
                    </h5>
            </div>
                    
                  <table class="table table-bordered">
    <thead>
      <tr>
        <th>Concepto</th>
        <th>Fecha Completado</th>
        <th>Fecha</th>
        <th>Emisor</th>
        <th>Receptor</th>
        <th>Cantidad</th>
        <th>Saldo</th>
      </tr>
    </thead>
    <tbody>
        
        <!--
        
        <% 
            //for(Movimiento mov : cuenta.getMovimientos()){
            
        %>
            <tr>
                <td>Putas</td>
                <td>asd</td>
                <td>sdfdfs</td>
                <td>sdffds</td>
                <td>asd</td>
                <td>sdfdfs</td>
                <td>sdfdfs</td>
            </tr>
         
        <% 
           // }
        %>
        
        -->
        
      <tr>
        <td>Putas</td>
        <td>asd</td>
        <td>sdfdfs</td>
        <td>sdffds</td>
        <td>asd</td>
        <td>sdfdfs</td>
        <td>sdfdfs</td>
      </tr>
      <tr>
        <td>Notas 10 UMA</td>
        <td>asd</td>
        <td>sdfdfs</td>
        <td>sdffds</td>
        <td>asd</td>
        <td>sdfdfs</td>
        <td>sdfdfs</td>
      </tr>
    </tbody>
  </table>  
                    
        </div>
        
    </body>
</html>
