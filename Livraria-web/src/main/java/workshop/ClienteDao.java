package workshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.core.Request;

public class ClienteDao {
	
	Connection conexao;
	
	public ClienteDao() throws SQLException{
		this.conexao= FabricaConexao.getConexao();
	}
	
	public Cliente inserir(Cliente cliente){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO CLIENTE (NOME,LOGIN,SENHA,ENDERECO,CIDADE,BAIRRO,ESTADO,CEP) VALUES (?,?,?,?,?,?,?,?)");
			PreparedStatement consulta = conexao.prepareStatement(sql.toString());
			consulta.setString(1, cliente.getNome());
			consulta.setString(2, cliente.getLogin());
			consulta.setString(3, cliente.getSenha());
			consulta.setString(4, cliente.getEndereco());
			consulta.setString(5, cliente.getCidade());
			consulta.setString(6, cliente.getBairro());
			consulta.setString(7, cliente.getEstado());
			consulta.setString(8, cliente.getCep());
			consulta.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return cliente;
	}
	
	public Cliente autenticar(Cliente cliente){
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT NOME, SENHA, LOGIN, ENDERECO, CIDADE, BAIRRO, ESTADO, CEP, COD_CLIENTE FROM CLIENTE where SENHA = ? and LOGIN = ?");
			this.conexao= FabricaConexao.getConexao();
			PreparedStatement consulta = conexao.prepareStatement(sql.toString());
			consulta.setString(1, cliente.getSenha());
			consulta.setString(2, cliente.getLogin());
			ResultSet resultado = consulta.executeQuery();
			if(resultado.next()){
				cliente.setNome(resultado.getString("NOME"));
				cliente.setSenha(resultado.getString("SENHA"));
				cliente.setLogin(resultado.getString("LOGIN"));
				cliente.setEndereco(resultado.getString("ENDERECO"));
				cliente.setCidade(resultado.getString("CIDADE"));
				cliente.setBairro(resultado.getString("BAIRRO"));
				cliente.setEstado(resultado.getString("ESTADO"));
				cliente.setCep(resultado.getString("CEP"));
				cliente.setCodigo(resultado.getInt("COD_CLIENTE"));
				cliente.setAutenticacao(true);
			}
			else{
				cliente=null;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return cliente;
	}
	

	
	public void alterar(Cliente cliente){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("update CLIENTE SET SENHA = ?, LOGIN = ?, ENDERECO = ?, CIDADE = ?, BAIRRO = ?, ESTADO = ?, CEP = ? where COD_CLIENTE = ?");
			
			this.conexao= FabricaConexao.getConexao();
			PreparedStatement consulta = conexao.prepareStatement(sql.toString());
			
			consulta.setString(1, cliente.getSenha());
			consulta.setString(2, cliente.getLogin());
			consulta.setString(3, cliente.getEndereco());
			consulta.setString(4, cliente.getCidade());
			consulta.setString(5, cliente.getBairro());
			consulta.setString(6, cliente.getEstado());
			consulta.setString(7, cliente.getCep());
			consulta.setInt(8, cliente.getCodigo());
			consulta.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	

	
	public boolean existeCliente(String login){
		boolean result = true;
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT (LOGIN, COD_CLIENTE) FROM CLIENTE WHERE LOGIN = ?");
			this.conexao= FabricaConexao.getConexao();
			PreparedStatement consulta = conexao.prepareStatement(sql.toString());
			consulta.setString(1, login);
			ResultSet resultado = consulta.executeQuery();
			if(!resultado.next()){
				result = false;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
}


