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
<h1>Lista de sugestoes</h1>
<table>
<tr>
<td>Camisetas</td>
            <td>Tenis</td>
            </tr>
            
        <c:forEach items="${produtos}" var="produto">
<tr>
		<td>${produto.camisetas}</td>
				<td>${produto.tenis}</td>
		
</tr>
 </c:forEach>

</table>
</body>
</html>