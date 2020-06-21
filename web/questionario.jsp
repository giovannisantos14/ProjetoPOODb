<%-- 
    Document   : questionario
    Created on : Jun 21, 2020, 6:07:53 PM
    Author     : Gio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Questionário - Projeto03</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf"%>
        <% //if(session.getAttribute("user.login")==null){%>
            <h3>Você precisa estar logado para ver o questionário!</h3>
        <% //} else{%>
            <h3>Questionário</h3>
            <form action="processamento/questionario_process.jsp" method="post">
                
                
                <input type="submit" value="resultado" name="resultado.submit"/>
            </form>
        <% //}%>
    </body>
</html>
