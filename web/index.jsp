<%-- 
    Document   : index
    Created on : Jun 21, 2020, 3:30:35 PM
    Author     : Gio
--%>

<%@page import="web.DbListener"%>
<%@page import="db.Quiz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina Inicial - Projeto 03</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf"%>
                <table border="1">
            <caption>Melhores 10 resultados</caption>
            <tr>
                <th>Nome</th>
                <th>Resultado</th>
            </tr>
            <%for(Quiz q: Quiz.getQuizzes()){%>
            <tr>
                <td><%=q.getNome()%></td>
                <td><%=q.getAcertos()%></td>
            </tr>    
             <%}%>
        </table>
        <table border=="1">
            <caption>Ultimos 10 resultados</caption>
            <tr>
                <th>Nome</th>
                <th>Resultado</th>
            </tr>
 
        </table>
    <%//}%>
    </body>
</html>
