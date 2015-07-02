package br.senai.sc.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.senai.sc.model.Cliente;
import br.senai.sc.util.JpaUtil;

/**
 *
 * @author Caio Pereira
 */
public class ClienteDao extends BaseDAO {

	private static ClienteDao instanciaRep;
	EntityManager em = JpaUtil.getEntityManager();

	// //SINGLETON//
	public static ClienteDao obterInstancia() {
		if (instanciaRep == null) {
			instanciaRep = new ClienteDao();
		}
		return instanciaRep;
	}

	public void insere(Cliente cliente) {
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
	}

	public List<Cliente> buscarTodos() {
		return em.createQuery("select d from Cliente d").getResultList();
	}

	public void atualizar(Cliente cliente) {
		em.getTransaction().begin();
		em.merge(cliente);
		em.getTransaction().commit();
	}

	public Cliente clientegetId(Integer id) {
		return (Cliente) em.find(Cliente.class, id);
	}

	public Cliente clientegetPlaca(String placa) {
		return (Cliente) em
				.createQuery("select c from Cliente c where c.placa= :placa")
				.setParameter("placa", placa).getSingleResult();
	}

	public void excluir(Cliente cliente) throws Exception {
		//cliente = em.find(Cliente.class, cliente.getId());
		em.getTransaction().begin();
		em.remove(cliente);
		em.getTransaction().commit();
	}

	public Cliente consultaExistenciaCliente(Cliente cliente) {
		return (Cliente) em
				.createQuery("select c from Cliente c where c.placa= :placa")
				.setParameter("placa", cliente.getPlaca()).getSingleResult();
	}
}
