<%-- 
    Document   : usuarios
    Created on : Jun 21, 2020, 7:43:44 PM
    Author     : Gio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%/*
    Exception requestException = null;
    if(request.getParameter("insert")!=null){
        try{
            String login = request.getParameter("login");
            String name = request.getParameter("name");
            String role = request.getParameter("role");
            String password = request.getParameter("password");
            User.addUser(login, name, role, password);
            response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            requestException = ex;
        }
    }
    
    if(request.getParameter("delete")!=null){
        try{
            String login = request.getParameter("login");
            User.removeUser(login);
            response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            requestException = ex;
        }
    }
    
    ArrayList<User> list = new ArrayList<>();
    try{
        list = User.getList();
    }catch(Exception ex){
        requestException = ex;
    }*/
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Painel de Usuarios - Projeto 03</title>
    </head>
    <% //if(session.getAttribute("user.login")==null){%>
            <h3>É preciso estar autenticado para acessar o conteúdo desta página.</h3>
        <% //}else{%>
            <% //if(!session.getAttribute("user.role").equals("ADMIN")){%>
                <h3>É preciso ser administrador para acessar o conteúdo desta página.</h3>
            <% //}else{%>
                <body>
                    <%@include file="../WEB-INF/jspf/header.jspf"%>
                    <fieldset>
                    <legend>Novo usuário</legend>
                    <form method="post">
                        Login: <input type="text" name="login"/>
                        Nome: <input type="text" name="name"/>
                        Role: <select name="role">
                            <option value="ADMIN">ADMIN</option>
                            <option value="USER">USER</option>
                        </select>
                        Senha: <input type="password" name="password"/>
                        <input type="submit" name="insert" value="Inserir"/>
                    </form>
                </fieldset>
                <hr/>
<!--                <table border="1">
                    <tr>
                        <th>Login</th>
                        <th>Nome</th>
                        <th>Papel</th>
                        <th>Comandos</th>
                    </tr>
                    <% //for(User user: list){%>
                    <tr>
                        <td><%//= user.getLogin() %></td>
                        <td><%//= user.getName() %></td>
                        <td><%//= user.getRole() %></td>
                        <td>
                            <form method="post">
                                <input type="hidden" name="login" value="<%//=user.getLogin()%>"/>
                                <input type="submit" name="delete" value="Remover"/>
                            </form>
                        </td>
                    </tr>
                    <%}%>
                </table>-->
                </body>
            <%//}%>
</html>