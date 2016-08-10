package service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
@Table(name="pedido")
public class Pedido implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //identity faz o Postgree controlar o auto-indentar
	@Column(name="COD_PEDIDO")
	private int codigo;
	
	@OneToMany(cascade={CascadeType.ALL}) //cascade: força gravar todas as classes dependentes, ALL: faz na atualização, na deleção, na inclusao
	@JoinColumn(name="COD_PEDIDO",foreignKey=@ForeignKey(name="fk_pedido_itens"))
	private List<ItemCarrinho> itens;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="COD_CLIENTE",foreignKey=@ForeignKey(name="fk_pedido_cliente"))
	private Cliente cliente;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_PEDIDO")
	private Date dataPedido = new Date();
	
	@Column(name="PAGAMENTO")
	@Enumerated(EnumType.STRING) //força o tipo como string //enum: lista fixa exemplo: meses do ano, nunca muda, forma de pagamento tb. 
	private EnumTipoPagamento pagamento;
	
	@Column(name="TOTAL")
	private Double valorTotal;
	
	@Column(name="STATUS",length=50)
	private String status;
	
	@Transient
	private String dataPedidoString;

	public String getDataPedidoString() {
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

	public EnumTipoPagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(EnumTipoPagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

}
