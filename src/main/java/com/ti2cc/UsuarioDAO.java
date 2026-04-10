package com.ti2cc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends DAO {
    
    public UsuarioDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }

    // --- MÉTODO INSERIR ---
    public boolean inserir(Usuario usuario) {
        boolean status = false;
        try {  
            // O comando SQL usa '?' como placeholders para evitar ataques
            String sql = "INSERT INTO usuario (codigo, login, senha, sexo) VALUES (?, ?, ?, ?);";
            PreparedStatement st = conexao.prepareStatement(sql);
            
            st.setInt(1, usuario.getCodigo());
            st.setString(2, usuario.getLogin());
            st.setString(3, usuario.getSenha());
            st.setString(4, String.valueOf(usuario.getSexo()));
            
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }

    // --- MÉTODO LISTAR (RETORNAR ARRAY) ---
    public Usuario[] get() {
        Usuario[] usuarios = null;
        try {
            // Conta quantos registros existem para criar o array do tamanho certo
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM usuario");
            
            rs.last();
            usuarios = new Usuario[rs.getRow()];
            rs.beforeFirst();

            int i = 0;
            while(rs.next()) {
                usuarios[i] = new Usuario();
                usuarios[i].setCodigo(rs.getInt("codigo"));
                usuarios[i].setLogin(rs.getString("login"));
                usuarios[i].setSenha(rs.getString("senha"));
                usuarios[i].setSexo(rs.getString("sexo").charAt(0));
                i++;
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return usuarios;
    }

    // --- MÉTODO EXCLUIR ---
    public boolean excluir(int codigo) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            // Complete o comando SQL de delete abaixo:
            String sql = "DELETE FROM usuario WHERE codigo = " + codigo;
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }
    
 // --- MÉTODO ATUALIZAR ---
    public boolean atualizar(Usuario usuario) {
        boolean status = false;
        try {  
            // O SQL define quais campos mudar baseando-se no código (ID)
            String sql = "UPDATE usuario SET login = ?, senha = ?, sexo = ? WHERE codigo = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            
            st.setString(1, usuario.getLogin());
            st.setString(2, usuario.getSenha());
            st.setString(3, String.valueOf(usuario.getSexo()));
            st.setInt(4, usuario.getCodigo());
            
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }
}