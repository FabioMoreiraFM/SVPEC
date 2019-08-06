<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Área do Administrador - Relatório</title>
</head>
<body>
	<h3>Relatório das eleições</h3>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<form action="resultadoRelatorio" method="post">
	
		<c:if test="${not empty eleicoesAndamento}">
			<label>Relatórios parciais:</label>
			<select name="relParcial">
				<c:forEach items="${eleicoesAndamento}" var="eleicao">
					<option value="${eleicao.id}">${eleicao.nome}</option>
			   	</c:forEach>
			</select><br>
			<input type="submit" name="btnSubmissao" value="Apresentar relatório parcial">
			<br><br>
		</c:if>
		
		
		<c:if test="${not empty eleicoesFinalizadas}">
			<label>Relatórios finais:</label>
			<select name="relFinal">
				<c:forEach items="${eleicoesFinalizadas}" var="eleicao">
			      		<option value="${eleicao.id}">${eleicao.nome}</option>
			   	</c:forEach>
			</select><br>
			<input type="submit" name="btnSubmissao" value="Apresentar relatório final">
		</c:if>
	
	</form>

</body>
</html>