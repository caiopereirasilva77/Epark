/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.senai.sc.model.Vaga;
import br.senai.sc.util.JpaUtil;

public class VagaDAO {

	private static VagaDAO instancia;
	private EntityManager em = JpaUtil.getEntityManager();

	public static VagaDAO obterInstancia() {
		if (instancia == null) {
			instancia = new VagaDAO();
		}
		return instancia;
	}

	public void inserir(Vaga vaga) {
		em.getTransaction().begin();
		em.persist(vaga);
		em.getTransaction().commit();
	}

	public void remover(Vaga vaga) {
		em.getTransaction().begin();
		em.remove(vaga);
		em.getTransaction().commit();
	}

	public List<Vaga> listarTodos() {
		return em.createQuery("select v from Vaga v").getResultList();
	}

	public void atualizar(Vaga vaga) {
		em.getTransaction().begin();
		em.merge(vaga);
		em.getTransaction().commit();
	}

	public Vaga buscarPosId(int id) {
		return em.find(Vaga.class, id);
	}

	public Vaga buscarPorNumero(int numero) {
		return (Vaga) em
				.createQuery(
						"select v from Vaga v where v.numeroVaga = :numero")
				.setParameter("numero", numero).getSingleResult();
	}

	public List<Vaga> listarVagas(String tipo, String status) {
		return em
				.createQuery(
						"select v from Vaga v where v.tipoVaga =:tipo and v.status = :status")
				.setParameter("status", status).setParameter("tipo", tipo)
				.getResultList();
	}
}



