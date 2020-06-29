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
            if(Quiz.getQuizzes(-1).isEmpty()){
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
                    + "VALUES ('De quem é a famosa frase “Penso, logo existo”?') ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_questao (ds_questao) "
                    + "VALUES ('De onde é a invenção do chuveiro elétrico?') ";
                stmt.executeUpdate(SQL); 
                SQL = "INSERT INTO tb_questao (ds_questao) "
                    + "VALUES ('Qual o livro mais vendido no mundo a seguir à Bíblia?') ";
                stmt.executeUpdate(SQL); 
                SQL = "INSERT INTO tb_questao (ds_questao) "
                    + "VALUES ('Quais os países que têm a maior e a menor expectativa de vida do mundo?') ";
                stmt.executeUpdate(SQL); 
                SQL = "INSERT INTO tb_questao (ds_questao) "
                    + "VALUES ('Qual o número mínimo de jogadores numa partida de futebol?') ";
                stmt.executeUpdate(SQL); 
                SQL = "INSERT INTO tb_questao (ds_questao) "
                    + "VALUES ('Quais as duas datas que são comemoradas em novembro?') ";
                stmt.executeUpdate(SQL); 
                SQL = "INSERT INTO tb_questao (ds_questao) "
                    + "VALUES ('Quem pintou \"Guernica\"?') ";
                stmt.executeUpdate(SQL); 
                SQL = "INSERT INTO tb_questao (ds_questao) "
                    + "VALUES ('Em que ordem surgiram os modelos atômicos?') ";
                stmt.executeUpdate(SQL); 
                SQL = "INSERT INTO tb_questao (ds_questao) "
                    + "VALUES ('Em que período da pré-história o fogo foi descoberto?') ";
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
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (1, "
                        + "5, "
                        + "'9',"
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
                        + "'Platão',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (2, "
                        + "2, "
                        + "'Galileu Galilei',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (2, "
                        + "3, "
                        + "'Descartes',"
                        + "1) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (2, "
                        + "4, "
                        + "'Sócrates',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (2, "
                        + "5, "
                        + "'Francis Bacon',"
                        + "0) ";
                stmt.executeUpdate(SQL);
            }
            if(Resposta.getRespostas(3).isEmpty()){
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (3, "
                        + "1, "
                        + "'França',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (3, "
                        + "2, "
                        + "'Inglaterra',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (3, "
                        + "3, "
                        + "'Brasil',"
                        + "1) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (3, "
                        + "4, "
                        + "'Austrália',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (3, "
                        + "5, "
                        + "'Itália',"
                        + "0) ";
                stmt.executeUpdate(SQL);
            }
            if(Resposta.getRespostas(4).isEmpty()){
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (4, "
                        + "1, "
                        + "'O Senhor dos Anéis',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (4, "
                        + "2, "
                        + "'Dom Quixote',"
                        + "1) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (4, "
                        + "3, "
                        + "'O Pequeno Príncipe',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (4, "
                        + "4, "
                        + "'Ela, a Feiticeira',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (4, "
                        + "5, "
                        + "'Um Conto de Duas Cidades',"
                        + "0) ";
                stmt.executeUpdate(SQL);
            }
            
            if(Resposta.getRespostas(5).isEmpty()){
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (5, "
                        + "1, "
                        + "'Japão e Serra Leoa',"
                        + "1) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (5, "
                        + "2, "
                        + "'Austrália e Afeganistão',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (5, "
                        + "3, "
                        + "'Itália e Chade',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (5, "
                        + "4, "
                        + "'Brasil e Congo',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (5, "
                        + "5, "
                        + "'Estados Unidos e Angola',"
                        + "0) ";
                stmt.executeUpdate(SQL);
            }
            if(Resposta.getRespostas(6).isEmpty()){
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (6, "
                        + "1, "
                        + "'8',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (6, "
                        + "2, "
                        + "'10',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (6, "
                        + "3, "
                        + "'9',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (6, "
                        + "4, "
                        + "'5',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (6, "
                        + "5, "
                        + "'7',"
                        + "1) ";
                stmt.executeUpdate(SQL);
            }
            if(Resposta.getRespostas(7).isEmpty()){
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (7, "
                        + "1, "
                        + "'Independência do Brasil e Dia da Bandeira',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (7, "
                        + "2, "
                        + "'Proclamação da República e Dia Nacional da Consciência Negra',"
                        + "1) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (7, "
                        + "3, "
                        + "'Dia do Médico e Dia de São Lucas',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (7, "
                        + "4, "
                        + "'Dia de Finados e Dia Nacional do Livro',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (7, "
                        + "5, "
                        + "'Black Friday e Dia da Árvore',"
                        + "0) ";
                stmt.executeUpdate(SQL);
            }
            if(Resposta.getRespostas(8).isEmpty()){
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (8, "
                        + "1, "
                        + "'Paul Cézanne',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (8, "
                        + "2, "
                        + "'Pablo Picasso',"
                        + "1) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (8, "
                        + "3, "
                        + "'Diego Rivera',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (8, "
                        + "4, "
                        + "'Tarsila do Amaral',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (8, "
                        + "5, "
                        + "'Salvador Dalí',"
                        + "0) ";
                stmt.executeUpdate(SQL);
            }
            if(Resposta.getRespostas(9).isEmpty()){
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (9, "
                        + "1, "
                        + "'Thomson, Dalton, Rutherford, Rutherford-Bohr',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (9, "
                        + "2, "
                        + "'Rutherford-Bohr, Rutherford, Thomson, Dalton',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (9, "
                        + "3, "
                        + "'Dalton, Rutherford-Bohr, Thomson, Rutherford',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (9, "
                        + "4, "
                        + "'Dalton, Thomson, Rutherford-Bohr, Rutherford',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (9, "
                        + "5, "
                        + "'Dalton, Thomson, Rutherford, Rutherford-Bohr',"
                        + "1) ";
                stmt.executeUpdate(SQL);
            }
            if(Resposta.getRespostas(10).isEmpty()){
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (10, "
                        + "1, "
                        + "'Neolítico',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (10, "
                        + "2, "
                        + "'Paleolítico',"
                        + "1) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (10, "
                        + "3, "
                        + "'Idade dos Metais',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (10, "
                        + "4, "
                        + "'Período da Pedra Polida',"
                        + "0) ";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO tb_resposta (cd_questao, "
                                            + "cd_resposta, "
                                            + "ds_resposta,"
                                            + "ic_correta) "
                    + "VALUES (10, "
                        + "5, "
                        + "'Idade Média',"
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
