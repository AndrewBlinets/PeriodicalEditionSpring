<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html>
<head>
    <title>Авторизация</title>
</head>
<body>
<form name="loginForm" method="POST" action="/main" th:object="${account}">
    Введите ваш логин и пароль: <br/>
    <table>
        <tr>
            <td>Логин:</td>
            <td><input type="text" name="login" size="20" th:field="*{login}"/></td>
        </tr>
        <tr>
            <td>Пароль:</td>
            <td><input type="password" name="hashpassword" size="20" th:field="*{hashpassword}"/></td>
        </tr>
    </table>
    ${errorLoginOrPassword} <br />
    <input type="submit" value="Войти" />
    <a href="/registration">Регистрация</a>
</form>
</body>
</html>