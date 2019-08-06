<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Área do eleitor</title>
</head>
<body>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<h3>Área do eleitor</h3>
	<small>Campos com * são obrigatórios:</small><br><br>
	
	<form action="areaEleitor" method="post">
	
		<div style="color: #FF0000;">
			<small>${errorNome}</small>
		</div>
		<label>Nome<small>*</small>: </label>
		<input type="text" name="nome">
		<br>
		
		<div style="color: #FF0000;">
			<small>${errorCPF}</small>
		</div>
		<label>CPF<small>*</small>: </label>
		<input type="text" name="cpf">
		<small>* Apenas números</small>
		<br>
		
		Vote para cada cargo abaixo: <br>
		
		<c:forEach items="${cargos}" var="cargo">
		<label>${cargo.nome}:</label>
			<select name="candidatos">
				<c:forEach items="${candidatos}" var="candidato">
					<c:if test="${cargo.id eq candidato.id_cargo}">
			      		<option value="${candidato.id}">${candidato.nome}</option>
			      	</c:if>
			   	</c:forEach>
			</select>
			<br>
		</c:forEach>
		
		<input type="hidden" name="eleicaoAtual" value="${eleicaoEscolhida}">
		<input type="submit" value="Enviar">
	</form>

</body>
</html>