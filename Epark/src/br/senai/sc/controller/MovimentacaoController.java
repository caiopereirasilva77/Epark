/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.controller;

import br.senai.sc.dao.MovimentacaoDao;
import br.senai.sc.model.Cliente;
import br.senai.sc.model.Movimentacao;
import br.senai.sc.model.Vaga;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Caio Pereira
 */
public class MovimentacaoController {
    
    private static MovimentacaoController instanciaRep;
    public static MovimentacaoController obterInstancia() {
        if ( instanciaRep == null ) {
            instanciaRep = new MovimentacaoController();
        }
        return instanciaRep;
    }
    public String consultarClienteMovimentacao(String pPlaca) throws ParseException{
         Cliente cliente = new Cliente(pPlaca);
    //   if ( cliente.getPlaca().isEmpty() ){
    //       throw new Exception("Placa inválida");
    //   }
       
        if(cliente.getId() != null){
             if(cliente.estaestacionado()){
                 return "Clienteestacionado";
             }else{
                 return "Clientenaoestacionado";
             }
        }else{
            return "Clientenaoexiste";
        }
   }
    public void estacionar(Movimentacao movimentacao) {
        movimentacao.estacionar();
    }
    public void desestacionar(Movimentacao movimentacao) throws Exception{
        movimentacao.desestacionar();
    }

}