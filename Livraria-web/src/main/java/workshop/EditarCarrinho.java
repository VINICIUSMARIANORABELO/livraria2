package workshop;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/EditarCarrinho")
public class EditarCarrinho extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String cod = request.getParameter("codigo");
		String acao = request.getParameter("acao");
		
		Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
		if(carrinho == null){
			carrinho = new Carrinho();
		}

		int codigo;
		try{
			codigo = Integer.parseInt(cod);
		}
		catch(NumberFormatException e){
			codigo = 0;
		}
		LivroDao dao = null;
		try{
			dao = new LivroDao();
		}
		catch(SQLException e1){
			e1.printStackTrace();
		}
		Livro livro = dao.consultar(codigo);
		ItemCarrinho item = new ItemCarrinho();
		item.setLivro(livro);
		if("adicionar".equals(acao)){
			carrinho.adicionar(item);
		}
		if("subtrair".equals(acao)){
			carrinho.subtrair(item);
		}
		if("remover".equals(acao)){
			carrinho.remover(item);
		}
		if("alterar".equals(acao)){
			String val = request.getParameter("qtd"+codigo);
			int valor;
			try{
				valor = Integer.parseInt(val);
			}
			catch(NumberFormatException e){
				valor = 0;
			}
			item.setQtd(valor);
			carrinho.altera(item);
			return;
		}
		if("remanejar".equals(acao)){
			String val= request.getParameter("QTD");
			String val2 = request.getParameter("ValorLivro");
			int qtd;
			double valorLivro, valorTotal;
			try{
				qtd = Integer.parseInt(val);
				valorLivro = Double.parseDouble(val2);
			}
			catch(NumberFormatException e){
				qtd = 0;
				valorLivro = 0;
			}
			item.setQtd(qtd);
			carrinho.altera(item);
			valorTotal = valorLivro*qtd;
			item.setValor(valorTotal);
			
			response.getWriter().printf("R$ %s",item.getValor2());
			//response.getWriter().printf("R$ %s",carrinho.getValor2());
			response.flushBuffer();
			return;
			
		}
		
		request.getSession().setAttribute("carrinho", carrinho);
		
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("carrinho.jsp");
		
		rd.forward(request, response);
		
	}
	

}
