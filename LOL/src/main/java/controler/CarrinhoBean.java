package controler;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import DAO.*;
import service.*;

import java.io.Serializable;
import java.sql.SQLException;

@ManagedBean(name="carrinhoBean")
@SessionScoped
public class CarrinhoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Carrinho carrinho;
	private Cliente cliente;
	private ItemCarrinho item;
	private Pedido pedido;
	private boolean cad;
	
	
	public void iniciaCarrinho(String cod){
		
		int codigo;
		LivroDao dao = null;
			
		if(carrinho == null){
			carrinho = new Carrinho();
		}
		
		try{
			codigo = Integer.parseInt(cod);
		}catch(NumberFormatException e){
			codigo = 0;
		}
		
		try{
			dao = new LivroDao();
		}
		catch(SQLException e1){
			e1.printStackTrace();
		}
		
		Livro livro = dao.consultar(codigo);
		ItemCarrinho item2 = new ItemCarrinho(); 
		item2.setLivro(livro);
		item2.setCodigo(livro.getCodigo());
		item = item2;
		
		
	}
	
	public String adicionar(String codigoS){
		
		iniciaCarrinho(codigoS);
		carrinho.adicionar(item);
		
		return "Carrinho";
	}
	
	public void retirar(String codigoS){
		
		iniciaCarrinho(codigoS);
		carrinho.subtrair(item);
	}
	
	public void remover(String codigoS){
		
		iniciaCarrinho(codigoS);
		carrinho.remover(item);
	}
	
	public boolean getCarrinhoVazio(){
		
		if(carrinho == null){
			carrinho = new Carrinho();
		}
		
		if(carrinho.getItens().size() == 0){
			return false;
		}
		
		return true;
	}
	
	
	public void finalizarCompra(){
		FacesMessage mensagem = null;
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		cliente = (Cliente) session.getAttribute("cliente");
		
		if(cliente == null){
			mensagem = new FacesMessage("Faça seu login antes de concluir a compra");
			mensagem.setSeverity(FacesMessage.SEVERITY_WARN);
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			cad = false;
			return;
		}
		
		pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setItens(carrinho.getItens());
		pedido.setStatus("Pedido Registrado");
		pedido.setValorTotal(carrinho.getValor());
		
		cad = true;
		
	}
	
	public String compraCartao(){

		finalizarCompra();
		if(cad == false){
			return "Carrinho";
		}
		
		pedido.setPagamento("Cartão");
		
		System.out.println("Compra feita com cartão");
		
		PedidoDao dao = null;
		try{
			dao = new PedidoDao();
		} catch(SQLException e){
			e.printStackTrace();
		}
		dao.incluir(pedido);
		carrinho = null;
		return "VerificarPedido";
		
	}
	
	public String compraBoleto(){

		finalizarCompra();
		if(cad == false){
			return "Carrinho";
		}
		
		pedido.setPagamento("Boleto");
		
		System.out.println("Compra feita no Boleto");
		
		PedidoDao dao = null;
		try{
			dao = new PedidoDao();
		} catch(SQLException e){
			e.printStackTrace();
		}
		dao.incluir(pedido);
		carrinho = null;
		return "VerificarPedido";
	}
	
	

	
	public Carrinho getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
	

	

}
