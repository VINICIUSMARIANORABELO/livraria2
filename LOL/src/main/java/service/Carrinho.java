package service;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
	
	private List<ItemCarrinho> itens = new ArrayList<ItemCarrinho>();
	
	public void adicionar(ItemCarrinho item){
		for(ItemCarrinho itm: itens){
			if(itm.equals(item)){
				itm.setQtd(itm.getQtd()+1);
				return;
			}
		}
		itens.add(item);
	}
	public void subtrair(ItemCarrinho item){
		for(ItemCarrinho itm: itens){
			if(itm.equals(item)){
				itm.setQtd(itm.getQtd()-1);
				return;
			}
		}
		itens.remove(item);
	}

	public String getValorString2(){
		return getValor2();
	}
	
	public boolean isEmptyCarrinho(){
		if(itens.size() == 0){
			return true;
		}
		else{
			return false;
		}
	}

	public double getValor(){
		double result = 0;
		for(ItemCarrinho item : itens){
			result += item.getValor();
		}
		
		return result;
	}
	
	public String getValor2(){
		String resultS = "";
		resultS = String.format("%.2f", getValor());
		
		return resultS;
	}
	
	


	
	public void remover(ItemCarrinho item){
		itens.remove(item);
	}
	public void altera(ItemCarrinho item){
		itens.remove(item);
		itens.add(item);
	}
	public List<ItemCarrinho> getItens(){
		return itens;
	}
	public void setItens(List<ItemCarrinho> itens){
		this.itens = itens;
	}

}
