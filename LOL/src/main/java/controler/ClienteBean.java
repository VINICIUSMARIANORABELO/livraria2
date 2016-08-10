package controler;


import java.sql.SQLException;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import DAO.ClienteDao;
import service.*;
import java.io.Serializable;


@ManagedBean(name="clienteBean")
@SessionScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String usuario, senha, endereco, bairro, cidade, estado, cep, nome;
	private Cliente cliente;
	private ClienteDao dao;


	
	public void cadastrar(){
		
		FacesMessage msg = null;
		
		if(cliente == null){
			cliente = new Cliente();
		}
		try{
			dao = new ClienteDao();
		}catch(SQLException e){
			e.printStackTrace();
		}	

		cliente.setSenha(senha);
		cliente.setNome(nome);
		cliente.setEndereco("endereço");
		cliente.setBairro("bairro");
		cliente.setCidade("cidade");
		cliente.setCep("cep");
		cliente.setEstado("estado");
		

		System.out.println("Usuário: "+usuario);
		System.out.println("Senha: "+senha);
		
		if(dao.existeCliente(usuario)){
			msg = new FacesMessage("Já existe esse usuário em nosso sistema");
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
		}else{
			cliente.setLogin(usuario);
			dao.inserir(cliente);
			msg = new FacesMessage("Cliente cadastrado com sucesso!");
			
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	
	

	public void alterarCadastro(){
		
		FacesMessage msg = null;
		
		if(cliente == null){
			cliente = new Cliente();
		}
		try{
			dao = new ClienteDao();
		}catch(SQLException e){
		}	

		cliente.setSenha(senha);
		cliente.setNome(nome);
		cliente.setEndereco(endereco);
		cliente.setBairro(bairro);
		cliente.setCidade(cidade);
		cliente.setCep(cep);
		cliente.setEstado(estado);
		
		msg = new FacesMessage("Cadastro alterado com Sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		System.out.println("Cadastro alterado com sucesso");
		
	}
	
	public String autenticar(){
		
		FacesMessage msg = null;
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		if(cliente == null){
			cliente = new Cliente();
		}
		try{
			dao = new ClienteDao();
		}catch(SQLException e){
		}	
		
		cliente.setLogin(usuario);
		cliente.setSenha(senha);
		
		cliente = dao.autenticar(cliente);
		if(cliente == null){
			msg = new FacesMessage("Login incorreto");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			System.out.println("Senha ou Usuario incorretos");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "Login";
		}else{
			System.out.println("Cliente "+cliente.getNome()+ " Autenticado!");
			cliente.setAutenticacao(true);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", cliente.getLogin());
			session.setAttribute("cliente", cliente);
			return "Home";
		}
		

	}
	
	public String sair(){
		
		cliente.setAutenticacao(false);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		System.out.println("Cliente "+cliente.getNome()+" saiu da sessão!");
		cliente = null;
		return "Home";
		
	}
	

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
}	

