<%-- 
    Document   : index
    Created on : Jun 21, 2020, 3:30:35 PM
    Author     : Gio
--%>

<%@page import="web.DbListener"%>
<%@page import="db.Quiz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    int codigo_usuario = -1;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina Inicial - Projeto 03</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf"%>
        <%if(session.getAttribute("user.login") != null){%>
        <% codigo_usuario = Integer.parseInt(session.getAttribute("user.id").toString()); %>
        <p>A média de seus resultados é: <%=Double.toString(Quiz.getMediaUsuario(codigo_usuario))%></p>
        <%}%>
                <table border="1">
            <caption>Melhores 10 resultados</caption>
            <tr>
                <th>Nome</th>
                <th>Resultado</th>
            </tr>
            <%for(Quiz q: Quiz.getMelhoresQuizzes(10)){%>
            <tr>
                <td><%=q.getNome()%></td>
                <td><%=q.getAcertos()%></td>
            </tr>
             <%}%>
        </table>
        <br>
        <br>
        <table border=="1">
            <caption>Últimos 10 resultados</caption>
            <tr>
                <th>Nome</th>
                <th>Resultado</th>
            </tr>
            <%for(Quiz q: Quiz.getQuizzes(10)){%>
            <tr>
                <td><%=q.getNome()%></td>
                <td><%=q.getAcertos()%></td>
            </tr>
             <%}%>
        </table>
        <%if(session.getAttribute("user.login") != null){%>
            <br>
            <br>
            <table border=="1">
                <caption>Últimos 10 resultados de <%=session.getAttribute("user.name")%></caption>
                <tr>
                    <th>Nome</th>
                    <th>Resultado</th>
                </tr>
                <%for(Quiz q: Quiz.getQuizzesUsuario(10, codigo_usuario)){%>
                <tr>
                    <td><%=q.getNome()%></td>
                    <td><%=q.getAcertos()%></td>
                </tr>
                 <%}%>
            </table>
        <%}%>
    </body>
</html>
