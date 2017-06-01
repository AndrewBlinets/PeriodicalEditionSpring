<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Издания</title>
	</head>
	<body>
		<table border="1">
			<tr bgcolor="#CCCCCC">
					<td align="center"><strong>Имя</strong></td>
					<td align="center"><strong>Цена</strong></td>
			</tr>
			<c:forEach var="edition" items="${periodicalList}">
				<tr>
					<td><c:out value="${ edition.name }" /></td>
					<td><c:out value="${ edition.price }" /></td>
					<td><a href="<c:url value='/camelcase/remove/${edition.id}' />" >Delete</a></td></td>
				</tr>
			</c:forEach>
		</table>
		<a href="/addcamelcase">Добавить периодическое издание</a>
		<a href="/main">Назад</a>
	</body>
</html>