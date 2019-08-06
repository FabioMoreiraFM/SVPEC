<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastrar novo candidato</title>
</head>
<body>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	 
	<h3>Cadastrar novo candidato:</h3>
	
	<small>Campos com * são obrigatórios:</small><br><br>
	
	<form action="cadastrarNovoCandidato" method="post" enctype="multipart/form-data">
		<div style="color: #FF0000;">
			<small>${errorFoto}</small>
		</div>
		<label>Foto<small>*</small>: </label>
		<input type="file" name="localFoto" value="Send" onchange='this.form.submit();'>
		<br><br>
			
		<div style="color: #FF0000;">
			<small>${errorNome}</small>
		</div>
		<label>Nome<small>*</small>: </label>
		<input type="text" name="nomeCandidato">
		<br><br>
			
		<label>Cargo: </label>
		<select name="cargos">
			<c:forEach items="${cargos}" var="cargo">
	        	<option value="${cargo.id}">${cargo.nome}</option>
	    	</c:forEach>
		</select>
		
		<br style = "line-height:3;">
		<input type="submit" name="btnSubmissao" value="Cadastrar e continuar cadastrando"><br>
		<input type="submit" name="btnSubmissao" value="Cadastrar e finalizar">
	</form>
	
	<img src=${localImagem } >

</body>
</html>