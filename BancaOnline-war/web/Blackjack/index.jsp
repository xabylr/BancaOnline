<%@page import="java.awt.Toolkit"%>
<%@page import="java.awt.Dimension"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en-us">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Unity WebGL Player | Blackjack Online</title>
    <link rel="shortcut icon" href="TemplateData/favicon.ico">
    <link rel="stylesheet" href="TemplateData/style.css">
    <script src="TemplateData/UnityProgress.js"></script>  
    <script src="Build/UnityLoader.js"></script>
    <script>
      var gameInstance = UnityLoader.instantiate("gameContainer", "Build/Desktop.json", {onProgress: UnityProgress});
    </script>
    
    <%@ include file="/WEB-INF/jspf/cargar_bootstrap.jspf"%>
    <link rel="stylesheet" type="text/css" href="styles.css">
    
    <%  List<String []> breadcrumb = new ArrayList<String []>();    
            breadcrumb.add(new String[] { "/BancaOnline/", "Inicio" } );
            breadcrumb.add(new String[] { "/BancaOnline/login", "Login" });
            breadcrumb.add(new String[] { "/BancaOnline/usuario", "Usuario" });
            breadcrumb.add(new String[] { "#", "Blackjack" });
 
    %>
           
  </head>
  <body>
      
       <%@ include file="/WEB-INF/jspf/cabecera.jspf"%>
      
      
    <div class="webgl-content">

      <div id="gameContainer" style="width: 960px; height: 600px"></div>
      <div class="footer">
        <div class="webgl-logo"></div>
        <div class="fullscreen" onclick="gameInstance.SetFullscreen(1)"></div>
        <div class="title">Blackjack Online</div>
      </div>
    </div>
  </body>
</html>
