package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;

/**
 * Processa tarefas relacionadas à imagens.
 * 
 * @author Fabio Moreira
 * @version 1.0
 */
public class Imagem {
	
	/** Status do resultado do processamento de uma imagem. */
	public enum ImagemStatus {OK, NaoEhImagem, Vazio};
	
	/**
	 * Carrega uma imagem passada pelo usuário e salva no local especificado.
	 * 
	 * @param localFoto Diretório da imagem.
	 * @param nomeFoto Nome da imagem.
	 * 	 
	 * @return O status do resultado do processamento.
	 * 
	 * @throws IOException Falha na leitura ou escrita de dados.
	 */
	public static ImagemStatus processaImagem(Part localFoto, String nomeFoto) throws IOException {
		 
		if (localFoto.getSize() != 0) {		    
		    if (ImageIO.read(localFoto.getInputStream()) != null) { // ImageIO.read suja o InputStream
		    	
		    	InputStream fileContent = localFoto.getInputStream();
			    File path = new File(Paths.get("./../imagens").toAbsolutePath().normalize().toString() + "/" + nomeFoto);
			    				
			    if (!path.exists())
					Files.copy(fileContent, path.toPath());
				
			    fileContent.close();
			    
			    return ImagemStatus.OK; 
		    } else {
		    	return ImagemStatus.NaoEhImagem; 
		    }
	    } else {
	    	return ImagemStatus.Vazio; 
	    }
	}
}
