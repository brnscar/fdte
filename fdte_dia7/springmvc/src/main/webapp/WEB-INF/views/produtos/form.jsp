<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOJA</title>
</head>
<body>
	<form action="/springmvc/produtos" method="POST">
<div>
	<label>Sugestões</label>	
	
</div>

<c:forEach items="${tipos}" var="tipoMarca" varStatus="status">
    <div>
        <label>${tipoMarca}</label>
        <input type="text" name="marcas[${status.index}].marca">
        <input type="hidden" name="marcas[${status.index}].tipo" value="${tipoMarca}">
    </div>
</c:forEach>
	<button type ="submit">Cadastrar</button>
	</form>
</body>
</html>