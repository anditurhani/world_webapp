<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Continents</title>
</head>
<body>

	<div style="text-align: right">
		<a href="http://localhost:8080/world/searchForm">search city</a>
	</div>
	<div style="text-align: right">
		<a href="http://localhost:8080/world/updateForm?id=-1">add city</a>
	</div>
	<ul>
		<c:forEach var="continent" items="${continentList}">
			<li><a
				href="http://localhost:8080/world/countries?continent=${continent}">${continent}</a></li>
		</c:forEach>
	</ul>
</body>
</html>