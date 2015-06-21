/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.model;

import br.senai.sc.dao.VagaDao;
import java.util.ArrayList;

/**
 *
 * @author caio_pereira
 */
public class Vaga {
    private int id ;
    private Integer numeroVaga;
    private String tipoVaga;
    private String tipoValor;
    private double valorUnit;
    private int status;
    
    public Vaga(){
    }   
    
    public Vaga(Integer pid){    
        Vaga tVaga = VagaDao.obterInstancia().vagagetId(pid);
        if(tVaga.getId() > 0){
            id=tVaga.getId();
            numeroVaga=tVaga.getNumeroVaga();
             tipoVaga=tVaga.getTipoVaga();
             tipoValor=tVaga.getTipoValor();
             valorUnit=tVaga.getValorUnit();
             status=tVaga.getStatus();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getNumeroVaga() {
        return numeroVaga;
    }

    public void setNumeroVaga(Integer numeroVaga) {
        this.numeroVaga = numeroVaga;
    }

    public String getTipoVaga() {
        return tipoVaga;
    }

    public void setTipoVaga(String tipoVaga) {
        this.tipoVaga = tipoVaga;
    }

    public String getTipoValor() {
        return tipoValor;
    }

    public void setTipovalor(String tipoValor) {
        this.tipoValor = tipoValor;
    }

    public double getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(double valorUnit) {
        this.valorUnit = valorUnit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }        

    public ArrayList<Vaga> listarVagasLivreAvulso(){
        return VagaDao.obterInstancia().listarVagasLivresAvulso();
    }

    public ArrayList<Vaga> listarVagasLivreMensalista(){
        return VagaDao.obterInstancia().listarVagasLivresMensalista();
    }
}