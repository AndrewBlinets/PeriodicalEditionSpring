<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error/error.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html>
	<head>
		<title>Регистрация</title>
	</head>
	<body>
		<form name="registrationForm" method="POST" action="/registration" th:object="${registration}">
			<input type="hidden" name="command" value="registration" />
			Введите ваши данные:<br/>
			<table>
				<tr>
					<td>Имя:</td>
					<td><input type="text" name="name" value="" size="20" th:field="*{name}"/></td>
				</tr>
				<tr>
					<td>Фамилия:</td>
					<td><input type="text" name="surname" value="" size="20" th:field="*{surname}"/></td>
				</tr>
				<tr>
					<td>Логин:</td>
					<td><input type="text" name="login" value="" size="20" th:field="*{login}"/></td>
				</tr>
				<tr>
					<td>Пароль:</td>
					<td><input type="password" name="password" value="" size="20" th:field="*{hashpassword}" /></td>
				</tr>
			</table>			 
			${operationMessage} 
			${errorUserExists} <br />
			<input type="submit" value="Зарегистрировать" />
			<a href="index">Назад</a>
		</form>
		
		
	</body>
</html>