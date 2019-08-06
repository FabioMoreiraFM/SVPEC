package modelo;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Relatorio {
	
	public Map<String, Map<Integer, String>> relatorio;
	
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
