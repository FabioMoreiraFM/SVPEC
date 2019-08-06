package utils;

public class GeradorAleatorio {
	
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
