<%-- 
    Document   : index
    Created on : 06-may-2018, 20:05:38
    Author     : Jose Santos
--%>

<%@page import="sesion.IbanCC"%>
<%@page import="sesion.ClienteFacade"%>
<%@page import="java.util.List"%>
<%@page import="entidad.Cliente"%>
<%@page import="entidad.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
            List<Cliente> lista = (List<Cliente>)request.getAttribute("lista");
            Empleado empleado = (Empleado)session.getAttribute("empleado");
        %>
        
        <title>Banca Online</title>
    </head>
    <body>
        <h1>Clientes</h1>
        
        <form method="post" action="BuscarClientes">
            
            Buscar por: 
            
            <select name="criteriobusqueda">
                <option value="0">DNI</option>
                <option value="1">Nombre</option>
                <option value="2">Apellidos</option>
                <option value="3">Entidad</option>
                <option value="4">Oficina</option>
                <option value="5">Nº Cuenta</option>
            </select>
            
            <input type="text" name="contenidobusqueda">
            
            <button>Buscar</button>
            
        </form>
        
        
        
    <table class="table table-bordered">
    <thead>
      <tr>
        <th>DNI</th>
        <th>Nombre</th>
        <th>Apellidos</th>
        <th>Cuenta</th>
      </tr>
    </thead>
    <tbody>
        
        
        <%
           if(lista!=null) 
           for(Cliente c : lista){ 
        %>
                
            <tr>
                <td><%=c.getDni()%></td>
                <td><%=c.getNombre()%></td>
                <td><%=c.getApellidos()%></td>
                <td><%=new IbanCC(c.getCuenta())%></td>
                
                <td>
                    <form method="get" action="../VerMovimientos">
                        <button>Ver Movimientos</button>
                        <input type="hidden" name="idCliente" value="<%=c.getDni()%>">
                    </form>       
                </td>
                
            </tr>   
        
        
        <%    
           }  
        %>
        </table>
        
    </body>
</html>
