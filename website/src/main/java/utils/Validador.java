package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {
	
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
	
	public static boolean validarExpressao(String nome, Regex regex) {
		Pattern regexPattern = Pattern.compile(regex.getRegex());
		Matcher regexMatcher = regexPattern.matcher(nome);
		
		return regexMatcher.matches();
	}
}
