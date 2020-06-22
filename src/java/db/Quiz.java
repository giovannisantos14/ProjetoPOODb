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
public class Quiz {
    private int codigo;
    private String nome;
    private int acertos;
    
    public static ArrayList<Quiz> getQuizzes() throws Exception{
        ArrayList<Quiz> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(DbListener.URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tb_quiz");
        while(rs.next()){
            list.add(new Quiz(
                    rs.getInt("cd_quiz"),
                    rs.getString("nm_quiz"),
                    rs.getInt("qt_acertos"))
            );
        }
        rs.close();
        stmt.close();
        conn.close();
        return list;
    }
    
    public static Quiz getQuiz(int codigo) throws Exception{
        Quiz quiz = null;
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "SELECT * "
                   + "FROM tb_quiz "
                   + "WHERE cd_quiz = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setInt(1, codigo);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            quiz = new Quiz(
                    rs.getInt("cd_quiz"),
                    rs.getString("nm_quiz"),
                    rs.getInt("qt_acertos"));
        }
        rs.close();
        stmt.close();
        con.close();
        return quiz;
    }
    
    public static void addQuiz(String nome, 
                                  int acertos, 
                                  int codigo_usuario) throws Exception{
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "INSERT INTO tb_quiz(nm_quiz, "
                                          + "qt_acertos, "
                                          + "cd_usuario "
                   + "VALUES(?,?,?)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setString(1, nome);
        stmt.setInt(2, acertos);
        stmt.setInt(3, codigo_usuario);
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void removeQuiz(int codigo) throws Exception{
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "DELETE FROM tb_quiz WHERE cd_quiz = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setInt(1, codigo);
        stmt.execute();
        stmt.close();
        con.close();
    }

    public Quiz(int codigo, String nome, int acertos) {
        this.codigo = codigo;
        this.nome = nome;
        this.acertos = acertos;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAcertos() {
        return acertos;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }
    
}
