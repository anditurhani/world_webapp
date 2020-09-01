<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Countries</title>
</head>
<body>
	<ul>
		<c:forEach var="country" items="${countryList}">
			<li><a
				href="http://localhost:8080/world/cities?countrycode=${country.code}">${country.name}</a>
				${country.population}</li>
		</c:forEach>
	</ul>
	<div style="text-align: center">
		<a href="http://localhost:8080/world/continents">indietro</a>
	</div>
</body>
</html>