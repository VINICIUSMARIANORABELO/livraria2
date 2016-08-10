package service;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="CLIENTE")
public class Cliente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5346027923991917617L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COD_CLIENTE")
	private int codigo;
	
	@Column(name="NOME",length=50)
	private String nome;
	
	
	@Column(name="LOGIN",length=10)
	
	private String login;
	
	@Column(name="SENHA",length=10)
	private String senha;
	
	@Column(name="ENDERECO",length=32)
	private String endereco;
	
	@Column(name="CIDADE",length=32)
	private String cidade;
	
	@Column(name="BAIRRO",length=32)
	private String bairro;
	
	@Column(name="ESTADO",length=32)
	private String estado;
	
	@Column(name="CEP",length=10)
	private String cep;
	
	@Transient //quando é transitório na memória e não vai gravar no BD
	private boolean autenticacao = false;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public boolean isAutenticacao() {
		return autenticacao;
	}
	public void setAutenticacao(boolean autenticacao) {
		this.autenticacao = autenticacao;
	}
	
	

}
