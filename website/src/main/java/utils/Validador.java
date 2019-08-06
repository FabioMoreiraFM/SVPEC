package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Responsável pela validação dos dados inseridos pelo usuário.
 * 
 * @author Fabio Moreira
 * @version 1.0
 *
 */
public class Validador {
	
	/** Coleção de Expressões Regulares. */
	public enum Regex {
		CPF("^[0-9]{11}$"), 
		NOME("^[A-Za-z\\.\\’ \\-]{2,30}$");
		
		private String regex;
		
		public String getRegex() {
			return this.regex;
		}
		
		private Regex(String regex) {
			this.regex = regex;
		}
	};
	
	/**
	 * Valida uma expressão com relação a uma determinada expressão regular.
	 * @param dados Dados a serem validados
	 * @param regex Expressão regular utilizada para realizar a validação.
	 * 
	 * @return True, se o dado foi validado com sucesso. False, caso contrário.
	 */
	public static boolean validarExpressao(String dados, Regex regex) {
		Pattern regexPattern = Pattern.compile(regex.getRegex());
		Matcher regexMatcher = regexPattern.matcher(dados);
		
		return regexMatcher.matches();
	}
}
