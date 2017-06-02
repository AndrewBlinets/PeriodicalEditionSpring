<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="../error/error.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html>
<head>
    <title>Добавления</title>
</head>
<body>
<form name="camelcaseForm" method="POST" action="/camelcase" th:object="${periodicalEditionDTO}">
    <input type="hidden" value="camelcase" />
    Введите ваши данные:<br/>
    <table>
        <tr>
            <td>названия:</td>
            <td><input type="text" name="nameCamelCase" value="" size="20" th:field="*{nameCamelCase}"/></td>
        </tr>
        <tr>
            <td>Цена:</td>
            <td><input type="text" name="price" value="" size="20" th:field="*{price}"/></td>
        </tr>
        <tr>
            <td>Логин редактора:</td>
            <td><input type="text" name="login" value="" size="20" th:field="*{login}"/></td>
        </tr>
        <tr>
            <td>Пароль редактора:</td>
            <td><input type="text" name="password" value="" size="20" th:field="*{password}"/></td>
        </tr>
        <tr>
            <td>Имя редактора:</td>
            <td><input type="text" name="name" value="" size="20" th:field="*{name}"/></td>
        </tr>
        <tr>
            <td>Фамилия редактора:</td>
            <td><input type="text" name="surname" value="" size="20" th:field="*{surname}"/></td>
        </tr>
    </table>
    ${operationMessage}
    ${errorCamelCaseExists} <br />
    <input type="submit" value="Добавить" />
    <a href="/main">Назад</a>
</form>


</body>
</html>