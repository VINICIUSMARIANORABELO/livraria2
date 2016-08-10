package service;

public class ItemCarrinho {
	private int codigo;
	private Livro livro;
	private double valor;
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
