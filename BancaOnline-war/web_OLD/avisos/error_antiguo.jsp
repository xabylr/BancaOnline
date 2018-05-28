<%-- 
    Document   : error
    Created on : 19-mar-2018, 15:22:54
    Author     : Jose Santos
--%>

<%--
    "terror" = Titulo
    "error" = Cuerpo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error :(</title>
    </head>
    
    <%  String titulo = "Error :(";
        if(request.getAttribute("terror") != null){
            titulo = (String)request.getAttribute("terror");
            request.setAttribute("terror", null);
        }
        
        String cuerpo = "Se ha producido un error al hacer la petición, inténtelo de nuevo";
        if(request.getAttribute("error") != null){
            cuerpo = (String)request.getAttribute("error");
            request.setAttribute("error", null);
        }
    %>
    
    <body>
        <h1><%=titulo%></h1>
        <p><%=cuerpo%></p>
    </body>
</html>
