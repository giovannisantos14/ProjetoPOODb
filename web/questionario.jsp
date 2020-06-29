<%-- 
    Document   : questionario
    Created on : Jun 21, 2020, 6:07:53 PM
    Author     : Gio
--%>

<%@page import="db.Resposta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="db.Questao"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
  ArrayList<Questao> questoes = Questao.getQuestoes();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Questionário - Projeto03</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf"%>
        <% if(session.getAttribute("user.login")==null){%>
            <h3>Você precisa estar logado para ver o questionário!</h3>
        <% } else{%>
            <h3>Questionário</h3>
            <form action="processamento/questionario_process.jsp" method="post">
                <input type="hidden" name="tamanho_quiz" value="<%=questoes.size()%>"/>
                <% for(int i = 1; i<=questoes.size(); i++){ %>
                <h3><%=i%>. <%=questoes.get(i-1).getDescricao()%></h3>
                <input type="hidden" name="questao<%=i%>" value="<%=questoes.get(i-1).getCodigo()%>"/>
                    <% for(Resposta resposta: Resposta.getRespostas(questoes.get(i-1).getCodigo())){%>
                    <div>
                    <input type="radio" id="<%=i%><%=resposta.getCodigo()%>" name="resposta<%=i%>" value="<%=resposta.getCodigo()%>">
                    <label for="<%=i%><%=resposta.getCodigo()%>"><%=resposta.getDescricao()%></label>
                    </div>
                    <%}%>
                    <br>
                <%}%>
                <br>
                <br>
                <input type="submit" value="resultado" name="resultado.submit"/>
            </form>
        <% }%>
    </body>
</html>
