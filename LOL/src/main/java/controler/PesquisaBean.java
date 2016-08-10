package controler;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import DAO.LivroDao;
import service.Livro;

@ManagedBean(name = "pesquisaBean")
@SessionScoped
public class PesquisaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String titulo;
	private Livro livro;
	private ArrayList<Livro> livros = new ArrayList<>();

	public String pesquisar() {

		if (titulo == null) {
			titulo = "";
		}
		System.out.println("Pesquisa: " + titulo);

		try {
			LivroDao dao = new LivroDao();
			List<Livro> array = dao.consultar(titulo);
			int tamanho = array.size();
			if (tamanho > 0) {

				livros.addAll(array);
				System.out.println(livros.toString());

			} else {
				System.out.println(titulo + "- Esta vindo vazio!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setTitulo(null);

		return "Resultado";
	}

	public String verLivro(Integer codigo) {

		
		try {
		} catch (NumberFormatException e) {
			codigo = 0;
		}
		try {
			LivroDao dao = new LivroDao();
			livro = dao.consultar(codigo);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "Livro";
	}

	public ArrayList<Livro> getLivros() {
		return livros;
	}

	public void setLivros(ArrayList<Livro> livros) {
		this.livros = livros;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

}
