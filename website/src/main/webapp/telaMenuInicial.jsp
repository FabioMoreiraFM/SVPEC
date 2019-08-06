<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SVEPC - Sistema de Vota��o de Entidades de Previd�ncia Complementar</title>
</head>
<body>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<h3>SVEPC - Sistema de Vota��o de Entidades de Previd�ncia Complementar!</h3>
	<h3>�rea do Usu�rio:</h3>
	
	<form action="menuInicial" method="post">
		<input type="submit" name="btnSubmissao" value="Cadastrar Nova Elei��o">
		<br>
		
		<c:if test="${not empty eleicoes}">
		<label>Votar para a elei��o:</label>
			<select name="eleicoes">
				<c:forEach items="${eleicoes}" var="eleicao">
					<c:if test="${eleicao.id eq eleicao.id}">
			      		<option value="${eleicao.id}">${eleicao.nome}</option>
			      	</c:if>
			   	</c:forEach>
			</select><br>
		<input type="submit" name="btnSubmissao" value="Votar">
		</c:if>	
	</form>

</body>
</html>