package DAO;

import service.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PedidoDao {
	Connection conexao;

	public PedidoDao() throws SQLException {
		this.conexao = FabricaConexao.getConexao();
	}

	public void incluir(Pedido pedido) {
		Date dataPedido = new Date(pedido.getDataPedido().getTime());

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO PEDIDO (DATA_PEDIDO, COD_CLIENTE, STATUS, PAGAMENTO, TOTAL) VALUES (?,?,?,?,?)");

		StringBuffer sqlItem = new StringBuffer();
		sqlItem.append("INSERT INTO ITEM_PEDIDO (COD_LIVRO, QTD, COD_PEDIDO) VALUES (?,?,?)");

		try {
			this.conexao = FabricaConexao.getConexao();
			PreparedStatement consulta = conexao.prepareStatement(sql.toString());
			consulta.setDate(1, dataPedido);
			consulta.setInt(2, pedido.getCliente().getCodigo());
			consulta.setString(3, pedido.getStatus());
			consulta.setString(4, pedido.getPagamento());
			consulta.setDouble(5, pedido.getValorTotal());
			consulta.execute();

			PreparedStatement consultaItem = conexao.prepareStatement(sqlItem.toString());
			for (ItemCarrinho item : pedido.getItens()) {
				consultaItem.setInt(1, item.getLivro().getCodigo());
				consultaItem.setInt(2, item.getQtd());
				consultaItem.setInt(3, getCodUltimoPedido(pedido.getCliente().getCodigo()));
				consultaItem.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCodUltimoPedido(int codCliente) {
		int cod = 1;
		try {
			PreparedStatement consulta = conexao
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
	}

	public List<Pedido> listarPedido(int codCliente) {
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT pedido.COD_PEDIDO, pedido.DATA_PEDIDO, pedido.PAGAMENTO, pedido.STATUS ");
		sql.append("FROM pedido WHERE pedido.COD_CLIENTE = ?");

		try {
			PreparedStatement consulta = conexao.prepareStatement(sql.toString());
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
		return pedidos;
	}

	public List<ItemCarrinho> listarItem(int codPedido) {
		ArrayList<ItemCarrinho> itens = new ArrayList<ItemCarrinho>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT estoque.IMAGEM, estoque.TITULO, estoque.preco , item_pedido.QTD, pedido.TOTAL ");
		sql.append("FROM cliente INNER JOIN pedido ON cliente.COD_CLIENTE = pedido.COD_CLIENTE ");
		sql.append("INNER JOIN item_pedido ON pedido.COD_PEDIDO = item_pedido.COD_PEDIDO ");
		sql.append("INNER JOIN estoque ON item_pedido.COD_LIVRO = estoque.COD_LIVRO ");
		sql.append("WHERE pedido.COD_PEDIDO = ?");

		try {
			PreparedStatement consulta = conexao.prepareStatement(sql.toString());
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
	}
}
