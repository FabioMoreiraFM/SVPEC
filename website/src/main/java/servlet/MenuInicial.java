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

import modelo.Candidato;
import modelo.Cargo;
import modelo.Eleicao;
import persistencia.RecuperarClasse;

@WebServlet("/menuInicial")
public class MenuInicial extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Properties prop = new Properties();
	private InputStream input = getClass().getClassLoader().getResourceAsStream("/mensagem.properties");
	
    public MenuInicial() {
        super();
    }
      
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (prop.isEmpty())
			prop.load(input);
		
    	List<Eleicao> eleicoes = RecuperarClasse.recuperaEleicoes();
		request.setAttribute("eleicoes", eleicoes);			
		
		String url = prop.getProperty("urlMenuInicial");
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (prop.isEmpty())
			prop.load(input);
		
		String btnSubmissao = request.getParameter("btnSubmissao");
		String url = "";
				
		if (btnSubmissao.equals("Votar")) {
			String eleicao = request.getParameter("eleicoes");
			int eleicaoEscolhida = Integer.parseInt(eleicao);
			
			url = prop.getProperty("urlAreaEleitor");
			
			List<Cargo> cargos = RecuperarClasse.recuperaCargos(eleicaoEscolhida);
			List<Candidato> candidatos = RecuperarClasse.recuperaCandidatos(eleicaoEscolhida);
						
			request.setAttribute("cargos", cargos);
			request.setAttribute("candidatos", candidatos);
			request.setAttribute("eleicaoEscolhida", eleicao);
		} else {
			url = prop.getProperty("urlCadastroEleicao");
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
}
