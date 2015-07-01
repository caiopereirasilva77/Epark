package br.senai.sc.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.senai.sc.dao.VagaDAO;
import br.senai.sc.model.Vaga;

@ManagedBean
public class VagaMB {

	private Vaga vaga;
	private List<Vaga> vagas;
	private VagaDAO vagaDAO = new VagaDAO();

	@PostConstruct
	private void init() {
		vaga = new Vaga();
		vagas = new ArrayList<Vaga>();
		vagas = vagaDAO.listarTodos();
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (vaga.getId() > 0) {
			vagaDAO.atualizar(vaga);
			vaga = new Vaga();
		} else {
			vagaDAO.inserir(vaga);
			vagas = vagaDAO.listarTodos();
			vaga = new Vaga();
		}
		context.addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Salvo com sucesso!", ""));
		return "";
	}

	public String excluir() {
		vagaDAO.remover(vaga);
		vagas = vagaDAO.listarTodos();
		return "";
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}

}
