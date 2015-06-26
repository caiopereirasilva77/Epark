/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.model;

import br.senai.sc.dao.ClienteDao;
import br.senai.sc.dao.MovimentacaoDao;

import java.text.ParseException;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author caio_pereira
 */
@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
    private Integer id;
    private String nome;
    private String cpf;
    private String placa;
    private String modelo;
    private String tipocliente;

    public Cliente(Integer pid){
        Cliente tCliente = ClienteDao.obterInstancia().clientegetId(pid);
        if(tCliente.getId() > 0){
            id=tCliente.getId();
            nome=tCliente.getNome();
            cpf=tCliente.getCpf();
            placa=tCliente.getPlaca();
            modelo=tCliente.getModelo();
            tipocliente=tCliente.getTipocliente();            
        }
    }

    
    public Cliente(String pplaca){
        Cliente tCliente = ClienteDao.obterInstancia().clientegetPlaca(pplaca);
        if(tCliente.getId() != null){
            id=tCliente.getId();
            nome=tCliente.getNome();
            cpf=tCliente.getCpf();
            placa=tCliente.getPlaca();
            modelo=tCliente.getModelo();
            tipocliente=tCliente.getTipocliente();            
        }
    }

    
    public Cliente(){
        
    }
    
    public boolean estaestacionado() throws ParseException{
        ArrayList<Movimentacao> lista = MovimentacaoDao.obterInstancia().relacionarmovimentacaocliente(id);
        if(lista.size() > 0){
            return true;
        }else{
            return false;
        }
    }
    
    public String getTipocliente() {
        return tipocliente;
    }

    public void setTipocliente(String tipocliente) {
        this.tipocliente = tipocliente;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }


	public String toString() {
        return nome + " - " + cpf;
        
        

} }
