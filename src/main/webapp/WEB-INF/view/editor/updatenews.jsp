<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Обновления новости</title>
</head>
<body>
<form name="newsForm" method="POST" action="/updateNews" th:object="${news}">
    <input type="hidden" value="news" />
    Введите ваши данные:<br/>
    <table>
        <tr>
            <td>Заголовок:</td>
            <td><input type="text" name="title" value=${news.title} th:field="*{title}"/></td>
        </tr>
        <tr>
            <td>Текст:</td>
            <td><textarea name="body" cols="20" value=${news.body} rows="5" th:field="*{body}" ></textarea> </td>
            <!-- <td><input type="" name="body" value="" size="20" th:field="*{body}"/></td>-->
        </tr>
        <tr>
            <td>Автор:</td>
            <td><input type="text" name="author" value=${news.author} th:field="*{author}"/></td>
        </tr>
    </table>
    ${operationMessage}
    ${errorCamelCaseExists} <br />
    <input type="submit" value="Обновить" />
    <a href="/main">Назад</a>
</form>


</body>
</body>
</html>
