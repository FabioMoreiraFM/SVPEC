package modelo;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Classe Relatorio
 * 
 * Produz um relatório completo sobre a eleição escolhida, incluindo
 * quantidade total de votos e quantidade de votos que cada candidato recebeu.
 * 
 * @author Fabio Moreira
 * @version 1.0
 */
public class Relatorio {
	
	/** Contém as informações que serão exibidas sobre uma eleição. Padrão: <Cargo, <QtdVotos, Candidato>>*/
	public Map<String, Map<Integer, String>> relatorio;
	
	/**
	 * Construtor da classe Relatório.
	 * 
	 * @param pontuaram Lista de candidatos que obteram ao menos um voto.
	 * @param zeraram Lista de candidatos que não obteram votos.
	 */
	public Relatorio(List<Object[]> pontuaram, List<Object[]> zeraram) {
		relatorio = new HashMap<String, Map<Integer, String>>();
		
		for (Object[] obj: pontuaram) {
			if (!relatorio.containsKey(obj[0])) {
				relatorio.put((String) obj[0], new HashMap<Integer, String>());
			}
			
			relatorio.get(obj[0]).put(((BigInteger) obj[2]).intValue(), (String) obj[1]);		
		}
		
		for (Object[] obj: zeraram) {
			if (!relatorio.containsKey(obj[0])) {
				relatorio.put((String) obj[0], new HashMap<Integer, String>());
			}
			
			relatorio.get(obj[0]).put(0, (String) obj[1]);		
		}
	}
	
	/**
	 * Formata o relatório para ser exibido no navegador.
	 * Cada linha contém: Nome do cargo, nome do candidato e quantidade de votos obtidos.
	 */
	@Override
	public String toString() {
		String linhas = "";
		
		for (Entry<String, Map<Integer, String>> cargo : relatorio.entrySet()) {
			for (Entry<Integer, String> candidato : cargo.getValue().entrySet()) {
				linhas = cargo.getKey() + ":        " + candidato.getValue() + "        " + candidato.getKey() + " votos  <br>  " + linhas;
			}
			linhas = " <br> " + linhas;
		}
		
		return linhas;
	}
}
