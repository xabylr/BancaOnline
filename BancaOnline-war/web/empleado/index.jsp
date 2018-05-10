<%-- 
    Document   : index.jsp
    Created on : 09-may-2018, 17:55:51
    Author     : Jose Santos
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entidad.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/WEB-INF/jspf/cargar_bootstrap.jspf"%>
        <link rel="stylesheet" type="text/css" href="styles.css">
        <%  List<String []> breadcrumb = new ArrayList<String []>();    
            breadcrumb.add(new String[] { "/BancaOnline/", "Inicio" } );
            breadcrumb.add(new String[] { "/BancaOnline/login", "Login" });
            breadcrumb.add(new String[] { "#", "Empleado" });
 
        %>
        
        
        <%
            Empleado empleado = (Empleado)session.getAttribute("empleado");
        %>
        
        
        <title>Banca Online</title>
    </head>
    <body class="">
        
        <%@ include file="/WEB-INF/jspf/cabecera.jspf"%>
        
        <br/>
        
        DNI: <%=empleado.getDni()%>
        <br/>
        Nombre: <%=empleado.getNombre()%>
        <br/>
        Apellidos: <%=empleado.getApellidos()%>
        <br/>
        <br/>
        
        <table>
            <tr>
                <td>
                   <form method="post" action="../empleado/altaUsuario">
                        <button> Ir AltaUsuario</button>
                    </form> 
                </td>
                
                <td>
                    <form method="post" action="<%=request.getContextPath() + "/VerClientes"%>">
                        <button>Ver Clientes</button>
                    </form>
                </td>
            </tr>
        </table>
        
        
        
        
    </body>
</html>
