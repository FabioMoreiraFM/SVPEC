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
import persistencia.InserirClasse;
import persistencia.RecuperarClasse;
import utils.GeradorAleatorio;
import utils.Validador;
import utils.Validador.Regex;

@WebServlet("/areaEleitor")
public class AreaEleitor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/** Eleição escolhida pelo usuário. */
	private int eleicaoEscolhida = 0;  
    
	private Properties prop = new Properties();
	private InputStream input = getClass().getClassLoader().getResourceAsStream("/mensagem.properties");
	
    public AreaEleitor() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (prop.isEmpty())
			prop.load(input);
		
		String url = prop.getProperty("urlAreaEleitor");
				
		getServletContext().getRequestDispatcher(url).forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (eleicaoEscolhida == 0)
			eleicaoEscolhida = Integer.parseInt(request.getParameter("eleicaoAtual"));
		
		if (prop.isEmpty())
			prop.load(input);
		
		request.setAttribute("cargos",  RecuperarClasse.recuperaCargos(eleicaoEscolhida));
		request.setAttribute("candidatos", RecuperarClasse.recuperaCandidatos(eleicaoEscolhida)); 
		
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String url = prop.getProperty("urlFimVotacao");
				
		if (!Validador.validarExpressao(nome, Regex.NOME)) {
			request.setAttribute("errorNome", prop.getProperty("msgErroNome"));
			url = prop.getProperty("urlAreaEleitor");
		}	
		
		if (!Validador.validarExpressao(cpf, Regex.CPF)) {
			request.setAttribute("errorCPF", prop.getProperty("msgErroCPF"));
			url = prop.getProperty("urlAreaEleitor");
		}		
		
		if (url == prop.getProperty("urlFimVotacao")) {
			String protocolo = GeradorAleatorio.geracaoProtocolo();
			String[] candidatos = request.getParameterValues("candidatos");
			
			List<Cargo> cargos = RecuperarClasse.recuperaCargos(eleicaoEscolhida);
									
			InserirClasse.insereEleitor(nome, cpf, protocolo, eleicaoEscolhida);
			InserirClasse.insereNovoVoto(cargos, candidatos, eleicaoEscolhida);
			
			request.setAttribute("protocolo", protocolo);
			eleicaoEscolhida = 0;
		}		
				
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}


}
