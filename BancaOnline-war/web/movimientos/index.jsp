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
            Cuentacorriente cuenta = cliente.getCuenta();
        %>
        
        <title>Movimientos de un usuario</title>
        
        <link rel="stylesheet" type="text/css" href="styles.css">

        <%  List<String []> breadcrumb = new ArrayList<String []>();    
            breadcrumb.add(new String[] { "/BancaOnline/", "Inicio" } );
            breadcrumb.add(new String[] { "/BancaOnline/login", "Login" });
            breadcrumb.add(new String[] { "/BancaOnline/usuario", "Usuario" });
            breadcrumb.add(new String[] { "#", "Movimientos" }); 
        %>
    </head>
    <body>
        
<%@ include file="/WEB-INF/jspf/cabecera.jspf"%>
        
        <div class="container"> 
            <div class="row">
                    <h5 class="col-3">
                        Usuario: <%=cliente.getNombre() + " " + cliente.getApellidos()%>
                    </h5>
                    
                    <h5 class="col-4">
                        IBAN: <%=new IbanCC(cuenta)%>
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
