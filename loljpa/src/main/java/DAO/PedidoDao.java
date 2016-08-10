package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import service.ItemCarrinho;
import service.Livro;
import service.Pedido;
import util.PersistenceManager;


public class PedidoDao {
	EntityManager em;

	public PedidoDao() throws SQLException {
		this.em = PersistenceManager.INSTANCE.getEntityManager();
	}

	public void incluir(Pedido pedido) {
		em.getTransaction().begin();
		em.persist(pedido);
		em.getTransaction().commit();
		
		/*

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO PEDIDO (DATA_PEDIDO, COD_CLIENTE, STATUS, PAGAMENTO, TOTAL) VALUES (?,?,?,?,?)");

		StringBuffer sqlItem = new StringBuffer();
		sqlItem.append("INSERT INTO ITEM_PEDIDO (COD_LIVRO, QTD, COD_PEDIDO) VALUES (?,?,?)");

		try {
			this.em = FabricaConexao.getConexao();
			PreparedStatement consulta = em.prepareStatement(sql.toString());
			consulta.setDate(1, dataPedido);
			consulta.setInt(2, pedido.getCliente().getCodigo());
			consulta.setString(3, pedido.getStatus());
			consulta.setString(4, pedido.getPagamento());
			consulta.setDouble(5, pedido.getValorTotal());
			consulta.execute();

			PreparedStatement consultaItem = em.prepareStatement(sqlItem.toString());
			for (ItemCarrinho item : pedido.getItens()) {
				consultaItem.setInt(1, item.getLivro().getCodigo());
				consultaItem.setInt(2, item.getQtd());
				consultaItem.setInt(3, getCodUltimoPedido(pedido.getCliente().getCodigo()));
				consultaItem.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}

	public int getCodUltimoPedido(int codCliente) {
		/*
		int cod = 1;
		try {
			PreparedStatement consulta = em
					.prepareStatement("SELECT MAX(COD_PEDIDO) AS COD_PEDIDO FROM PEDIDO WHERE COD_CLIENTE = ?");
			consulta.setInt(1, codCliente);
			ResultSet resultado = consulta.executeQuery();
			if (resultado.next()) {
				cod = resultado.getInt("COD_PEDIDO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			;
		}
		return cod;
		*/
		
		 CriteriaBuilder builder = em.getCriteriaBuilder();
		 CriteriaQuery<Integer> cquery = builder.createQuery(Integer.class); 
		 Root<Pedido> root = cquery.from(Pedido.class);
		 Predicate predicate = builder.equal(root.get("cliente").get("codigo"), codCliente);
		 cquery.where(predicate);
		 Path<Integer> codPedido = root.get("codigo");
		 Expression<Integer> max = builder.max(codPedido);
		 cquery.select(max);
		 TypedQuery<Integer> qry = em.createQuery(cquery);
		 return qry.getSingleResult();
		 
	}

	public List<Pedido> listarPedido(int codCliente) {
		/*
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT pedido.COD_PEDIDO, pedido.DATA_PEDIDO, pedido.PAGAMENTO, pedido.STATUS ");
		sql.append("FROM pedido WHERE pedido.COD_CLIENTE = ?");

		try {
			PreparedStatement consulta = em.prepareStatement(sql.toString());
			consulta.setInt(1, codCliente);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				Pedido pedido = new Pedido();

				pedido.setCodigo(resultado.getInt("COD_PEDIDO"));
				pedido.setDataPedido(resultado.getDate("DATA_PEDIDO"));
				pedido.setPagamento(resultado.getString("PAGAMENTO"));
				pedido.setStatus(resultado.getString("STATUS"));

				pedidos.add(pedido);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		CriteriaBuilder builder =  em.getCriteriaBuilder();
		CriteriaQuery<Pedido> cquery = builder.createQuery(Pedido.class);
		Root<Pedido> root = cquery.from(Pedido.class);
        Predicate predicate = builder.equal(root.get("cliente").get("codigo"), codCliente);
		cquery.where(predicate);
		TypedQuery<Pedido> pedidos = em.createQuery(cquery);
		return pedidos.getResultList();
	}

	public List<ItemCarrinho> listarItem(int codPedido) {
		/*
		ArrayList<ItemCarrinho> itens = new ArrayList<ItemCarrinho>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT estoque.IMAGEM, estoque.TITULO, estoque.preco , item_pedido.QTD, pedido.TOTAL ");
		sql.append("FROM cliente INNER JOIN pedido ON cliente.COD_CLIENTE = pedido.COD_CLIENTE ");
		sql.append("INNER JOIN item_pedido ON pedido.COD_PEDIDO = item_pedido.COD_PEDIDO ");
		sql.append("INNER JOIN estoque ON item_pedido.COD_LIVRO = estoque.COD_LIVRO ");
		sql.append("WHERE pedido.COD_PEDIDO = ?");

		try {
			PreparedStatement consulta = em.prepareStatement(sql.toString());
			consulta.setInt(1, codPedido);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {

				ItemCarrinho item = new ItemCarrinho();
				Livro livro = new Livro();
				livro.setImagem(resultado.getString("IMAGEM"));
				livro.setTitulo(resultado.getString("TITULO"));
				livro.setPreco(resultado.getDouble("PRECO"));
				item.setTotal(resultado.getDouble("TOTAL"));
				item.setLivro(livro);
				item.setQtd(resultado.getInt("QTD"));
				itens.add(item);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return itens;
		*/
		
		CriteriaBuilder builder =  em.getCriteriaBuilder();
		CriteriaQuery<ItemCarrinho> cquery = builder.createQuery(ItemCarrinho.class);
		
		Root<ItemCarrinho> fromItemCarrinho = cquery.from(ItemCarrinho.class);
		Root<Pedido> fromPedido = cquery.from(Pedido.class);
		Root<Livro> fromLivro = cquery.from(Livro.class);
		
		cquery.multiselect(fromItemCarrinho,fromPedido,fromLivro);
		
		List<Predicate> condicoes = new ArrayList<>();
		
		condicoes.add(builder.equal(fromPedido.get("codigo"), codPedido));
		condicoes.add(builder.equal(fromItemCarrinho.get("pedido"), codPedido));
		condicoes.add(builder.equal(fromPedido.get("livro").get("codigo"),fromLivro.get("codigo")));
		
		cquery.where(condicoes.toArray(new Predicate[condicoes.size()]));
		
		TypedQuery<ItemCarrinho> itens = em.createQuery(cquery);

		
		return itens.getResultList();
		
	}
}
