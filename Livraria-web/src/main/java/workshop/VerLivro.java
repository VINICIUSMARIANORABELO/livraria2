
package workshop;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/VerLivro")
public class VerLivro extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String cod = request.getParameter("codigo");
		int codigo;
		try{
			codigo = Integer.parseInt(cod);
		}
		catch(NumberFormatException e){
			codigo = 0;
		}
		try{
			LivroDao dao = new LivroDao();
			Livro livro = dao.consultar(codigo);
			request.setAttribute("livro", livro);
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("livro.jsp");
			rd.forward(request, response);
		} 
		catch(SQLException e){
			e.printStackTrace();
		}
	}

}
