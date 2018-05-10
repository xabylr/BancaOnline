<%-- 
    Document   : bancaInicio
    Created on : 18-mar-2018, 0:10:16
    Author     : Javier (Basado en login)
--%>

<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="sesion.IbanCC"%>
<%@page import="sesion.DineroCC"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="entidad.*"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Dinero"%>

<%
    Cliente cliente = (Cliente) session.getAttribute("cliente"); 
    String nombreYApellidos = cliente.getNombre() + " " + cliente.getApellidos();
    List<Movimiento> movimientos = (List<Movimiento>)session.getAttribute("movimientos");
%>


<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/cargar_bootstrap.jspf"%>
         <link rel="stylesheet" type="text/css" href="styles.css">
         
        <title>Visión general de <%=nombreYApellidos%></title>
        <%  List<String []> breadcrumb = new ArrayList<String []>();    
            breadcrumb.add(new String[] { "/BancaOnline/", "Inicio" } );
            breadcrumb.add(new String[] { "/BancaOnline/login", "Login" });
            breadcrumb.add(new String[] { "#", "Usuario" });
 
        %>
    </head>
    <body class="body">
        
        <%@ include file="/WEB-INF/jspf/cabecera.jspf"%>
 
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
                                <td class="celdaValor"><%= new IbanCC(cliente.getCuenta()) %>  </td>
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
            
            <div class="container">
                <div class="row d-flex justify-content-center">       
                    <a href="transferencia" 
                        class="btn btn-warning" role="button">Realizar transferencia
                     </a>
                </div>
            </div>
            
            
            Últimos movimientos:
            
           <div class="enmarcado row">
               <table class="col-lg-12" border ="1">
                    <tr>
                        <th>Remitente</th>
                        <th>Receptor</th>
                        <th>Cantidad</th>
                        <th>Concepto</th>
                        <th>Fecha</th>

                    </tr>

                <%
                    if(movimientos != null){
                        for(Movimiento m : movimientos){
                            String saldo = new Dinero(m.getCuantia().longValue(),
                                    m.getDecimales(), m.getDivisa()).toString();
                            String ibanRemitente = new IbanCC(m.getRemitente()).getIBAN();
                             String ibanReceptor = new IbanCC(m.getReceptor()).getIBAN();
                            %>
                            <tr>
                            <td><%=ibanRemitente%>
                            <td><%=ibanReceptor%>
                            <td><%=saldo%>
                            <td><%=m.getConcepto()%>
                            <td><%=new Date(m.getFecha().longValue() )%>
                            <tr>    

                            <%
                        }
                    }
                %>

                </table>
                </div>
                <br/>
                <div class="row d-flex justify-content-center">

                    <a href="VerMovimientos?idCliente=<%=cliente.getDni() %>" 
                        accesskey=""class="btn btn-info" role="button">Ver Movimientos
                    </a> 

                </div>
            </div>
                       
            <div class="container">
                
            </div>
    </body>
</html>
