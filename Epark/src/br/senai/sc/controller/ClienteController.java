/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.controller;

import br.senai.sc.dao.ClienteDao;
import br.senai.sc.model.Cliente;
import java.util.List;

/**
 *
 * @author Caio Pereira
 */
public class ClienteController {
    
    private static ClienteController instanciaRep;
    
    
    public static ClienteController obterInstancia() {
        if ( instanciaRep == null ) {
            instanciaRep = new ClienteController();
        }
        return instanciaRep;
    }
    
    public void inserir(Cliente cliente) throws Exception{
        
       if (cliente.getCpf().isEmpty()&& cliente.getPlaca().isEmpty() ){
         throw new Exception("Cpf inv�lido");
        }
    
        if ( cliente.getPlaca().isEmpty()|| cliente.getModelo().isEmpty()){
            throw new Exception("Placa inv�lida");
        }    
            
        if ( cliente.getPlaca().isEmpty()&& cliente.getNome().isEmpty()){
            throw new Exception("Placa ou  Nome inv�lidos");    
            
        }
        if (cliente.getModelo().isEmpty()){
            throw new Exception("Modelo inv�lido");
        }    
        if ( cliente.getNome().isEmpty() && cliente.getPlaca().isEmpty()&& cliente.getModelo().isEmpty()
                                         &&cliente.getCpf().isEmpty()){  
            throw new Exception("CPF  inv�lido");
        }else{
             
        }
        
        ClienteDao.obterInstancia().insere(cliente);
        
    }
    public List<Cliente> listarTodos(){
        return ClienteDao.obterInstancia().buscarTodos();
    }
    
    public void alterar(Cliente cliente)throws Exception{
        
        if ( cliente == null ){
            throw new Exception("Selecione um cliente");
        }
        if ( cliente.getNome().trim().isEmpty() ){
  //     }else{    
     //       throw new Exception("Preencha o nome do cliente");
        }
        if ( cliente.getCpf().trim().isEmpty() ){
  //      }else{    
  //          throw new Exception("Preencha o CPF do cliente");
            
       }     
        if ( cliente.getPlaca().trim().equals("") ){
             throw new Exception("Preencha a Placa do ve�culo");
        }
        
        if ( cliente.getModelo().trim().equals("") ){
             throw new Exception("Preencha a Placa do ve�culo");
             
        }
        ClienteDao.obterInstancia().atualizar(cliente);
        
        }
    
    
    public void remover(Cliente cliente) throws Exception{
        if ( cliente == null){
            throw new Exception("Selecione um cliente");
        }
        ClienteDao.obterInstancia().excluir(cliente);
    }
    
    public Cliente consultaExistenciaCliente(Cliente cliente){
    
        return ClienteDao.obterInstancia().consultaExistenciaCliente(cliente);
    }
        
       //  ClienteDao.obterInstancia().consultaExistenciaCliente(cliente);
        
}
    
    
    
    
