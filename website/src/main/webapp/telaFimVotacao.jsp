<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Votação finalizada!</title>
</head>
<body>

	<h3>Seu voto foi registrado!</h3>
	
	<label>Número de protocolo: </label>
	<label>${protocolo}</label>
	
	<form action="fimVotacao" method="post">
		<input type="submit" name="btnSubmissao" value="Finalizar"><br>
	</form>


</body>
</html>