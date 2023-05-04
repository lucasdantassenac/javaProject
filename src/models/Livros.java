/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author 42labinfo
 */
public class Livros {
    private int id;
    private String titulo;
    private int pageNumber;
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getTitulo(){
        return this.titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public int getPageNumber(){
        return this.pageNumber;
    }
    
    public void setPageNumber(int pageNumber){
        this.pageNumber = pageNumber;
    }
    
    
}
