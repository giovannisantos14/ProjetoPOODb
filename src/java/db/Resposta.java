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
import java.util.ArrayList;
import web.DbListener;

/**
 *
 * @author Giovanni
 */
public class Resposta {
    private int codigo;
    private String descricao;
    private boolean correta;
    
    public static ArrayList<Resposta> getRespostas(int codigo_questao) throws Exception{
        ArrayList<Resposta> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(DbListener.URL);
        String SQL = "SELECT * "
                   + "FROM tb_resposta "
                   + "WHERE cd_questao = ?";
        PreparedStatement stmt = conn.prepareStatement(SQL);
        stmt.setInt(1, codigo_questao);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            list.add(new Resposta(
                    rs.getInt("cd_resposta"),
                    rs.getString("ds_resposta"),
                    rs.getBoolean("ic_correta"))
            );
        }
        rs.close();
        stmt.close();
        conn.close();
        return list;
    }
    
    public static Resposta getResposta(int codigo_resposta,
                                       int codigo_questao) throws Exception{
        Resposta resposta = null;
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "SELECT * "
                   + "FROM tb_resposta "
                   + "WHERE cd_resposta = ? "
                   + "AND cd_questao = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setInt(1, codigo_resposta);
        stmt.setInt(2, codigo_questao);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            resposta = new Resposta(
                    rs.getInt("cd_resposta"),
                    rs.getString("ds_resposta"),
                    rs.getBoolean("ic_correta"));
        }
        rs.close();
        stmt.close();
        con.close();
        return resposta;
    }
    
    public static void addResposta(String descricao, 
                                   boolean correta, 
                                   int codigo_questao) throws Exception{
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "INSERT INTO tb_resposta(cd_questao, "
                                          + "cd_resposta, "
                                          + "ds_resposta,"
                                          + "ic_correta "
                   + "VALUES(?, SELECT MAX(cd_resposta) + 1 FROM tb_respostas WHERE cd_questao = ?, ?,?)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setInt(1, codigo_questao);
        stmt.setInt(2, codigo_questao);
        stmt.setString(3, descricao);
        stmt.setBoolean(4, correta);
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void removeResposta(int codigo_resposta,
                                      int codigo_questao) throws Exception{
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "DELETE FROM tb_resposta "
                   + "WHERE cd_resposta = ? "
                   + "AND cd_questao = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setInt(1, codigo_resposta);
        stmt.setInt(2, codigo_questao);
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static int getRespostaCorreta(int codigo_questao) throws Exception{
        int resposta;
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "SELECT cd_resposta "
                   + "FROM tb_resposta "
                   + "WHERE ic_correta = 1 "
                   + "AND cd_questao = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setInt(1, codigo_questao);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            resposta = rs.getInt("cd_resposta");
        } else {
            resposta = -1;
        }
        rs.close();
        stmt.close();
        con.close();
        return resposta;
    }

    public Resposta(int codigo, 
                    String descricao, 
                    boolean correta) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.correta = correta;
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

    public boolean isCorreta() {
        return correta;
    }

    public void setCorreta(boolean correta) {
        this.correta = correta;
    }
    
}
