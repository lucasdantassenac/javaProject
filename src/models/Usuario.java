/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author 42labinfo
 */
public class Usuario {
    private String login;
    private String senha;

    public Usuario(){}
    public Usuario(String l, String s){
        this.login = l;
        this.senha = s;
    }
    
    public String getLogin(){
        return this.login;
    }
    public void setLogin(String l){
        this.login = l;
    }
    public String getSenha(){
        return this.senha;
    }
    public void setSenha(String s){
        this.senha = s;
    }
}

