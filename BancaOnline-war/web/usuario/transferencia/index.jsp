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
        
        <div class="container">
            
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
        
        
        <h1>Hello World!</h1>
    </body>
</html>
