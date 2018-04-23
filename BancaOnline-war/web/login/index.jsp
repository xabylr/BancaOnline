<%-- 
    Document   : bancaInicio
    Created on : 18-mar-2018, 0:10:16
    Author     : Abel (Remasterizado by Stefan)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UMA Bank Login</title>
        
        <link rel="stylesheet" type="text/css" href="styles.css">
             <%--
        <link rel="stylesheet" href="../res/boostrap/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="../res/boostrap/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
   --%>
      
         <link rel="stylesheet" href="/static/bootstrap/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
            <script src="/static/bootstrap/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
            
            
    </head>
    <body class="body">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 leftPane">
                    <div class="row header">
                        <h2 class="col-lg-4"><b>UMA BANK</b></h2>
                        <h4 class="col-lg-8"><i>Tu dinero es nuestro y nuestro dinero tambien</i></h4>
                    </div>

                    <div class="row imgIzquierda">
                        <img class="img" src="res/banco.jpg"/>
                    </div>
                </div>
                
                <div class="col-lg-4">
                    <div class="col-lg-12 logInForm">
                        
                            <form class="formTopMargin" method="post" action="../LoginServlet" >
                                <pre>       Iniciar Sesi&oacute;n</pre>
                                <pre>       DNI :      <input type="text" name="DNI" /></pre>
                                <pre>       Password : <input type="password" name="password"/></pre>
                                <pre>       <button>Enviar</button></pre>
                            </form>
                        
                    </div>

                    <div class="col-lg-12 gifRobo">                      
                        <video class="img" loop autoplay >
                            <source src="res/rata.mp4" type="video/mp4"/>
                        </video>
                    </div>
                    
                </div>
            </div>                
        </div>
    </body>
</html>