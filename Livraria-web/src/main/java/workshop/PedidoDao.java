package workshop;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PedidoDao {
	Connection conexao;
	
	public PedidoDao() throws SQLException{
		this.conexao = FabricaConexao.getConexao();
	}
	
	public void incluir(Pedido pedido){
		Date dataPedido = new Date(pedido.getDataPedido().getTime());
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO PEDIDO (DATA_PEDIDO, COD_CLIENTE, STATUS, PAGAMENTO) VALUES (?,?,?,?)");
		
		StringBuffer sqlItem = new StringBuffer();
		sqlItem.append("INSERT INTO ITEM_PEDIDO (COD_LIVRO, QTD, COD_PEDIDO) VALUES (?,?,?)");
		
		try{
			this.conexao = FabricaConexao.getConexao();
			PreparedStatement consulta = conexao.prepareStatement(sql.toString());
			consulta.setDate(1,dataPedido);
			consulta.setInt(2, pedido.getCliente().getCodigo());
			consulta.setString(3, pedido.getStatus());
			consulta.setString(4, pedido.getPagamento());
			consulta.execute();
			
			PreparedStatement consultaItem = conexao.prepareStatement(sqlItem.toString());
			for(ItemCarrinho item : pedido.getItens()){
				consultaItem.setInt(1, item.getLivro().getCodigo());
				consultaItem.setInt(2, item.getQtd());
				consultaItem.setInt(3, getCodUltimoPedido(pedido.getCliente().getCodigo()));
				consultaItem.execute();
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public int getCodUltimoPedido(int codCliente){
		int cod=1;
		try{
			PreparedStatement consulta = conexao.prepareStatement("SELECT MAX(COD_PEDIDO) AS COD_PEDIDO FROM PEDIDO WHERE COD_CLIENTE = ?");
			consulta.setInt(1, codCliente);
			ResultSet resultado = consulta.executeQuery();
			if(resultado.next()){
				cod=resultado.getInt("COD_PEDIDO");
			}
		} catch(SQLException e){
			e.printStackTrace();;
		}
		return cod;
	}
	
	public List<Pedido> listar(int codCliente){
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT cliente.NOME, pedido.COD_PEDIDO, pedido.DATA_PEDIDO, pedido.PAGAMENTO, ");
		sql.append(" pedido.STATUS, estoque.TITULO, estoque.COD_LIVRO, item_pedido.QTD ");
		sql.append(" FROM CLIENTE INNER JOIN pedido ON cliente.COD_CLIENTE = pedido.COD_CLIENTE INNER ");
		sql.append(" JOIN item_pedido ON pedido.COD_PEDIDO = item_pedido.COD_PEDIDO INNER JOIN estoque ON item_pedido.COD_LIVRO = estoque.COD_LIVRO WHERE cliente.COD_CLIENTE = ?");

		
		try{
			PreparedStatement consulta = conexao.prepareStatement(sql.toString());
			consulta.setInt(1, codCliente);
			ResultSet resultado = consulta.executeQuery();
			while(resultado.next()){
				Pedido pedido = new Pedido();
				Cliente cliente = new Cliente();
				cliente.setNome(resultado.getString("NOME"));
				pedido.setCliente(cliente);
				pedido.setCodigo(resultado.getInt("COD_PEDIDO"));
				pedido.setDataPedido(resultado.getDate("DATA_PEDIDO"));
				pedido.setPagamento(resultado.getString("PAGAMENTO"));
				pedido.setStatus(resultado.getString("STATUS"));
				
				ArrayList<ItemCarrinho> itens = new ArrayList<ItemCarrinho>();
				
				
					ItemCarrinho item = new ItemCarrinho();
					Livro livro = new Livro();
					livro.setTitulo(resultado.getString("TITULO"));
					item.setLivro(livro);
					item.setCodigo(resultado.getInt("COD_LIVRO"));
					item.setQtd(resultado.getInt("QTD"));
					itens.add(item);
				
				
				pedido.setItens(itens);
				pedidos.add(pedido);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return pedidos;
			
		}
	}

