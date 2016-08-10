package service;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ITEM_PEDIDO")
public class ItemCarrinho implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COD_ITEM")
	private int codigo;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="COD_LIVRO",foreignKey=@ForeignKey(name="fk_item_pedido_livro"))
	private Livro livro;
	
	@Transient
	private double valor;
	
	@Transient
	private double total;
	
	private int qtd=1;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	
	@Override
	public boolean equals(Object obj){
		ItemCarrinho item = (ItemCarrinho) obj;
		if(item.getLivro().getCodigo()==this.getLivro().getCodigo()){
			return true;
		}
		else{
			return false;
		}
	}

	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getValor(){
		return livro.getPreco()*qtd;
	}
	public void setValor(double d){
		this.valor = d;
	}

	

}
