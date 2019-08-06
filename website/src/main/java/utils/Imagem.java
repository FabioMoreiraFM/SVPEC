package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

public class Imagem {
	
	public enum ImagemStatus {OK, NaoEhImagem, Vazio};
	
	public static ImagemStatus processaImagem(Part localFoto, String nomeFoto) throws IOException, ServletException {
		 
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
