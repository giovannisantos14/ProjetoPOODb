<%-- 
    Document   : login
    Created on : Jun 28, 2020, 5:58:28 PM
    Author     : Gio
--%>
<%@page import="web.DbListener"%>
<%@page import="db.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Exception requestException = null;
    if(request.getParameter("insert")!=null){
        try{
            String login = request.getParameter("login");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            Usuario.addUsuario(login, name, "USER", password);
            response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            requestException = ex;
        }
    }
    
     String loginError = null;
    if(request.getParameter("session.login")!=null){
        String login = request.getParameter("user.login");
        String password = request.getParameter("user.password");
        try{
            Usuario user = Usuario.getUsuario(login, password);
            if(user==null){
                loginError = "Login or password incorrect";
            }else{
                session.setAttribute("user.id", user.getCodigo());
                session.setAttribute("user.login", login);
                session.setAttribute("user.name", user.getNome());
                session.setAttribute("user.role", user.getCargo());
                response.sendRedirect(request.getRequestURI());
            }
        }catch(Exception ex){
            loginError = "Error: " + ex.getMessage();
        }
    }else if(request.getParameter("session.logoff")!=null){
        session.removeAttribute("user.id");
        session.removeAttribute("user.login");
        session.removeAttribute("user.name");
        session.removeAttribute("user.role");
        response.sendRedirect(request.getRequestURI());
    }
%>
<html>
    <head>
        <%if(requestException != null){%>
                    <div>Erro ao cadastrar novo usuário:<%= requestException.getMessage()%></div>
        <%}%>
        <%if(loginError != null){%>
            <div>Erro: <%= loginError%></div>
        <%}%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Projeto 03</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf"%>
        <%if(session.getAttribute("user.login") != null){%>
            <h2>Você já esta logado, redirecionando para tela principal...</h2>
            <meta http-equiv="refresh" content="0; url=index.jsp" />
        <%} else{%> 
            <form method="post">
                <div>Bem vindo, por favor, insira seu login e senha:</div>    
                Usuário: <input type="text" name="user.login"/>
                Senha: <input type="password" name="user.password"/>
                <input type="submit" value="LogIn" name="session.login"/>
            </form>
        <%}%>
    </body>
</html>
