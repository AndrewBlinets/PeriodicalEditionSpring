<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
	<head>
		<title>Добро пожаловать</title>
	</head>
	<body>
		<h2>${user.name} </h2>
		<h3>Вы вошли в систему как редактор</h3>
		<a href="/personalArea">Личный кабинет</a> <br/>
		<h4>Выберите операцию:</h4>
		${operationMessage}<br/>
		<a href="/addNewsPage">Добавить новость</a> <br/>
		<a href="/news">Новости</a> <br/>
		<a href="/index">Выйти из системы</a> <br/>
	</body>
</html>