package DAO;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import service.Livro;
import util.PersistenceManager;

public class LivroDao {
	
	EntityManager em;
	
	public LivroDao() throws SQLException{
		this.em = PersistenceManager.INSTANCE.getEntityManager();
	}
	
	public Livro consultar (int codigo){
		/*
		Query qry = em.createQuery("SELECT e FROM Livro WHERE e.codigo = :id",Livro.class);
		qry.setParameter("id", codigo);
		return (Livro) qry.getSingleResult();
		*/
		return em.find(Livro.class, codigo);
	}
	
	public List<Livro> consultar(String titulo){
		TypedQuery<Livro> typeQry = em.createQuery("SELECT e FROM Livro e WHERE upper(TITULO) LIKE :titulo"			
				,Livro.class);
		typeQry.setParameter("titulo", "%"+titulo.toUpperCase()+"%");
		return typeQry.getResultList();
	}

}
