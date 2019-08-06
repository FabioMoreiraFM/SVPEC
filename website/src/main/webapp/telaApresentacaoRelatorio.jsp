<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Apresentação do Relatório</title>
</head>
<body>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<c:if test="${not empty resultadoParcial}">
		<h3>Relatório parcial: </h3>
		<label>Quantidade de votos: ${resultadoParcial}   </label><small>*Considerando a quantidade de eleitores votantes</small>
	</c:if>
	
	<c:if test="${not empty resultadoFinal}">
		<h3>Relatório final: </h3>
		<label>Quantidade de votos: ${resultadoFinal}   </label><small>*Considerando a quantidade de eleitores votantes</small><br>
		<label>${relatorio}</label>
	</c:if>
	
	<form action="apresentacaoRelatorio" method="post">
		<input type="submit" value="Voltar">
	</form>

</body>
</html>