<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавления новости</title>
</head>
<body>
<form name="newsForm" method="POST" action="/addNews" th:object="${news}">
    <input type="hidden" value="news" />
    Введите ваши данные:<br/>
    <table>
        <tr>
            <td>Заголовок:</td>
            <td><input type="text" name="title" value="" size="20" th:field="*{title}"/></td>
        </tr>
        <tr>
            <td>Текст:</td>
            <td><input type="text" name="body" value="" size="20" th:field="*{body}"/></td>
        </tr>
        <tr>
            <td>Автор:</td>
            <td><input type="text" name="author" value="" size="20" th:field="*{author}"/></td>
        </tr>
    </table>
    ${operationMessage}
    ${errorCamelCaseExists} <br />
    <input type="submit" value="Добавить" />
    <a href="/main">Назад</a>
</form>


</body>
</body>
</html>
