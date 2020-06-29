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
    
    public static ArrayList<Quiz> getQuizzes(int limite) throws Exception{
        ArrayList<Quiz> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(DbListener.URL);
        Statement stmt = conn.createStatement();
        String SQL = "SELECT * FROM tb_quiz ORDER BY cd_quiz DESC ";
        if (limite > 0){
            SQL += "LIMIT "+Integer.toString(limite);
        }
        
        ResultSet rs = stmt.executeQuery(SQL);
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
    
    public static ArrayList<Quiz> getMelhoresQuizzes(int limite) throws Exception{
        ArrayList<Quiz> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(DbListener.URL);
        Statement stmt = conn.createStatement();
        String SQL = "SELECT * FROM tb_quiz ORDER BY qt_acertos DESC ";
        if (limite > 0){
            SQL += "LIMIT "+Integer.toString(limite);
        }
        
        ResultSet rs = stmt.executeQuery(SQL);
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
    
    public static ArrayList<Quiz> getQuizzesUsuario(int limite, 
                                                    int codigo_usuario) throws Exception{
        ArrayList<Quiz> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(DbListener.URL);
//        Statement stmt = conn.createStatement();
        String SQL = "SELECT * FROM tb_quiz WHERE cd_usuario = ? ORDER BY cd_quiz DESC ";
        if (limite > 0){
            SQL += "LIMIT "+Integer.toString(limite);
        }
        PreparedStatement stmt = conn.prepareStatement(SQL);
        stmt.setInt(1, codigo_usuario);
        ResultSet rs = stmt.executeQuery();
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
    
    public static double getMediaUsuario(int codigo_usuario) throws Exception{
        double media;
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "SELECT CAST(SUM(qt_acertos) AS REAL)/CAST(COUNT(cd_quiz) AS DOUBLE) AS media "
                    + "FROM tb_quiz "
                    + "WHERE cd_usuario = ?;";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setInt(1, codigo_usuario);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            media = rs.getDouble("media");
        } else {
            media = -1.0;
        }
        rs.close();
        stmt.close();
        con.close();
        System.out.print(media);
        return media;
    }
    
    public static void addQuiz(String nome, 
                                  int acertos, 
                                  int codigo_usuario) throws Exception{
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "INSERT INTO tb_quiz (nm_quiz, "
                                          + "qt_acertos, "
                                          + "cd_usuario) "
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
    
    public static int getNumeroQuiz(int codigo_usuario) throws Exception{
        int numero;
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "SELECT COUNT(cd_quiz)+1 as numero "
                   + "FROM tb_quiz "
                   + "WHERE cd_usuario = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setInt(1, codigo_usuario);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            numero = rs.getInt("numero");
        } else {
            numero = -1;
        }
        rs.close();
        stmt.close();
        con.close();
        return numero;
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
