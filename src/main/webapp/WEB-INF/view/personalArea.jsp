<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
	<head>
		<title>Личный кабинет</title>
	</head>
	<body>
	<form name="updateForm" method="post" action="/userupdate" th:object="${update}">
		Введите ваши данные:<br/>
		<table>
			<tr>
				<td>Имя:</td>
				<td><input type="text" name="name" value=${user.name} th:field="*{name}"/></td>
			</tr>
			<tr>
				<td>Фамилия:</td>
				<td><input type="text" name="surname" value=${user.surname} th:field="*{surname}"/></td>
			</tr>
			<tr>
				<td>Логин:</td>
				<td><input type="text" name="login" value=${user.login} th:field="*{login}"/></td>
			</tr>
			<tr>
				<td>Старый пароль:</td>
				<td><input type="password" name="oldPassword" th:field="*{oldPassword}"/></td>
			</tr>
			<tr>
				<td>Новый пароль:</td>
				<td><input type="password" name="password" th:field="*{password}"/></td>
			</tr>
		</table>
		${operationMessage}
		${errorUserExists} <br />
		<input type="submit" value="Сохранить изменения"/>
		<a href="main">Назад</a>
	</form>
	</body>
</html>