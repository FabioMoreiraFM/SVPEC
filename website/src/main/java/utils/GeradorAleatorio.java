package utils;

/**
 * Classe responsável por métodos que geram informações aleatórias.
 * Por exemplo: protocolos, hashs, números aleatórios.
 * 
 * @author Fabio Moreira
 * @version 1.0
 */
public class GeradorAleatorio {
	
	/**
	 * Gera um protocolo alfanumérico de 16 dígitos, separado por um hífen a cada 4 dígitos.
	 * 
	 * @return O protocolo alfanumérico.
	 */
	public static  String geracaoProtocolo() {
		final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int count = 0;
		
		StringBuilder builder = new StringBuilder();
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		
		while (++count != 16) {

			character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			
			if (count % 4 != 0)
				builder.append(ALPHA_NUMERIC_STRING.charAt(character));
			else
				builder.append("-" + ALPHA_NUMERIC_STRING.charAt(character));
		}

		return builder.toString();
	}
}
