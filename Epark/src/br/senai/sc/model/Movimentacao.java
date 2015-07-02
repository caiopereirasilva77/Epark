/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.model;

import br.senai.sc.dao.MovimentacaoDao;
import br.senai.sc.dao.VagaDAO;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



/**
 *
 * @author Caio Pereira
 */
@Entity
public class Movimentacao {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
    private String tipocliente;
   
    private Date dataentrada;
    private Date datasaida;
    private Double vlrtotal;
    @ManyToOne
    private Cliente cliente;
    private Integer id;
    @ManyToOne
    private Vaga vaga;
  
   
    public Movimentacao(){   
    }

  
    public Movimentacao(Integer pid){
        try {
            Movimentacao movimentacao = MovimentacaoDao.obterInstancia().movimentacaoget(pid);
            if(movimentacao.getId() > 0){
                id=movimentacao.getId();
                tipocliente=movimentacao.getTipocliente();
               
                dataentrada=movimentacao.getDataentrada();
                datasaida=movimentacao.getDatasaida();
                vlrtotal=movimentacao.getVlrtotal();
                cliente=movimentacao.getCliente();
                vaga=movimentacao.getVaga();
            }
        } catch (ParseException ex) {
        }
    }
  
    public Movimentacao(String pPlaca){
        try {
            Movimentacao movimentacao = MovimentacaoDao.obterInstancia().movimentacaoSaidaGet(pPlaca);
            if(movimentacao.getId() > 0){
                id=movimentacao.getId();
                tipocliente=movimentacao.getTipocliente();
        //        placa=movimentacao.getCliente().getPlaca();
                dataentrada=movimentacao.getDataentrada();
                datasaida=movimentacao.getDatasaida();
                vlrtotal=movimentacao.getVlrtotal();
                cliente=movimentacao.getCliente();
                vaga=movimentacao.getVaga();
            }
        } catch (ParseException ex) {
        }
    }    

    public void estacionar(){
        MovimentacaoDao dao = new MovimentacaoDao();
        VagaDAO vagadao = new VagaDAO();
        vagadao.alterarStatusEstacionar(this.vaga.getId());
        dao.insere(this);
    }
    
    public void desestacionar(){
        MovimentacaoDao dao = new MovimentacaoDao();
        VagaDAO vagadao = new VagaDAO();
        vagadao.alterarStatusDesestacionar(this.vaga.getId());
        dao.inserirSaida(this);        
    }
    
    public double calcularSaida(Date dataSaidaDate){
        double valor = 0.0;
        if(this.getCliente().getTipocliente().equals("A")){

            Calendar dataSaidaCal = Calendar.getInstance();  
            dataSaidaCal.setTime(dataSaidaDate);  
            Calendar dataEntradaCal = Calendar.getInstance();  
            dataEntradaCal.setTime(this.getDataentrada());  

            long diferenca = dataSaidaCal.getTimeInMillis() - dataEntradaCal.getTimeInMillis();  
            int diferencaHoras =  1+(int)(diferenca / (60 * 60 * 1000));

            valor = this.getVaga().getValorUnit() * diferencaHoras;
        }else{
            valor = this.getVaga().getValorUnit();                
        }
        return valor;
    }
    
    
    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }
  
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
  

    public String getTipocliente() {
        return tipocliente;
    }

    public void setTipocliente(String tipocliente) {
        this.tipocliente = tipocliente;
    }

  

    public Date getDataentrada() {
        return dataentrada;
    }

    public void setDataentrada(Date dataentrada) {
        this.dataentrada = dataentrada;
    }

    public Date getDatasaida() {
        return datasaida;
    }

    public void setDatasaida(Date datasaida) {
        this.datasaida = datasaida;
    }

    public Double getVlrtotal() {
        return vlrtotal;
    }

    public void setVlrtotal(Double vlrtotal) {
        this.vlrtotal = vlrtotal;
    }
  
  
    
    
}
