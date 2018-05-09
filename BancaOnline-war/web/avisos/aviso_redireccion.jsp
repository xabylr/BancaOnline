<%-- 
    Document   : aviso_redireccion
    Created on : 09-may-2018, 0:54:19
    Author     : javier
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  
    String aviso = (String) request.getAttribute("aviso");
    String detalles = (String) request.getAttribute("detalles");
    String url = (String) request.getAttribute("url");
    Integer duracion = (Integer) request.getAttribute("duracion");
    %>
<!DOCTYPE html>
<html>
<head>
    <title><%=aviso%> </title>
    
    <meta http-equiv="refresh" content="<%=duracion%>;url="<%=url%>" >
    <meta name="keywords" content="automatic redirection">

</head>

<body>
    <center>
        <h1><%=aviso%></h1>
        <h2><%=detalles%></h2>
    Si tu navegador no te redirige automáticamente en <%=duracion%> segundos,
    <a href="<%=url%>">pulsa aquí</a> para acceder manualmente.

    </center>
</body>
</html>
