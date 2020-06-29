<%-- 
    Document   : questionario_process
    Created on : Jun 21, 2020, 7:08:25 PM
    Author     : Gio
--%>

<%@page import="db.Quiz"%>
<%@page import="db.Resposta"%>
<%-- any content can be specified here e.g.: --%>
<%@ page pageEncoding="UTF-8" %>
<%
    if(request.getParameter("resultado.submit") != null && 
            session.getAttribute("user.id") != null){
        int codigo_usuario = Integer.parseInt(session.getAttribute("user.id").toString());
        int acertos = 0;
        int codigo_questao;
        int codigo_resposta;
        int tamanho_quiz = Integer.parseInt(request.getParameter("tamanho_quiz"));
        for(int i = 1; i<=tamanho_quiz; i++){
            codigo_questao = Integer.parseInt(request.getParameter("questao".concat(Integer.toString(i))));
            codigo_resposta = Integer.parseInt(request.getParameter("resposta".concat(Integer.toString(i))));
            if(codigo_resposta == Resposta.getRespostaCorreta(codigo_questao)){
                acertos++;
            }
        }
        Quiz.addQuiz(session.getAttribute("user.name").toString()+ " - " + Integer.toString(Quiz.getNumeroQuiz(codigo_usuario)), 
                     acertos, 
                     codigo_usuario);
    }
%>
<h3>Você será redirecionado automáticamente, ou <a href="../index.jsp">clique aqui</a> para ser redirecionado</h3>
<!--<meta http-equiv="refresh" content="0; url=../index.jsp" />-->