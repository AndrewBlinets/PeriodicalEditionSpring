<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Пользователи</title>
</head>
<body>

<table>
    <tr>
        <td align="center"><strong>Имя</strong></td>
        <td align="center"><strong>Фамилия</strong></td>
        <td></td>
        <td></td>
    </tr>
    <c:forEach var="reader" items="${readerList}">
        <tr>
            <td><c:out value="${ reader.name }" /></td>
            <td><c:out value="${ reader.surname }" /></td>
            <td><c:out value="${ reader.userRole }" /></td>
            <td><a href="<c:url value='/user/remove/${reader.id}' />" >Delete</a></td></td>
        </tr>
    </c:forEach>
</table>
<a href="/main">Назад</a>
</body>
</html>
