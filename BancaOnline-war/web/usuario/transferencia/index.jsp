<%-- 
    Document   : index
    Created on : 26-abr-2018, 22:07:50
    Author     : javier
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/cargar_bootstrap.jspf"%>
        <title>Realizar transferencia bancaria</title>
        <% //BEADCRUMB
            List<String []> breadcrumb = new ArrayList<String []>();    
            breadcrumb.add(new String[] { "/BancaOnline/", "Inicio" } );
            breadcrumb.add(new String[] { "/BancaOnline/login", "Login" });
            breadcrumb.add(new String[] { "/BancaOnline/usuario", "Usuario" });
            breadcrumb.add(new String[] { "#", "Realizar Transferencia" });

        %>
        
    </head>
    <body>
        <%@ include file="/WEB-INF/jspf/cabecera.jspf"%>
        
        <div class="container"> 
            <form action="/BancaOnline/usuario/RealizarTransferencia"
                  method="post">
                
                <div class="row">
                    <table>
                        <tr>
                            <th>Nombre(*):&emsp;</th>
                            <th><input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre" size="15"></th>
                            <th><input type="text" class="form-control" id="apellidos" name="apellidos" placeholder="Apellidos"size="35"></th>
                        </tr>
                          
                        <tr>
                            <td><b>Email:</b></td>
                            <td><input type="text" class="form-control" id="email" name="email"size="20"></td>
                        </tr>

                        <tr>
                            <td>&emsp;</td>
                        </tr>

                    </table>
                </div>
                 
                <div class="row">
                    <table>
                        <tr>
                           <td></td>
                           <td><b>ctrl IBAN(*)</b>&emsp;</td>
                           <td>&emsp;<b>Entidad(*)</b>&emsp;</td>
                           <td>&emsp;<b>Oficina(*)</b>&emsp;</td>
                           <td>&emsp;<b>ctrl CCC(*)</b>&emsp;</td>
                           <td>&emsp;<b>CCC(*)</b></td>
                        </tr>

                        <tr>
                            <td><b>ES</b></td>
                            <td><input type="text" class="form-control" name="ctrliban" size="4"></td>
                            <td><input type="text" class="form-control" name="entidad" size="4"></td>
                            <td><input type="text" class="form-control" name="oficina" size="4"></td>
                            <td><input type="text" class="form-control" name="ctrlCCC" size="2"></td>
                            <td><input type="text" class="form-control" name="nc" size="10"></td>
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
                            <td><pre>Concepto: <input type="text" name="concepto"></pre></td>
                        </tr>
                    </table> 
                </div>
                
                <div class="row">
                    <button class=" mx-auto col-3 btn btn-danger">Realizar transferencia</button>
                </div>
            </form>
        </div>




    </body>
</html>
