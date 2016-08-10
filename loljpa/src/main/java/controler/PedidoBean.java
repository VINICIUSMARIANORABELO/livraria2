package controler;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import DAO.PedidoDao;
import service.Cliente;
import service.ItemCarrinho;
import service.Pedido;

@ManagedBean
@SessionScoped
public class PedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ItemCarrinho item;
	
	@ManagedProperty("#{clienteBean}")
	private ClienteBean clienteBean;
	
	private List<Pedido> pedidos;
	private List<ItemCarrinho> itens;
	
    

	public String verificarPedido(){
		
		//mostrar como a injeção de dependencia funciona
		//HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		//cliente = (Cliente) session.getAttribute("cliente");
		
		if(clienteBean.getCliente() == null){
			
			return "Login";
		}
		
		PedidoDao dao = null;
		try{
			dao = new PedidoDao();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		pedidos = dao.listarPedido(clienteBean.getCliente().getCodigo());
		
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

	public void setClienteBean(ClienteBean clienteBean) {
		this.clienteBean = clienteBean;
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
