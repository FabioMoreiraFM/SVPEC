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

import modelo.Cargo;
import modelo.Eleicao;
import persistencia.InserirClasse;
import persistencia.RecuperarClasse;
import utils.Validador;
import utils.Validador.Regex;

@WebServlet("/cadastrarNovoCargo")
public class CadastroCargo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Properties prop = new Properties();
	private InputStream input = getClass().getClassLoader().getResourceAsStream("/mensagem.properties");
	
    public CadastroCargo() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (prop.isEmpty())
			prop.load(input);
		
		String url = prop.getProperty("urlCadastroCargo");
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (prop.isEmpty())
			prop.load(input);
		
		String nome = request.getParameter("nomeCargo");
		String url = prop.getProperty("urlCadastroCandidato");
		
		if (!Validador.validarExpressao(nome, Regex.NOME)) {
			request.setAttribute("errorNome", prop.getProperty("msgErroNome"));
			url = prop.getProperty("urlCadastroCargo");
		} else {
			InserirClasse.insereNovoCargo(nome);
			
			if (request.getParameter("btnSubmissao").equals("Enviar e continuar cadastrando")) {
				url = prop.getProperty("urlCadastroCargo");
			} else {
				List<Cargo> cargos = RecuperarClasse.recuperaCargos(Eleicao.nEleicao);
				request.setAttribute("cargos", cargos);
			}
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
