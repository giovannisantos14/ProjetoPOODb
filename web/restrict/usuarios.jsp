<%-- 
    Document   : usuarios
    Created on : Jun 21, 2020, 7:43:44 PM
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
            String role = request.getParameter("role");
            String password = request.getParameter("password");
            Usuario.addUsuario(login, name, role, password);
            response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            requestException = ex;
        }
    }
    
    if(request.getParameter("delete")!=null){
        try{
            String valor = request.getParameter("codigo");
            int codigo = Integer.parseInt(valor);
            Usuario.removeUser(codigo);
            response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            requestException = ex;
        }
    }
    
    ArrayList<Usuario> list = new ArrayList<>();
    try{
        list = Usuario.getUsuarios();
    }catch(Exception ex){
        requestException = ex;
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Painel de Usuarios - Projeto 03</title>
    </head>
    <% if(session.getAttribute("user.login")==null){%>
            <h3>É preciso estar autenticado para acessar o conteúdo desta página.</h3>
        <% }else if(!session.getAttribute("user.role").equals("ADMIN")){%>
                <h3>É preciso ser administrador para acessar o conteúdo desta página.</h3>
            <% }else{%>
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
                <table border="1">
                    <tr>
                        <th>Codigo</th>
                        <th>Login</th>
                        <th>Nome</th>
                        <th>Papel</th>
                        <th>Comandos</th>
                    </tr>
                    <%for(Usuario usuario: list){%>
                    <tr>
                        <td><%= usuario.getCodigo()%></td>
                        <td><%= usuario.getLogin() %></td>
                        <td><%= usuario.getNome() %></td>
                        <td><%= usuario.getCargo() %></td>
                        <td>
                            <form method="post">
                                <input type="hidden" name="codigo" value="<%=usuario.getCodigo()%>"/>
                                <input type="submit" name="delete" value="Remover"/>
                            </form>
                        </td>
                    </tr>
                    <%}%>
                </table>
                </body>
            <%}%>
</html>