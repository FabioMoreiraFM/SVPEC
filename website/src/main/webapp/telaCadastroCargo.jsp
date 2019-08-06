<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastrar novo cargo</title>
</head>
<body>
	<h3>Cadastrar novo cargo:</h3>
	
	<small>Campos com * são obrigatórios:</small><br><br>
	
	<form action="cadastrarNovoCargo" method="post">
		<div style="color: #FF0000;">
			<small>${errorNome}</small>
		</div>
		<label>Nome<small>*</small>: </label>
		<input type="text" name="nomeCargo"><br>
		
		<input type="submit" name="btnSubmissao" value="Enviar e continuar cadastrando"><br>	
		<input type="submit" name="btnSubmissao" value="Enviar e finalizar">
	</form>

</body>
</html>