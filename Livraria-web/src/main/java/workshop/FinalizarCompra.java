package workshop;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

@WebServlet("/FinalizarCompra")
public class FinalizarCompra extends HttpServlet{
	private final static String CARTAO="cartao";
	private final static String BOLETO="boleto";
	
	private String mensagem;
	
	private void mostrarPagina(HttpServletRequest request, HttpServletResponse response, String pagina) throws ServletException, IOException
	{
		request.setAttribute("mensagem", mensagem);
		
		RequestDispatcher rd = request.getRequestDispatcher(pagina);
		rd.forward(request, response);
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
		
		if(cliente == null){
			mensagem = "Faça seu login antes de concluir a compra";
			mostrarPagina(request, response, "login.jsp");
			return;
		}
		request.getSession().removeAttribute("carrinho");
		mensagem = "";
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setItens(carrinho.getItens());
		
		
		if(CARTAO.equals(request.getParameter("optradio"))){
			pedido.setPagamento("Cartão");
		}else if(BOLETO.equals(request.getParameter("optradio"))){
			pedido.setPagamento("Boleto");
		}
		
		pedido.setStatus("Pedido Registrado");
		
		PedidoDao dao = null;
		try{
			dao = new PedidoDao();
		} catch(SQLException e){
			e.printStackTrace();
		}
		dao.incluir(pedido);
		response.sendRedirect("fim.jsp");
	}

}
