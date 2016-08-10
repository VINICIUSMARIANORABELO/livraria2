package workshop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDao {
	
	Connection conexao;
	
	public LivroDao() throws SQLException{
		this.conexao = FabricaConexao.getConexao();
	}
	
	public Livro consultar (int codigo){
		Livro livro = null;
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("select AUTOR, TITULO, COD_LIVRO, IMAGEM, PRECO, DESCRICAO from ESTOQUE WHERE COD_LIVRO = ?");
			PreparedStatement consulta = conexao.prepareStatement(sql.toString());
			consulta.setInt(1, codigo);
			ResultSet resultado = consulta.executeQuery();
			if(resultado.next()){
				livro = new Livro();
				livro.setAutor(resultado.getString("AUTOR"));
				livro.setCodigo(resultado.getInt("COD_LIVRO"));
				livro.setImagem(resultado.getString("IMAGEM"));
				livro.setPreco(resultado.getDouble("PRECO"));
				livro.setTitulo(resultado.getString("TITULO"));
				livro.setDescricao(resultado.getString("DESCRICAO"));
			}
			conexao.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return livro;
	}
	
	public List<Livro> consultar(String titulo){
		ArrayList<Livro> lista= new ArrayList<Livro>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COD_LIVRO, TITULO, AUTOR, PRECO, IMAGEM, DESCRICAO FROM ESTOQUE WHERE TITULO LIKE ?");
			this.conexao = FabricaConexao.getConexao();
			PreparedStatement consulta = conexao.prepareStatement(sql.toString());
			
			consulta.setString(1, "%"+titulo.toUpperCase()+"%");
			ResultSet resultado = consulta.executeQuery();
			while(resultado.next()){
				Livro livro = new Livro();
				livro.setAutor(resultado.getString("AUTOR"));
				livro.setCodigo(resultado.getInt("COD_LIVRO"));
				livro.setImagem(resultado.getString("IMAGEM"));
				livro.setPreco(resultado.getDouble("PRECO"));
				livro.setTitulo(resultado.getString("TITULO"));
				livro.setDescricao(resultado.getString("DESCRICAO"));
				lista.add(livro);
			}
			conexao.close();	
			} catch (SQLException e){
				e.printStackTrace();
		}
		return lista;
	}

}
