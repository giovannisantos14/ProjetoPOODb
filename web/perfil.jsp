<%-- 
    Document   : perfil
    Created on : Jun 28, 2020, 7:11:15 PM
    Author     : Gio
--%>
<%@page import="db.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Exception requestException = null;
    if(request.getParameter("changePassword")!=null){
        try{
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            int codigo = Usuario.getUsuario(login, password).getCodigo();
            String new_password = request.getParameter("new_password");
            String new_password2 = request.getParameter("new_password2");
            if(Usuario.getUsuario(login, password)==null){
                throw new Exception("Login/password incorrect");
            }else if(!new_password.equals(new_password2)){
                throw new Exception("Invalid password confirmation");
            }else{
                Usuario.changePassword(codigo, new_password);
                response.sendRedirect(request.getRequestURI());
            }
        }catch(Exception ex){
            requestException = ex;
        }
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perfil - Projeto03</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <h2>Meu perfil</h2>
        <%if(session.getAttribute("user.login")==null){%>
            <p>É preciso estar logado para acessar o conteúdo desta página</p>
        <%}else{%>
            <%if(requestException != null){%>
                <div style="color:red"><%= requestException.getMessage() %></div>
            <%}%>
            <h3>Login: <%= session.getAttribute("user.login")%></h3>
            <h3>Nome: <%= session.getAttribute("user.name")%></h3>
            <h3>Cargo: <%= session.getAttribute("user.role")%></h3>
            <fieldset>
                <legend>Mudar senha</legend>
                <form method="post">
                    <input type="hidden" name="login" value="<%= session.getAttribute("user.login") %>"/>
                    Senha atual: <br/><input type="password" name="password" required/><br/>
                    Senha nova: <br/><input type="password" name="new_password" required/><br/>
                    Confirmação da senha nova: <br/><input type="password" name="new_password2" required/><br/>
                    <br/><input type="submit" name="changePassword" value="Redefinir" required/>
                </form>
            </fieldset>
        <%}%>
    </body>
</html>
