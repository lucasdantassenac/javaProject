/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import models.Livros;
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
public class LivroDao {
    
    public void create(Livros livro) throws SQLException{
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        
        try{
            sql = con.prepareStatement(
         "insert into livros(titulo, paginas) values (?,?)") ;
            sql.setString(1, livro.getTitulo());
            sql.setInt(2, livro.getPageNumber());

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
    
    
    public List<Livros> read() throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;
        
        List<Livros> listaLivros = new ArrayList<>();
        try{
            sql = con.prepareStatement("SELECT * FROM livros");
            rs = sql.executeQuery();
            while(rs.next()){
                Livros livro = new Livros();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setPageNumber(rs.getInt("paginas"));
                listaLivros.add(livro);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        } finally{
            ConnectionFactory.closeConnection(con, sql, rs);
        }
        return listaLivros;
      
    }
    
    
    public void update(Livros livro) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;
        try{
            sql = con.prepareStatement("update livros set titulo = ?, paginas = ? where id = ?");
            sql.setString(1, livro.getTitulo());
            sql.setInt(2, livro.getPageNumber());
            sql.setInt(3, livro.getId());

            sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sucesso");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            ConnectionFactory.closeConnection(con,sql,rs);
        }
    }
    
    public void delete(Livros livro) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        
        try{
            sql = con.prepareStatement("DELETE FROM livros WHERE id = ?");
            sql.setInt(1, livro.getId());
            sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "sucesso");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            ConnectionFactory.closeConnection(con,sql);
        }
    }
    
     
    public List<Livros> readBusca(String busca) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;
        
        List<Livros> listaLivros = new ArrayList<>();
        try{
            sql = con.prepareStatement("SELECT * FROM livros WHERE titulo LIKE ?");
            sql.setString(1, "%"+busca+"%");
            rs = sql.executeQuery();
            while(rs.next()){
                Livros livro = new Livros();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setPageNumber(rs.getInt("paginas"));
                listaLivros.add(livro);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        } finally{
            ConnectionFactory.closeConnection(con, sql, rs);
        }
        return listaLivros; 
    }

    
}
