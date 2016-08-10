package controler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import DAO.*;
import service.*;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@ManagedBean
@SessionScoped
public class PedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ItemCarrinho item;
	private Cliente cliente;
	private List<Pedido> pedidos;
	private List<ItemCarrinho> itens;
	
    

	public String verificarPedido(){
		
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		cliente = (Cliente) session.getAttribute("cliente");
		
		if(cliente == null){
			
			return "Login";
		}
		
		PedidoDao dao = null;
		try{
			dao = new PedidoDao();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		pedidos = dao.listarPedido(cliente.getCodigo());
		
		return "VerificarPedido";
	}
	
	public String verificarItens(int codPedido){
		
		PedidoDao dao = null;
		try{
			dao = new PedidoDao();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		itens = dao.listarItem(codPedido);
		
		item = itens.get(0);
		
		
		return "VerificarItem";
		
	}



	public ItemCarrinho getItem() {
		return item;
	}

	public void setItem(ItemCarrinho item) {
		this.item = item;
	}

	public List<ItemCarrinho> getItens() {
		return itens;
	}

	public void setItens(List<ItemCarrinho> itens) {
		this.itens = itens;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}



	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	

}
