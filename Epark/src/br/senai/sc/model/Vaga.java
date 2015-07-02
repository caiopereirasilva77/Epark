package br.senai.sc.model;

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
public class Vaga {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
	
	@Column(name="Numero_vaga")
    private Integer numeroVaga;
	
	@Column(name="Tipo_vaga")
    private String tipoVaga;
	
	@Column(name="Tipo_valor")
    private String tipoValor;
	
	@Column(name="Valor_unit")
    private double valorUnit;
    private int status;
    
    public Vaga(){
    
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

	public void setTipoValor(String tipoValor) {
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

    
}
