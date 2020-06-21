/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import web.DbListener;

/**
 *
 * @author Giovanni
 */
public class Usuario {
    private int codigo;
    private String login;
    private String nome;
    private String cargo;
    
    public static ArrayList<Usuario> getUsuarios() throws Exception{
        ArrayList<Usuario> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(DbListener.URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tb_usuarios");
        while(rs.next()){
            list.add(new Usuario(
                    rs.getInt("cd_usuario"),
                    rs.getString("nm_login"), 
                    rs.getString("nm_usuario"),
                    rs.getString("nm_cargo"))
            );
        }
        rs.close();
        stmt.close();
        conn.close();
        return list;
    }
    
    public static Usuario getUsuario(String login, 
                                     String senha) throws Exception{
        Usuario usuario = null;
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "SELECT * "
                   + "FROM tb_usuario "
                   + "WHERE nm_login = ? "
                   + "AND nm_senha = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setString(1, login);
        stmt.setLong(2, senha.hashCode());
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            usuario = new Usuario(
                    rs.getInt("cd_usuario"),
                    rs.getString("nm_login"), 
                    rs.getString("nm_usuario"),
                    rs.getString("nm_cargo")
            );
        }
        rs.close();
        stmt.close();
        con.close();
        return usuario;
    }
    
    public static void addUsuario(String login, 
                                  String nome, 
                                  String cargo, 
                                  String senha) throws Exception{
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "INSERT INTO tb_usuario(nm_login, "
                                          + "nm_usuario, "
                                          + "nm_cargo, "
                                          + "nm_senha) "
                   + "VALUES(?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setString(1, login);
        stmt.setString(2, nome);
        stmt.setString(3, cargo);
        stmt.setLong(4, senha.hashCode());
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void removeUser(int codigo) throws Exception{
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "DELETE FROM tb_usuario WHERE cd_usuario = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setInt(1, codigo);
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    
    public static void changePassword(int codigo, String password) throws Exception{
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "UPDATE tb_usuario "
                   + "SET nm_senha = ? "
                   + "WHERE cd_usuario = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setLong(1, password.hashCode());
        stmt.setInt(2, codigo);
        stmt.execute();
        stmt.close();
        con.close();
    }

    public Usuario(int codigo, String login, String nome, String cargo) {
        this.codigo = codigo;
        this.login = login;
        this.nome = nome;
        this.cargo = cargo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
