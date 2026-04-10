package com.ti2cc;

import java.sql.*;

public class DAO {
    protected Connection conexao;
    
    public DAO() {
        conexao = null;
    }
    
    public boolean conectar() {
        boolean status = false;
        // 1. Defina as strings de configuração
        String driverName = "org.postgresql.Driver";
        String serverName = "localhost";
        String mydatabase = "usuarios";
        // A URL segue o padrão: jdbc:postgresql://servidor:porta/banco
        String url = "jdbc:postgresql://" + serverName + ":5432/" + mydatabase;
        String username = "ti2cc";
        String password = "ti@cc"; // A senha que você usa no phpPgAdmin

        try {
            // 2. Carregar o Driver na memória
            Class.forName(driverName);
            
            // 3. Tentar estabelecer a conexão
            conexao = DriverManager.getConnection(url, username, password);
            status = (conexao != null);
            System.out.println("Conexão efetuada com sucesso!");
        } catch (ClassNotFoundException e) { 
            System.err.println("Erro: Driver JDBC não encontrado! " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro: Falha na conexão com o banco! " + e.getMessage());
        }
        return status;
    }
    
    public boolean close() {
        boolean status = false;
        try {
            // 4. Fechar a conexão se ela estiver aberta
            conexao.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }
}