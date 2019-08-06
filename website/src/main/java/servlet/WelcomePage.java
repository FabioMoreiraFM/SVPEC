package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Eleicao;
import persistencia.PreCarregamento;
import persistencia.RecuperarClasse;
import utils.Pair;

@WebServlet("/welcomePage")
public class WelcomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Properties prop = new Properties();
	private InputStream input = getClass().getClassLoader().getResourceAsStream("/mensagem.properties");
	
    public WelcomePage() {
        super();
    }

  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (prop.isEmpty())
			prop.load(input);
		
		String url = prop.getProperty("urlWelcomePage");
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (prop.isEmpty())
			prop.load(input);
		
		PreCarregamento.inicializacao();
		
		String btnSubmissao = request.getParameter("btnSubmissao");
		String url = "";
		
		if (btnSubmissao.equals("Entrar")) {
			url = prop.getProperty("urlMenuInicial");
			
			request.setAttribute("eleicoes", RecuperarClasse.recuperaEleicoes());			
		} else {
			url = prop.getProperty("urlRelatorio");
			
			Pair<List<Eleicao>, List<Eleicao>> eleicoes = PreCarregamento.separaEleicoesPorStatus();
						
			request.setAttribute("eleicoesAndamento", eleicoes.getFirst());
			request.setAttribute("eleicoesFinalizadas", eleicoes.getSecond());
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
			
}
