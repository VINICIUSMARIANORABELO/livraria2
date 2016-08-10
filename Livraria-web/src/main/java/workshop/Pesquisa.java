package workshop;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Pesquisa")
public class Pesquisa extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String titulo = request.getParameter("titulo");
		if(titulo==null){
			titulo = "";
		}
		try{
			LivroDao dao = new LivroDao();
			List<Livro> array = dao.consultar(titulo);
			int tamanho = array.size();
			if(tamanho>0){
				ArrayList<Livro> livros = new ArrayList<Livro>();
				livros.addAll(array);
				System.out.println(livros.toString());
				request.setAttribute("livros", livros);
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("resultado.jsp");	
				rd.forward(request, response);
			}
			else{
				System.out.println(titulo + "- Esta vindo vazio!");
			}
		}
		catch(SQLException e){
				e.printStackTrace();
			}
			
		}
	}



