<%-- 
    Document   : questionario_process
    Created on : Jun 21, 2020, 7:08:25 PM
    Author     : Gio
--%>

<%-- any content can be specified here e.g.: --%>
<%@ page pageEncoding="UTF-8" %>
<%
    if(request.getParameter("request.submit") != null){
//        verificar se as respostas estão certas ou não e depois mandar o resultado para o banco de dados
    }
%>
<h3>Você será redirecionado automáticamente, ou <a href="../index.jsp">clique aqui</a> para ser redirecionado</h3>
<meta http-equiv="refresh" content="0; url=../index.jsp" />