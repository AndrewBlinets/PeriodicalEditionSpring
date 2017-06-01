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
		<h4>Выберите операцию:</h4>
		<a href="controller?command=editions">Добавить новость</a> <br/>
		<a href="controller?command=logout">Выйти из системы</a> <br/>
	</body>
</html>