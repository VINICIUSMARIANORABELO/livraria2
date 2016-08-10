package service;

import java.util.Date;
import java.util.List;

public class Pedido {
	
	private int codigo;
	private List<ItemCarrinho> itens;
	private Cliente cliente;
	private Date dataPedido = new Date();
	private String pagamento;
	private Double valorTotal;
	private String status;
	private String dataPedidoString;
	
	public String getDataPedidoString(){
		return dataPedido + "";
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public List<ItemCarrinho> getItens() {
		return itens;
	}

	public void setItens(List<ItemCarrinho> itens) {
		this.itens = itens;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDataPedidoString(String dataPedidoString) {
		this.dataPedidoString = dataPedidoString;
	}
	
	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	

}
