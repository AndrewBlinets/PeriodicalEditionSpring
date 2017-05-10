<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html>
	<head>
		<title>Добавление</title>
	</head>
	<body>
		<form name="addForm" method="POST" action="controller">
			<input type="hidden" name="command" value="addedition" />
			Введите ваши данные:<br/>
			<table>
				<tr>
					<td>Название:</td>
					<td><input type="text" name="nameedition" value="" size="20"/></td>
				</tr>
				<tr>
					<td>Цена:</td>
					<td><input type="text" name="price" value="" size="20"/></td>
				</tr>

			</table>
			<input type="submit" value="Добавить" />
			${operationMessage}
			${errorUserExists} <br />
			<a href="controller?command=backadmin">Вернуться обратно</a>
		</form>
		
		
	</body>
</html>