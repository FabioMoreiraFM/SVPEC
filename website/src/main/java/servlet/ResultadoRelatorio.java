package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Relatorio;
import persistencia.ProduzirRelatorio;

@WebServlet("/resultadoRelatorio")
public class ResultadoRelatorio extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Properties prop = new Properties();
	private InputStream input = getClass().getClassLoader().getResourceAsStream("/mensagem.properties");
	
    public ResultadoRelatorio() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (prop.isEmpty())
			prop.load(input);
		
		String url = prop.getProperty("urlRelatorio");
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (prop.isEmpty())
			prop.load(input);
		
		String btnSubmissao = request.getParameter("btnSubmissao");
		String url = prop.getProperty("urlApresentacaoRelatorio");
						
		if (btnSubmissao.equals("Apresentar relatório parcial")) {
			String idEleicao = request.getParameter("relParcial");
			int qtdVotantes = ProduzirRelatorio.carregarRelatorioParcial(idEleicao);
			
			request.setAttribute("resultadoParcial", qtdVotantes);
		} else {
			String idEleicao = request.getParameter("relFinal");
			
			int qtdVotantes = ProduzirRelatorio.carregarRelatorioParcial(idEleicao);
			Relatorio relatorio = ProduzirRelatorio.carregarRelatorioFinal(idEleicao);
			
			request.setAttribute("resultadoFinal", qtdVotantes);
			request.setAttribute("relatorio", relatorio);
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
	
}
