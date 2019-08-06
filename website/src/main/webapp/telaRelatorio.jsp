<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>�rea do Administrador - Relat�rio</title>
</head>
<body>
	<h3>Relat�rio das elei��es</h3>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<form action="resultadoRelatorio" method="post">
	
		<c:if test="${not empty eleicoesAndamento}">
			<label>Relat�rios parciais:</label>
			<select name="relParcial">
				<c:forEach items="${eleicoesAndamento}" var="eleicao">
					<option value="${eleicao.id}">${eleicao.nome}</option>
			   	</c:forEach>
			</select><br>
			<input type="submit" name="btnSubmissao" value="Apresentar relat�rio parcial">
			<br><br>
		</c:if>
		
		
		<c:if test="${not empty eleicoesFinalizadas}">
			<label>Relat�rios finais:</label>
			<select name="relFinal">
				<c:forEach items="${eleicoesFinalizadas}" var="eleicao">
			      		<option value="${eleicao.id}">${eleicao.nome}</option>
			   	</c:forEach>
			</select><br>
			<input type="submit" name="btnSubmissao" value="Apresentar relat�rio final">
		</c:if>
	
	</form>

</body>
</html>