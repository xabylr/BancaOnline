<%-- 
    Document   : bancaInicio
    Created on : 18-mar-2018, 0:10:16
    Author     : Javier (Basado en login)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visión general de &ltUSUARIO&gt </title>
        
        <link rel="stylesheet" type="text/css" href="styles.css">
      
         <link rel="stylesheet" href="/static/bootstrap/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
            <script src="/static/bootstrap/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
               
    </head>
    <body class="body">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    información
                    <div class="row header enmarcado">
                        <table style="width:100%">
                            <tr>
                                <td clas="celdaDato">IBAN:</td>
                                <td class="celdaValor">ES 0046 8993 4556 4712</td>
                            </tr>
                            <tr>
                                <td class="celdaDato">Saldo:</td>
                                <td class="celdaValor">0€</td>
                            </tr>       
                        </table>
                    </div>
                </div>
                
                <div class="col-sm-6">
                    <img src ="res/blackjack.jpg" alt="Juega a nuestro blackjack online!" width="100%">
                    
                </div>
            
            </div>  <!-- fin de la primera fila -->
            
            
            <div class="row">
                   Últimos movimientos
                   <div class="enmarcado">
                   </div>
                   
                   
            </div>
            
            
            
        </div>
    </body>
</html>