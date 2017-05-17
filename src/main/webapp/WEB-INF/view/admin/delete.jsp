<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
	<head>
		<title>Удаление</title>
	</head>
	<body>
		Пользователь <c:out value="${ user.name }" /> <c:out value="${ user.surname }" /> <br/>
		${information} <br/>
		<a href="controller?command=logout">Назад</a>
	</body>
</html>