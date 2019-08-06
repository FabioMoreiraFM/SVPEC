package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fimVotacao")
public class FimVotacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Properties prop = new Properties();
	private InputStream input = getClass().getClassLoader().getResourceAsStream("/mensagem.properties");	
	
    public FimVotacao() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (prop.isEmpty())
			prop.load(input);
		
		String url = prop.getProperty("urlWelcomePage");
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
