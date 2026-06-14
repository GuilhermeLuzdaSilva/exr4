package com.ti2cc;

import java.sql.*;

public class DAO {
    protected Connection conexao;
    
    public DAO() {
        conexao = null;
    }
    
    public boolean conectar() {
        boolean status = false;
        String driverName = "org.postgresql.Driver";
        
        
        String serverName = "exr4.postgres.database.azure.com";
        String mydatabase = "postgres"; 
        String username = "Guilherme"; 
        String password = "R3!macaco"; 
        
        
        String url = "jdbc:postgresql://" + serverName + ":5432/" + mydatabase + "?sslmode=require";

        try {
            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, username, password);
            status = (conexao != null);
            System.out.println("Conexão com o Azure efetuada com sucesso!");
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
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                status = true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }
}