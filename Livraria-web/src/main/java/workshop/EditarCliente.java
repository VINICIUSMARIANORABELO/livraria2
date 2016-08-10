package workshop;


import java.io.IOException;

import java.sql.SQLException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/EditarCliente")
public class EditarCliente extends HttpServlet {
	
	private Cliente cliente;
	private String mensagem;
	 

	
	private void mostrarPagina(HttpServletRequest request, HttpServletResponse response, String pagina) throws ServletException, IOException
	{
		request.setAttribute("mensagem", mensagem);
		
		RequestDispatcher rd = request.getRequestDispatcher(pagina);
		rd.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String acao = request.getParameter("acao");
		mensagem = "";
		cliente = (Cliente) request.getSession().getAttribute("cliente");
		if (cliente == null){
			cliente = new Cliente();
		}
		ClienteDao dao = null;
		try{
			dao = new ClienteDao();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
	
		if("autenticar".equals(acao)){
			cliente.setLogin(request.getParameter("login"));
			cliente.setSenha(request.getParameter("senha"));
			
			cliente = dao.autenticar(cliente);
			if(cliente==null){
				mensagem = "Senha ou usuário incorretos!";
				mostrarPagina(request,response,"login.jsp");
			}
			else{
				request.getSession().setAttribute("cliente", cliente);
				mostrarPagina(request,response,"home.jsp");
			}
		}

		if("cadastrar".equals(acao)){
			String senha1= request.getParameter("senha1");
			String senha2= request.getParameter("senha2");
			
			if(!senha1.equals(senha2)){
				mensagem = "As senhas não são iguais!";
				mostrarPagina(request,response,"cadastro.jsp");
				return;
			}
			
			cliente.setNome(request.getParameter("nome"));
			cliente.setSenha(request.getParameter("senha1"));
			cliente.setLogin(request.getParameter("login"));
			cliente.setEndereco("rua");
			cliente.setCidade("cidade");
			cliente.setBairro("bairro");
			cliente.setEstado("estado");
			cliente.setCep("0000000");
			
			if(dao.existeCliente(request.getParameter("login"))){
					mensagem = "Já existe esse usuário em nosso sistema!";
				}else{
					cliente.setLogin(request.getParameter("login"));
					dao.inserir(cliente);
					mensagem = "Cliente cadastrado com sucesso!";
				}
			mostrarPagina(request,response,"cadastro.jsp");
			}
		
			if("alterar".equals(acao)){
				
				cliente.setSenha(request.getParameter("senha1"));
				cliente.setLogin(request.getParameter("login"));
				cliente.setEndereco(request.getParameter("endereco"));
				cliente.setCidade(request.getParameter("cidade"));
				cliente.setBairro(request.getParameter("bairro"));
				cliente.setEstado(request.getParameter("estado"));
				cliente.setCep(request.getParameter("cep"));
				
				if(dao.existeCliente(request.getParameter("login"))){
					mensagem = "Já existe esse usuário em nosso sistema!";
				}else{
					cliente.setLogin(request.getParameter("login"));
					dao.alterar(cliente);
					mensagem = "Dados alterados com sucesso!";
				}
			mostrarPagina(request,response,"cadastro.jsp");
			}
				
		
		if("verCadastro".equals(acao)){
			RequestDispatcher rd = request.getRequestDispatcher("cadastro.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
}
