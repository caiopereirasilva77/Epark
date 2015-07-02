package br.senai.sc.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.senai.sc.dao.ClienteDao;
import br.senai.sc.model.Cliente;


@ManagedBean
public class ClienteMB {
	
	private Cliente cliente;
	private String confCpfCliente;
	private List<Cliente> clientes;
	private ClienteDao clienteDAO = new ClienteDao();


	@PostConstruct
	private void init(){
		cliente = new Cliente();
		 clientes= new ArrayList<Cliente>();

		 clientes = clienteDAO.buscarTodos();

	}

	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
//		if (cliente.getCpf().equals(confCpfCliente)&& cliente.getId() ==null){
//			
//			context.addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "cpf ja existe", ""));
		
		if (cliente.getNome().isEmpty()&& 
			cliente.getCpf().isEmpty()&&
			cliente.getPlaca()!=null&& 
			cliente.getModelo()!=null&&
			cliente.getTipocliente()!=null) {
			
		cliente.setTipocliente("A");
		clienteDAO.insere(cliente);
		clientes = clienteDAO.buscarTodos();
		cliente = new Cliente();
		}
		
			if(cliente.getId() == null&& 
			   cliente.getNome()!=null&&
			   cliente.getCpf()!=null&&
			   cliente.getPlaca()!=null&& 
			   cliente.getModelo()!=null&&
			   cliente.getTipocliente()!=null){
				cliente.setTipocliente("M");
						
				clienteDAO.insere(cliente);
				clientes = clienteDAO.buscarTodos();
				cliente = new Cliente();
			}
			
			if (cliente.getId()>0) {
				
				clienteDAO.atualizar(cliente);
				clientes = clienteDAO.buscarTodos();
				cliente = new Cliente();
				
				
			}
				
			
			
//		}else{
//			clienteDAO.insere(cliente);
//			clientes = clienteDAO.buscarTodos();
//			context.addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", ""));
			
		
		return "";
	}
	
	
	public String excluir() throws Exception{
		ClienteDao dao = new ClienteDao();
		dao.excluir(cliente);
		clientes = dao.buscarTodos();
		cliente = new Cliente();
		return"";
		
		
	}

	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getConfSenha() {
		return confCpfCliente;
	}
	public void setConfSenha(String confSenha) {
		this.confCpfCliente = confSenha;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public ClienteDao getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(ClienteDao clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

}
