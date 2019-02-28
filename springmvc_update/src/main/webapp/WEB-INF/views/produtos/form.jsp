<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<div>
	<label>Camisetas</label> <input type="text" name=camisetas>
</div>
<div>
	<label>Tênis</label> <input type="text" name=tenis>
</div>
<div>
	<label>Blusas</label> <input type="text" name=blusas>
</div>
	<button type ="submit">Cadastrar</button>
	</form>
</body>
</html>