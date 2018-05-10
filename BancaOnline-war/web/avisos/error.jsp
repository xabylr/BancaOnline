<%-- 
    Document   : error
    Created on : 19-mar-2018, 15:22:54
    Author     : Jose Santos
--%>

<%@page import="java.util.Random"%>
<%@page import="java.util.StringTokenizer"%>
<%--
    "terror" = Titulo
    "error" = Cuerpo

( Autor: http://fakeupdate.net/win10u/bsod.html )

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%  String titulo = "Error :(";
        if(request.getAttribute("terror") != null){
            titulo = (String)request.getAttribute("terror");
        }
        
        String cuerpo = "Se ha producido un error en GF y necesita reiniciarse."
                + " Vamos a leer los errores de los logs y después reiniciaremos"
                + " a mano.";
        if(request.getAttribute("error") != null){
            cuerpo = (String)request.getAttribute("error");
        }
        
        String ruta = "#";
        if(request.getAttribute("rerror") != null){
            ruta = (String)request.getAttribute("rerror");
            request.setAttribute("rerror", null);
        }
        
            

        String[] ERRORES = {
            "C#_ES_MEJOR_QUE_JAVA",
            "JAVA_SIEMPRE_MALO",
            "JOSE_SIEMPRE_MALO",
            "EJB_NO_EXCEPTION_EXCEPTION",
            "NULL_KNOWLEDGE_EXCEPTION",
            "ILEGAL_FACTORIO_EXCEPTION",
            "EOL_ERROR_EXCEPTION",
            "UNEXPECTED_INDENT_EXCEPTION",
            "SIMULACRO_EXAM_NOTES_EXCEPTION",
            "PAPYRUS_WORKING_WELL_EXCEPTION",
            "TITO_BILL_FAULT_EXCEPTION",
            "GLASSFISH_NO_ERRORS_EXCEPTION",
            "SNYTAX_ERORR_EXECPTION",
            "HONOR_OR_502_BAD_GATEWAY",
            "SQL_INYECTION_EXCEPTION"
        };
       
        String error = ERRORES[new Random().nextInt(ERRORES.length) ];

     %>

    
    <body>
        <h1><%=titulo%></h1>
        <p><%=cuerpo%></p>
        
        <form method="post" action="<%=ruta%>">
        
            <button>Aceptar</button>
            
        </form>
        
    </body>
</html>


<!DOCTYPE html>
<style>
body { font-family:"Segoe UI Light", "Segoe UI", Arial; font-weight: lighter;}
.content {width:700px; margin:10% auto;user-select:none;-moz-user-select:none;-webkit-user-select:none;-ms-user-select:none; }
</style>


</head><body bgcolor="#0086ac" scroll="no"> 

<div class="content">
<font size="4" color="#FFFFFF"> 
<p style="font-size:100pt; margin:0; padding-bottom:20pt">:(</p>
<p style="font-size:20pt; margin:0; padding:0"><%=cuerpo%></p>

<br>Vuelve a la página anterior<br><br>


<img src="qr.png" style="float:left;"/>
<div style="float:left;margin-left:30px;width:65%;">

<p style="font-size:14pt; color:#fff; margin-top:-30px; padding:0;line-height:32px;"><br />Para más información acerca de este fallo<br>y posibles arreglos, visita<br>http://server.com/Bancaonline/Blackjack<br><br>Si contactas con algún desarrollador dale la siguente información:<br>Stop Code: <%=error%></p>



</div>

</div>
</font> 
</body></html> 

