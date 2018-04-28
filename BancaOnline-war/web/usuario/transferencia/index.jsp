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
        
<!--        <div class="container">
            
            <form>
              <div class="form-group row">
                <label for="colFormLabelSm" class="col-sm-2 col-form-label col-form-label-sm">Email</label>
              
             
                  
                <div class="col">
                   
                  <input type="text" class="form-control" placeholder="First name">
                </div>
                <div class="col">
                  <input type="text" class="form-control" placeholder="Last name">
                </div>
                  
                  
              </div>
            </form>
            
            
            
            <form class="iban">
                
                  
                
                
                <table class='table' alight='center'>
                    <thead>
                    <td>ctrl IBAN <td>Entidad <td> Oficina <td> ctrl CCC <td> CCC
                    </thead>
                    
                    <tr>
                       
                    <td>  ES   <input class="text" name="CTRL_IBAN" maxlength="2"> </td>
                    <td>       <input class="text" name="ENTIDAD"maxlength="4">    </td>
                    <td>       <input class="text" name="OFICINA"maxlength="4">    </td>
                    <td>       <input class="text" name="CTR_CCC"maxlength="2">    </td>
                    <td>       <input class="text" name="CCC"maxlength="10">       </td>
                </table>
               
                
                
               
                

                
            </form>
            
            
            
        </div>
        
        
        <h1>Hello World!</h1>-->


        <div class="container" style="margin-top:50px"> 

            <form>
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
        </table>
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
          <td><input min="1" step="any" />&emsp;</td>
          <td><br><select name="Divisa">
                <option value="Euros">Euros</option>
                <option value="Dolares">Dolares</option>
                <option value="Pesos">Pesos</option>
              </select><br/><br/></td>
      </tr>
      <tr>
          <td></td>
          <td><button type="button" class="btn btn-success">Realizar transferencia</button></td>
      </tr>
    </tbody>
  </table>
         </form>   
            
            
            
        </div>




    </body>
</html>
