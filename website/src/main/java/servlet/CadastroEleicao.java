package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistencia.InserirClasse;
import utils.Validador;
import utils.Validador.Regex;

@WebServlet("/cadastrarNovaEleicao")
public class CadastroEleicao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Properties prop = new Properties();
	private InputStream input = getClass().getClassLoader().getResourceAsStream("/mensagem.properties");
	
    public CadastroEleicao() {
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (prop.isEmpty())
			prop.load(input);
		
		String url = prop.getProperty("urlCadastroEleicao");
			
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (prop.isEmpty())
			prop.load(input);
		
		String url = prop.getProperty("urlCadastroCargo");
		
		String nome = request.getParameter("nomeEleicao");
		String dataIni = request.getParameter("dataEleicaoIni"); 
		String dataFim = request.getParameter("dataEleicaoFim");
		
		if (!Validador.validarExpressao(nome, Regex.NOME)) {
			request.setAttribute("errorNome", prop.getProperty("msgErroNome"));
			url = prop.getProperty("urlCadastroEleicao");
		} 
		
		if (dataIni.isEmpty()) {
			request.setAttribute("errorDataIni", prop.getProperty("msgErroDataIni"));
			url = prop.getProperty("urlCadastroEleicao");
		}
		
		if (dataFim.isEmpty()) {
			request.setAttribute("errorDataFim", prop.getProperty("msgErroDataFim"));
			url = prop.getProperty("urlCadastroEleicao");
		}
		
		if (url != prop.getProperty("urlCadastroEleicao")) {
			InserirClasse.insereNovaEleicao(nome, dataIni, dataFim);
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
	
}
