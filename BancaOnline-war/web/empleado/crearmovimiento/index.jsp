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
        <title>Crear Movimiento</title>
        
        <%      
            
            List<String []> breadcrumb = new ArrayList<String []>();    
            breadcrumb.add(new String[] { "/BancaOnline/", "Inicio" } );
            breadcrumb.add(new String[] { "/BancaOnline/login", "Login" });
            breadcrumb.add(new String[] { "/BancaOnline/empleado", "Empleado" });
            breadcrumb.add(new String[] { "/BancaOnline/movimientosempleado", "Movimientos" });
            breadcrumb.add(new String[] { "#", "CrearMovimiento" }); 
        
            String [] datos;
            datos = (String [])request.getAttribute("datos");
        %>
    </head>
    <body class="">
        <%@ include file="/WEB-INF/jspf/cabecera.jspf"%>
        <br/>
        
        <form class="container" method="post" action="CrearMovimiento">
            
            <span class="row">
                Datos Bancarios:
            </span>
            <div class="row">
                <table class="">
                    <tr>
                       <td></td>
                       <td><b>ctrl IBAN(*)</b>&emsp;</td>
                       <td>&emsp;<b>Entidad(*)</b>&emsp;</td>
                       <td>&emsp;<b>Oficina(*)</b>&emsp;</td>
                       <td>&emsp;<b>ctrl CCC(*)</b>&emsp;</td>
                       <td>&emsp;<b>NC(*)</b></td>
                    </tr>

                    <tr>
                        <td><b><%=datos[0]%></b></td>
                        <td><input type="text" class="form-control" name="ctrliban" size="4" value="<%=datos[1]%>"></td>
                        <td><input type="text" class="form-control" name="entidad" size="4" value="<%=datos[2]%>"></td>
                        <td><input type="text" class="form-control" name="oficina" size="4" value="<%=datos[3]%>"></td>
                        <td><input type="text" class="form-control" name="ctrlCCC" size="2" value="<%=datos[4]%>"></td>
                        <td><input type="text" class="form-control" name="nc" size="10" value="<%=datos[5]%>"></td>
                    </tr>

                    <tr>
                        <td><b>Importe(*)</b>&emsp;</td>
                        <td><input min="1" step="any" name = "importe"/>&emsp;</td>
                        <td>
                            <br>
                                <select name="divisa">
                                    <option value="EUR">Euros</option>
                                    <option value="USD">Dólares</option>
                                    <option value="MXN">Pesos Mexicanos</option>
                                    <option value="JPY">Yen</option>
                                    <option value="BTC">Bitcoin</option>
                                </select>
                            <br/><br/>
                        </td>
                    </tr>
                    <tr>
                        <td><b>Movimiento(*)</b>&emsp;</td>
                        <td>
                           <select name="movimiento">
                                <option value="ingreso">Ingresar</option>
                                <option value="retirada">Retirar</option>
                            </select>
                        </td>
                    </tr>
                    <tr><td></td></tr>
                    <tr>
                        <td>Concepto:</td>
                        <td><input type="text" name="concepto"/></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </table> 
            </div>
            <br/>
            <div class="row">
                        <button class=" mx-auto col-3 btn btn-primary">Crear</button>
            </div>
            
        </form>
        
    </body>
</html>

