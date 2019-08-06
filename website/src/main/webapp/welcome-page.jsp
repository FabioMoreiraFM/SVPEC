<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SVEPC - Sistema de Votação de Entidades de Previdência Complementar</title>
</head>
<body>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<h3>Bem-Vindo ao SVEPC - Sistema de Votação de Entidades de Previdência Complementar!</h3>
	<h3>Área do Usuário:</h3>
	
	<form action="welcomePage" method="post">
		<input type="submit" name="btnSubmissao" value="Entrar">
	</form>
	
	
	<h3>Área do Administrador:</h3>
	
	<form action="welcomePage" method="post">
		<input type="submit" name="btnSubmissao" value="Relatórios">
	</form>

</body>
</html>