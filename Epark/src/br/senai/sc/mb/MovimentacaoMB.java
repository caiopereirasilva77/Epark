package br.senai.sc.mb;

import java.text.ParseException;
import java.util.List;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.senai.sc.dao.ClienteDao;
import br.senai.sc.dao.MovimentacaoDao;
import br.senai.sc.dao.VagaDAO;
import br.senai.sc.model.Cliente;
import br.senai.sc.model.Movimentacao;

@ManagedBean
public class MovimentacaoMB {
	private List<Movimentacao> movimentacoes;
	private MovimentacaoDao movimentacaoDAO;
	private ClienteDao clienteDAO;
	private VagaDAO vagaDAO;
	private Movimentacao movimentacao;
	private String consultaPlaca;

	@PostConstruct
	private void init() {
		consultaPlaca = "";
		movimentacao = new Movimentacao();
		movimentacaoDAO = MovimentacaoDao.obterInstancia();
		clienteDAO = ClienteDao.obterInstancia();
		vagaDAO = vagaDAO.obterInstancia();
		movimentacoes = movimentacaoDAO.listarTodos();
	}

	public String consultarPlaca() throws ParseException {
		FacesContext context = FacesContext.getCurrentInstance();
		movimentacao.setCliente(clienteDAO.burcarPorPlaca(consultaPlaca));

		if (movimentacao.getCliente().getId() == null) {
			context.addMessage("", new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Cliente não cadastrado!", ""));
			return "";
		} else {
			Movimentacao verMov = new Movimentacao();
			verMov = movimentacaoDAO.verStatusCliente(movimentacao.getCliente().getId());
			if (verMov.getDatasaida() == null) {				
				return "sairVaga.jsf";
			} else {
				return "estacionar.jsf";
			}
		}
	}

	public void estacionar(Movimentacao movimentacao) {
		if (movimentacao.getId() > 0) {
			movimentacao.estacionar();
		} else {
			movimentacao.desestacionar();
		}
	}

	public void sairVaga(Movimentacao movimentacao) throws Exception {
		movimentacao.desestacionar();
	}

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public String getConsultaPlaca() {
		return consultaPlaca;
	}

	public void setConsultaPlaca(String consultaPlaca) {
		this.consultaPlaca = consultaPlaca;
	}

}
