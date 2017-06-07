<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>Мои подписки</title>
	</head>
	<body>
		<table border="1">
			<tr bgcolor="#CCCCCC">
					<td align="center"><strong>Имя</strong></td>
			</tr>
			<c:forEach var="edition" items="${periodicalList}">
				<tr>
					<td><c:out value="${ edition.name }" /></td>
					<td><a href="<c:url value='/subscription/remove/${edition.id}' />" >Отписаться</a></td></td>
				</tr>
			</c:forEach>
		</table>
		<a href="/main">Назад</a>
	</body>
</html>