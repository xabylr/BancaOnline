<%-- 
    Document   : bancaInicio
    Created on : 18-mar-2018, 0:10:16
    Author     : Javier (Basado en login)
--%>

<%@page import="sesion.DineroCC"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="entidad.*"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Dinero"%>

<%
    Cliente cliente = (Cliente) session.getAttribute("cliente"); 
    String nombreYApellidos = cliente.getNombre() + " " + cliente.getApellidos();
    Collection<Movimientorealizado> movimientos = (Collection<Movimientorealizado>)session.getAttribute("movimientosRealizados");
%>


<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/cargar_bootstrap.jspf"%>
         <link rel="stylesheet" type="text/css" href="styles.css">
        <title>Visión general de <%=nombreYApellidos%></title>

    </head>
    <body class="body">
        
        <jsp:include page="/WEB-INF/jspf/cabecera.jspf"/>
        
        <div class="d-flex">
            <ul class="breadcrumb list-inline mx-auto justify-content-center">
                /<li ><a href="/BancaOnline/usuario">Inicio</a></li>
            </ul>
        </div>
        
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    Información
                    <div class="row header enmarcado">
                        <table style="width:100%">
                            <tr>
                                <td clas="celdaDato">Cliente:</td>
                                <td class="celdaValor"><%=nombreYApellidos%></td>
                            </tr>
                            
                            <tr>
                                <td clas="celdaDato">IBAN:</td>
                                <td class="celdaValor"><%= cliente.getCuenta().getIban() %>  </td>
                            </tr>
                            <tr>
                                <td class="celdaDato">Saldo:</td>
                                <td class="celdaValor"><%= new DineroCC(cliente.getCuenta()).toString() %>  </td>
                            </tr>       
                        </table>
                    </div>   
                            
                </div>
                
                <div class="col-sm-6">
                    <form action="../Blackjack">
                        <!--<button><img src ="res/blackjack.jpg" alt="Juega a nuestro blackjack online!" width="100%"></button>-->
                        <button id="close-image" display="block"><img src ="res/blackjack.jpg" alt="Juega a nuestro blackjack online!" width="100%"></button>
                        
                    </form>
                    
                    
                </div>
            
            </div>  <!-- fin de la primera fila -->
            
            Últimos movimientos:
            
            <!--<div class="container">-->
            
                <div class="row">
            <div class="col-sm-6">
                   <div class="enmarcado">
                       <%
                           if(movimientos != null){
                               for(Movimientorealizado m : movimientos){
                                   %>
                                   <%=m.toString()%>
                                   <br>
                                   <%
                               }
                           }
                       %>
                   </div>
                   
                   
            </div>
                <div class="col-sm-6">
                    
                    <form action="../realizartransferencia">
                        <button>Realizar Transferencia</button>
                    </form>
                    
                </div>
            </div>
            
            </div>
                <div class="col-sm-6">
                    
                    <form method="post" action="../VerMovimientos">
                        <button>Ver Movimientos</button>
                        <input type="hidden" name="dnicliente" value="<%=cliente.getDni()%>">
                    </form>
                    
                </div>
            </div>
            
            <!--</div>-->
        </div>
    </body>
</html>
