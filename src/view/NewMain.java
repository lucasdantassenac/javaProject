package view;

import models.Cliente;
import models.Servico;

public class NewMain {

   
    public static void main(String[] args) {
        //criando uma instância de um objeto
        Servico s = new Servico();
        Servico s2 = new Servico();
        
        
        
        
       //entrada
        s.setId(1);
        s.setDescricao("Teste");
        s.setValor(100);
        
        s2.setId(2);
        s2.setDescricao("Flamengo");
        s2.setValor(1000);
       
        //saída
        linha();
        System.out.println("Serviço 1");
        System.out.println("Id: " + s.getId());
        System.out.println("Descrição: " + s.getDescricao());
        System.out.println("Valor: R$ " + s.getValor());
        linha();
        System.out.println("Serviço 2");
        System.out.println("Id: " + s2.getId());
        System.out.println("Descrição: " + s2.getDescricao());
        System.out.println("Valor: R$ " + s2.getValor());
        linha();
        
        
        
    }
    public static void linha(){
        System.out.println("************************");
    }
    
}
