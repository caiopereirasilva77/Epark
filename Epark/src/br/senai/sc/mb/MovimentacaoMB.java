package br.senai.sc.mb;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.senai.sc.controller.MovimentacaoController;
import br.senai.sc.dao.ClienteDao;
import br.senai.sc.dao.MovimentacaoDao;
import br.senai.sc.model.Cliente;
import br.senai.sc.model.Movimentacao;


@ManagedBean
public class MovimentacaoMB {
		private Cliente cliente;
		private Movimentacao movimentacao;
		private List<Movimentacao> entradas;
		private List<Movimentacao> saidas;
		private MovimentacaoDao movDAO = MovimentacaoDao.obterInstancia();
	    private static MovimentacaoMB instanciaRep;
	    
	    
	    public static MovimentacaoMB obterInstancia() {
	        if ( instanciaRep == null ) {
	            instanciaRep = new MovimentacaoMB();
	        }
	        return instanciaRep;
	    }

	@PostConstruct
	private void init(){
		
		movimentacao = new Movimentacao();
		entradas = new ArrayList<Movimentacao>();
		saidas = new ArrayList<Movimentacao>();
		entradas = movDAO.buscarEntradas();
		saidas = movDAO.buscarSaidas();
	//	cliente = new Cliente();
		
	}


	
	    public String consultarClienteMovimentacao(String pPlaca) throws ParseException{
	         Cliente cliente = new Cliente(pPlaca);
	    //   if ( cliente.getPlaca().isEmpty() ){
	    //       throw new Exception("Placa inválida");
	    //   }
	       
	        if(cliente.getId() != null){
	             if(cliente.estaestacionado()){
	            	 saidas = movDAO.buscarSaidas();
	                 return "Clienteestacionado";
	             }else{
	            	 entradas = movDAO.buscarEntradas();
	                 return "Clientenaoestacionado";
	                 
	             }
	        }else{
	            return "Clientenaoexiste";
	        }
	   }
	    
	    
	    public void estacionar(Movimentacao movimentacao) {
	    	
	    	if (movimentacao.getId()>0) {
	    		  movimentacao.estacionar();
			}else{
				movimentacao.desestacionar();
			}
	      
	        
	        
	    }
	    public void desestacionar(Movimentacao movimentacao) throws Exception{
	        movimentacao.desestacionar();
	    }

		public Cliente getCliente() {
			return cliente;
		}

		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

		public Movimentacao getMovimentacao() {
			return movimentacao;
		}

		public void setMovimentacao(Movimentacao movimentacao) {
			this.movimentacao = movimentacao;
		}

		public List<Movimentacao> getEntradas() {
			return entradas;
		}

		public void setEntradas(List<Movimentacao> entradas) {
			this.entradas = entradas;
		}

		public List<Movimentacao> getSaidas() {
			return saidas;
		}

		public void setSaidas(List<Movimentacao> saidas) {
			this.saidas = saidas;
		}

		public MovimentacaoDao getMovDAO() {
			return movDAO;
		}

		public void setMovDAO(MovimentacaoDao movDAO) {
			this.movDAO = movDAO;
		}

	

}
