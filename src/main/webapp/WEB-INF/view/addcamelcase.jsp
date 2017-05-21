<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="error/error.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html>
<head>
    <title>Добавления</title>
</head>
<body>
<form name="camelcaseForm" method="POST" action="/camelcase" th:object="${camelcase}">
    <input type="hidden" value="camelcase" />
    Введите ваши данные:<br/>
    <table>
        <tr>
            <td>названия:</td>
            <td><input type="text" name="name" value="" size="20" th:field="*{name}"/></td>
        </tr>
        <tr>
            <td>Цена:</td>
            <td><input type="text" name="price" value="" size="20" th:field="*{price}"/></td>
        </tr>
    </table>
    ${operationMessage}
    ${errorUserExists} <br />
    <input type="submit" value="Добавить" />
</form>


</body>
</html>