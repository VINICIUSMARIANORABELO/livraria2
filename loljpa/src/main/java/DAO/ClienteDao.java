package DAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import service.Cliente;
import util.PersistenceManager;

public class ClienteDao {

	EntityManager em;

	public ClienteDao()  {
		this.em = PersistenceManager.INSTANCE.getEntityManager();
	}

	public Cliente inserir(Cliente cliente) {
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		return cliente;
	}

	public Cliente autenticar(Cliente cliente) {
		Query qry = em.createQuery("SELECT c FROM Cliente c " 
				+ "WHERE c.login = :p1 AND c.senha = :p2 ", Cliente.class);

		qry.setParameter("p1", cliente.getLogin());
		qry.setParameter("p2", cliente.getSenha());

		cliente = (Cliente) qry.getSingleResult();
		cliente.setAutenticacao(true);
		return cliente;
	}

	public void alterar(Cliente cliente) {
		em.merge(cliente);
	}

	public boolean existeCliente(String login) {

		Query qry = em.createQuery("FROM Cliente c" 
		+ " WHERE c.login = :p1 ", Cliente.class);

		qry.setParameter("p1", login);
		try {
			
			Object resultado = (Cliente) qry.getSingleResult();
			
			return resultado != null;
		} catch (Exception e) {
			return false;
		}

	}
}
