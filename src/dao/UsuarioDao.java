/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import models.Usuario;
import conexao.ConnectionFactory;

import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import view.Login;
import view.TelaPrincipal;



/**
 *
 * @author 42labinfo
 */
public class UsuarioDao {
    
    public void create(Usuario u) throws SQLException{
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        
        try{
            sql = con.prepareStatement(
         "insert into usuario(login, senha) values (?,?);");
            sql.setString(1, u.getLogin());
            sql.setString(2, u.getSenha());
            sql.executeUpdate();
            
            JOptionPane.showMessageDialog(
                    null, "Cadastrado com sucesso!");
        }catch(SQLException ex){
           JOptionPane.showMessageDialog(
                   null, "Erro ao Cadastrar: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, sql);
        }
        
    }
    
    
    public List<Usuario> read() throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;
        
        List<Usuario> usuarios = new ArrayList<>();
        try{
            sql = con.prepareStatement("SELECT * FROM usuario");
            rs = sql.executeQuery();
            while(rs.next()){
                Usuario c = new Usuario();
                c.setLogin(rs.getString("login"));
                c.setSenha(rs.getString("senha"));
                usuarios.add(c);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        } finally{
            ConnectionFactory.closeConnection(con, sql, rs);
        }
        return usuarios;
      
    }
    
    
    public void update(Usuario c) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;
        try{
            sql = con.prepareStatement("update usuario set login = ?, senha = ? where login= ?");
            sql.setString(1, c.getLogin());
            sql.setString(2, c.getSenha());
            sql.setString(3, c.getLogin());

            sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sucesso");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            ConnectionFactory.closeConnection(con,sql,rs);
        }
    }
    
    public void delete(Usuario c) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        
        try{
            sql = con.prepareStatement("DELETE FROM usuario WHERE login = ?");
            sql.setString(1, c.getLogin());
            sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "sucesso");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            ConnectionFactory.closeConnection(con,sql);
        }
    }
    
     
    public List<Usuario> readBusca(String busca) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;
        
        List<Usuario> usuarios = new ArrayList<>();
        try{
            sql = con.prepareStatement("SELECT * FROM usuario WHERE login LIKE ?");
            sql.setString(1, "%"+busca+"%");
            rs = sql.executeQuery();
            while(rs.next()){
                Usuario c = new Usuario();
                c.setLogin(rs.getString("login"));
                c.setSenha(rs.getString("senha"));
                usuarios.add(c);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        } finally{
            ConnectionFactory.closeConnection(con, sql, rs);
        }
        return usuarios; 
    }

     public void loginSistema(String login, String senha) throws SQLException{
         Connection con = ConnectionFactory.getConnection();
         PreparedStatement sql = null;
         ResultSet rs = null;
         try{
            sql = con.prepareStatement("select * from usuario;");
            rs = sql.executeQuery();
            
            while(rs.next()){
                String user = rs.getString("login");
                String pass = rs.getString("senha");
                if(login.equals(user) && senha.equals(pass)){
                    JOptionPane.showMessageDialog(null, "logado com sucesso!");
                    new Login().setVisible(true);
                    new TelaPrincipal().setVisible(true);
                    break;
                }else{
                    JOptionPane.showMessageDialog(null, "Login ou senha incorretos!");
                }
            }
         }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        } finally{
            ConnectionFactory.closeConnection(con, sql, rs);
        }
     }
}
