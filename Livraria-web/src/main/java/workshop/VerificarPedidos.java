package workshop;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VerificarPedidos")
public class VerificarPedidos extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		
		if(cliente == null || !cliente.isAutenticacao()){
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		PedidoDao dao = null;
		try{
			dao = new PedidoDao();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		List<Pedido> pedidos = dao.listar(cliente.getCodigo());
		if(pedidos == null){
			
			return;
		}
		request.getSession().setAttribute("pedidos",pedidos);
		RequestDispatcher rd = request.getRequestDispatcher("pedidos.jsp");
		rd.forward(request, response);
		
	}

}
