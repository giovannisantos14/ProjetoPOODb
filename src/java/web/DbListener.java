/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

//import db.Category;
//import db.Transaction;
import db.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Giovanni
 */
public class DbListener implements ServletContextListener {
    public static final String URL = "jdbc:sqlite:quiz.db";
    
    public static String exceptionMessage = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String step = "Iniciando criação do banco de dados";
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(URL);
            Statement stmt = conn.createStatement();
            
            String SQL = null;
            
            step = "Criação da tabela tb_usuario";
            SQL = "CREATE TABLE IF NOT EXISTS tb_usuario("
                        + "cd_usuario INTEGER PRIMARY KEY, "
                        + "nm_usuario VARCHAR(50) NOT NULL, "
                        + "nm_login VARCHAR(20) NOT NULL, "
                        + "nm_senha LONG, "
                        + "nm_cargo VARCHAR(20) NOT NULL "
                        + ")";
            stmt.executeUpdate(SQL);
            
            step = "Criação dos usuarios padrão";
            if(Usuario.getUsuarios().isEmpty()){
                SQL = "INSERT INTO tb_usuarios (nm_usuario, "
                                            + "nm_login, "
                                            + "nm_senha, "
                                            + "nm_role) "
                    + "VALUES ('Administrador', "
                        + "'admin', "
                        + "'"+("admin".hashCode())+"', "
                        + "'ADMIN') ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO users(nm_usuario, "
                        + "nm_login, "
                        + "nm_senha, "
                        + "nm_role) "
                    + "VALUES('João da Silva', "
                        + "'joao', "
                        + "'"+("123".hashCode())+"', "
                        + "'USER')";
                stmt.executeUpdate(SQL);        
            }
            
            step = "Criação da tabela tb_quiz";
            SQL = "CREATE TABLE IF NOT EXISTS tb_quiz("
                        + "cd_quiz INTEGER PRIMARY KEY, "
                        + "nm_quiz VARCHAR(60) NOT NULL, "
                        + "qt_acertos SMALLINT NOT NULL, "
                        + "cd_usuario INTEGER,"
                    + "FOREIGN KEY (cd_usuario) "
                    + "REFERENCES tb_usuario(cd_usuario) "
                    + ")";
            stmt.executeUpdate(SQL);
            
            step = "Criação da tabela tb_questao";
            SQL = "CREATE TABLE IF NOT EXISTS tb_questao("
                        + "cd_questao INTEGER PRIMARY KEY, "
                        + "ds_questao VARCHAR(300) NOT NULL "
                    + ")";
            stmt.executeUpdate(SQL);
            
            
            step = "Criação da tabela tb_resposta";
            SQL = "CREATE TABLE IF NOT EXISTS tb_resposta("
                        + "cd_questao INTEGER NOT NULL,"
                        + "cd_resposta INTEGER NOT NULL, "
                        + "ds_resposta VARCHAR(300) NOT NULL, "
                        + "ic_correta SMALLINT NOT NULL, "
                    + "PRIMARY KEY (cd_questao, cd_resposta), "
                    + "FOREIGN KEY (cd_questao)"
                    + ")";
            stmt.executeUpdate(SQL);
            
            stmt.close();
            conn.close();
            
        }catch (Exception ex) {
            exceptionMessage = step + ": " + ex;
        }
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
