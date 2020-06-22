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
public class Questao {
    private int codigo;
    private String descricao;
    
    public static ArrayList<Questao> getQuestoes() throws Exception{
        ArrayList<Questao> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(DbListener.URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tb_questao");
        while(rs.next()){
            list.add(new Questao(
                    rs.getInt("cd_questao"),
                    rs.getString("ds_questao"))
            );
        }
        rs.close();
        stmt.close();
        conn.close();
        return list;
    }
    
    public static Questao getQuestao(int codigo) throws Exception{
        Questao questao = null;
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "SELECT * "
                   + "FROM tb_questao "
                   + "WHERE cd_questao = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setInt(1, codigo);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            questao = new Questao(
                    rs.getInt("cd_questao"),
                    rs.getString("ds_questao"));
        }
        rs.close();
        stmt.close();
        con.close();
        return questao;
    }
    
    public static void addQuestao(String descricao) throws Exception{
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "INSERT INTO tb_questao(ds_questao "
                   + "VALUES(?)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setString(1, descricao);
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void removeQuestao(int codigo) throws Exception{
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "DELETE FROM tb_questao WHERE cd_questao = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setInt(1, codigo);
        stmt.execute();
        stmt.close();
        con.close();
    }

    public Questao(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
