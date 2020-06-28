<%-- 
    Document   : cadastro
    Created on : Jun 28, 2020, 5:59:11 PM
    Author     : Gio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Novo Usuario - Projeto 03</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf"%>
        <%if(session.getAttribute("user.login") != null){%>
            <h3>Você já está logado!</h3>
        <%} else{%>
        <form method="post" action="login.jsp">
                Login: <input type="text" name="login" required/><br>
                Nome: <input type="text" name="name" required/><br>
                Senha: <input type="password" name="password" required/><br>
                <input type="submit" name="insert" value="Criar novo usuário"/>
            </form>
        <%}%>
    </body>
</html>
