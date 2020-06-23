/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.Quiz;
import db.Questao;
import db.Resposta;
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
 * @author Felipe Borreli
 */
public class DbListener implements ServletContextListener {
    public static final String path = System.getProperty("user.home") + "/Documents/NetBeansProjects/ProjetoPOODb/quiz.db";
    public static final String URL = "jdbc:sqlite:" + path;
    
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
                SQL = "INSERT INTO tb_usuario (nm_usuario, "
                                            + "nm_login, "
                                            + "nm_senha, "
                                            + "nm_cargo) "
                    + "VALUES ('Administrador', "
                        + "'admin', "
                        + "'"+("admin".hashCode())+"', "
                        + "'ADMIN') ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_usuario(nm_usuario, "
                        + "nm_login, "
                        + "nm_senha, "
                        + "nm_cargo) "
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
            
            step = "Criação dos quizzes padrão";
            if(Quiz.getQuizzes().isEmpty()){
                SQL = "INSERT INTO tb_quiz (nm_quiz, "
                                            + "qt_acertos, "
                                            + "cd_usuario) "
                    + "VALUES ('Administrador - 1', "
                        + "9, "
                        + "1) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_quiz (nm_quiz, "
                                            + "qt_acertos, "
                                            + "cd_usuario) "
                    + "VALUES ('Joao - 1', "
                        + "5, "
                        + "2) ";
                stmt.executeUpdate(SQL);       
            }
            
            step = "Criação da tabela tb_questao";
            SQL = "CREATE TABLE IF NOT EXISTS tb_questao("
                        + "cd_questao INTEGER PRIMARY KEY, "
                        + "ds_questao VARCHAR(300) NOT NULL "
                    + ")";
            stmt.executeUpdate(SQL);
            
            step = "Criação das questões padrão";
            if(Questao.getQuestoes().isEmpty()){
                SQL = "INSERT INTO tb_questao (ds_questao) "
                    + "VALUES ('Com quantos paus se faz uma canoa?') ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_questao (ds_questao) "
                    + "VALUES ('Teste?') ";
                stmt.executeUpdate(SQL);      
            }
            
            step = "Criação da tabela tb_resposta";
            SQL = "CREATE TABLE IF NOT EXISTS tb_resposta( "
                        + "cd_questao INTEGER NOT NULL,"
                        + "cd_resposta SMALLINT NOT NULL, "
                        + "ds_resposta VARCHAR(300) NOT NULL, "
                        + "ic_correta SMALLINT NOT NULL, "
                    + "PRIMARY KEY (cd_questao, cd_resposta), "
                    + "FOREIGN KEY (cd_questao) "
                    + "REFERENCES tb_questao (cd_questao)"
                    + ")";
            stmt.executeUpdate(SQL);
            
            step = "Criação das respostas padrão";
            if(Resposta.getRespostas(1).isEmpty()){
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (1, "
                        + "1, "
                        + "'5',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (1, "
                        + "2, "
                        + "'3',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (1, "
                        + "3, "
                        + "'1',"
                        + "1) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (1, "
                        + "4, "
                        + "'7',"
                        + "0) ";
                stmt.executeUpdate(SQL);
            }
            if(Resposta.getRespostas(2).isEmpty()){
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (2, "
                        + "1, "
                        + "'TESTE1',"
                        + "1) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (2, "
                        + "2, "
                        + "'TESTE2',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (2, "
                        + "3, "
                        + "'TESTE3',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (2, "
                        + "4, "
                        + "'TESTE4',"
                        + "0) ";
                stmt.executeUpdate(SQL);
            }
            
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
