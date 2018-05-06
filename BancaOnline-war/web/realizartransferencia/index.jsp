<%-- 
    Document   : index
    Created on : 26-abr-2018, 22:07:50
    Author     : javier
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/cargar_bootstrap.jspf"%>
        <title>Realizar transferencia bancaria</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jspf/cabecera.jspf"/>
        <div class="d-flex">
            <ul class="breadcrumb list-inline mx-auto justify-content-center">
                /<li ><a href="/BancaOnline/usuario">Inicio</a></li>/
                <li ><a href="#">Realizar Transferencia</a></li>
            </ul>
        </div>
        <br/>
        
        <div class="container"> 
            <form action="../RealizarTransferencia">
                <div class="row">
                    <table>
                        <thead>
                          <tr>
                            <th>Nombre(*):&emsp;</th>
                            <th><input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre" size="15"></th>
                            <th><input type="text" class="form-control" id="apellidos" name="apellidos" placeholder="Apellidos"size="35"></th>
                          </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><b>Email:</b></td>
                                <td><input type="text" class="form-control" id="email" name="email"size="20"></td>
                            </tr>

                            <tr>
                                <td>&emsp;</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                    
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
                        <td><input type="text" class="form-control" id="IBAN" name="IBAN" size="4"></td>
                        <td><input type="text" class="form-control" id="Entidad" name="Entidad" size="4"></td>
                        <td><input type="text" class="form-control" id="Oficina" name="Oficina" size="4"></td>
                        <td><input type="text" class="form-control" id="ctrlCCC" name="ctrlCCC" size="2"></td>
                        <td><input type="text" class="form-control" id="CCC" name="CCC" size="10"></td>
                    </tr>
                    
                    <tr>
                        <td><b>Importe(*)</b>&emsp;</td>
                        <td><input min="1" step="any" name = "importe"/>&emsp;</td>
                        <td><br><select name="divisa">
                              <option value="Euros">Euros</option>
                              <option value="Dolares">Dolares</option>
                              <option value="Pesos">Pesos</option>
                            </select><br/><br/></td>
                    </tr>
                    <tr>
                        <td><pre>Concepto: <input type="text" name="concepto"></pre></td>
                        <td><button class="btn btn-success">Realizar transferencia</button></td>
                    </tr>
                </table>   
            </form>
        </div>




    </body>
</html>
