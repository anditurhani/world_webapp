<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cities</title>
</head>
<body>
	<div style="text-align: center">${message}</div>
	<c:forEach var="city" items="${cityList}">
		<ul>
			<li>${city.name}${city.population} <br>
			<a href="http://localhost:8080/world/updateForm?id=${city.id}">update</a>
			</li>
		</ul>
	</c:forEach>
	<div style="text-align: center">
		<a href="http://localhost:8080/world/countries">indietro</a>
	</div>
</body>
</html>