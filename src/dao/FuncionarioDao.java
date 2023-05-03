/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import models.Funcionario;
import conexao.ConnectionFactory;

import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 *
 * @author 42labinfo
 */
public class FuncionarioDao {
    
    public void create(Funcionario f) throws SQLException{
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        
        try{
            sql = con.prepareStatement(
         "insert into funcionario(nome, telefone, email, rg, cpf, salario) values (?,?, ?, ? ,? ,?)") ;
            sql.setString(1, f.getNome());
            sql.setString(2, f.getTelefone());
            sql.setString(3, f.getEmail());
            sql.setString(4, f.getRg());
            sql.setString(5, f.getCpf());
            sql.setFloat(6,f.getSalario());
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
    
    
    public List<Funcionario> read() throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;
        
        List<Funcionario> funcionarios = new ArrayList<>();
        try{
            sql = con.prepareStatement("SELECT * FROM funcionario");
            rs = sql.executeQuery();
            while(rs.next()){
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setTelefone(rs.getString("telefone"));
                f.setEmail(rs.getString("email"));
                f.setRg(rs.getString("rg"));
                f.setCpf(rs.getString("cpf"));
                f.setSalario(rs.getFloat("salario"));
                
                funcionarios.add(f);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        } finally{
            ConnectionFactory.closeConnection(con, sql, rs);
        }
        return funcionarios;
      
    }
    
    
    public void update(Funcionario f) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;
        try{
            sql = con.prepareStatement("update funcionario set nome = ?, telefone = ?, email = ?, rg = ?, cpf = ?, salario = ? where id = ?");
            sql.setString(1, f.getNome());
            sql.setString(2, f.getTelefone());
            sql.setString(3, f.getEmail());
            sql.setString(4, f.getRg());
            sql.setString(5, f.getCpf());
            sql.setFloat(6, f.getSalario());
                                
            sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sucesso");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            ConnectionFactory.closeConnection(con,sql,rs);
        }
    }
    
    public void delete(Funcionario f) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        
        try{
            sql = con.prepareStatement("DELETE FROM funcionario WHERE id = ?");
            sql.setInt(1, f.getId());
            sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "sucesso");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            ConnectionFactory.closeConnection(con,sql);
        }
    }
    
     
    public List<Funcionario> readBusca(String busca) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;
        
        List<Funcionario> funcionarios = new ArrayList<>();
        try{
            sql = con.prepareStatement("SELECT * FROM funcionario WHERE nome LIKE ?");
            sql.setString(1, "%"+busca+"%");
            rs = sql.executeQuery();
            while(rs.next()){
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setTelefone(rs.getString("telefone"));
                f.setEmail(rs.getString("email"));
                f.setRg(rs.getString("rg"));
                f.setCpf(rs.getString("cpf"));
                f.setSalario(rs.getFloat("salario"));
                
                funcionarios.add(f);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        } finally{
            ConnectionFactory.closeConnection(con, sql, rs);
        }
        return funcionarios; 
    }

    
}
