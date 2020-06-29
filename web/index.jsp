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
    <style>
        .row {
            display: flex;
        }

        .column {
             flex: 50%;
        }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina Inicial - Projeto 03</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf"%>
        <div class="row">
            <div class="column">
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
            </div>
            <br>
            <br>
            <div class="column">
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
            </div>
            <%if(session.getAttribute("user.login") != null){%>
                <br>
                <br>
                <div class="column">
                    <table border=="1">
                        <caption>Últimos 10 resultados de <%=session.getAttribute("user.name")%></caption>
                        <tr>
                            <th>Nome</th>
                            <th>Resultado</th>
                        </tr>
                        <% int codigo_usuario = Integer.parseInt(session.getAttribute("user.id").toString()); %>
                        <%for(Quiz q: Quiz.getQuizzesUsuario(10, codigo_usuario)){%>
                        <tr>
                            <td><%=q.getNome()%></td>
                            <td><%=q.getAcertos()%></td>
                        </tr>
                         <%}%>
                    </table>
                </div>
            <%}%>
        </div>
    </body>
</html>
