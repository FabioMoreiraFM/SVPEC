package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import modelo.Cargo;
import modelo.Eleicao;
import persistencia.InserirClasse;
import persistencia.RecuperarClasse;
import utils.Imagem;
import utils.Imagem.ImagemStatus;
import utils.Validador;
import utils.Validador.Regex;

@WebServlet("/cadastrarNovoCandidato")
@MultipartConfig
public class CadastroCandidato extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String nomeFoto = "";
	private String nome = "";
	
	private Properties prop = new Properties();
	private InputStream input = getClass().getClassLoader().getResourceAsStream("/mensagem.properties");
	
	
    public CadastroCandidato() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (prop.isEmpty())
			prop.load(input);
		
		List<Cargo> cargos = RecuperarClasse.recuperaCargos(Eleicao.nEleicao);
		request.setAttribute("cargos", cargos);
		
		String url = prop.getProperty("urlCadastroCandidato");
		getServletContext().getRequestDispatcher(url).forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		if (prop.isEmpty())
			prop.load(input);
		
		List<Cargo> cargos = RecuperarClasse.recuperaCargos(Eleicao.nEleicao);
		request.setAttribute("cargos", cargos);
		
		String quemEnviouRequest = request.getParameter("btnSubmissao");
		String candidato = request.getParameter("cargos");
		String url = prop.getProperty("urlWelcomePage");
		
		if (quemEnviouRequest == null) {
			Part filePart = request.getPart("localFoto");
			nomeFoto = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			
			ImagemStatus resultado = Imagem.processaImagem(filePart, nomeFoto);		
			
			switch (resultado) {
				case OK:
					request.setAttribute("localImagem", "/teste/" + nomeFoto);
					break;
				case NaoEhImagem:
					request.setAttribute("errorFoto", prop.getProperty("msgErroFoto"));
					break;
				case Vazio:
					request.setAttribute("errorFoto", prop.getProperty("msgErroNaoSelecionouFoto"));
					break;
			}
			
			url = prop.getProperty("urlCadastroCandidato");
			
		} else if (!nomeFoto.isEmpty()) {
			nome = request.getParameter("nomeCandidato");
			
			if (!Validador.validarExpressao(nome, Regex.NOME)) {
				request.setAttribute("errorNome", prop.getProperty("msgErroNome"));
				url = prop.getProperty("urlCadastroCandidato");
			} else {
				InserirClasse.insereNovoCandidato(nome, nomeFoto, Integer.parseInt(candidato));
				
				if (quemEnviouRequest.equals("Cadastrar e continuar cadastrando")) {
					url = prop.getProperty("urlCadastroCandidato");
				} else if (quemEnviouRequest.equals("Cadastrar novo cargo")) {
					url = prop.getProperty("urlCadastroCargo");
				}
			}
			
			nomeFoto = "";
			nome = "";
		} else {
			request.setAttribute("errorFoto", prop.getProperty("msgErroNaoSelecionouFoto"));
			url = prop.getProperty("urlCadastroCandidato");
		}		
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
