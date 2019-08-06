<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastrar nova eleição</title>
</head>
<body>
	<h3>Cadastrar nova eleição:</h3>
	<small>Campos com * são obrigatórios</small><br><br>
	
	<form action="cadastrarNovaEleicao" method="post">
		<div style="color: #FF0000;">
			<small>${errorNome}</small>
		</div>
		<label>Nome<small>*</small>: </label>
		<input type="text" name="nomeEleicao">
		<br>
		
		<div style="color: #FF0000;">
			<small>${errorDataIni}</small>
		</div>
		<label>Data de início<small>*</small>: </label>
		<input type="date" name="dataEleicaoIni">
		<br>
		
		<div style="color: #FF0000;">
			<small>${errorDataFim}</small>
		</div>
		<label>Data de fim<small>*</small>: </label>
		<input type="date" name="dataEleicaoFim">
		<br>
		
		<input type="submit" value="Send">
	</form>

</body>
</html>