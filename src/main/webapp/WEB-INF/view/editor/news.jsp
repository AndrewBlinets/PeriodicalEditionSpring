<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border="1">
        <c:forEach var="newsOne" items="${news}">
            <tr>
                <td><h2><c:out value="${ newsOne.title }"/></h2></td>
                <td><c:out value="${ newsOne.body }" /></td>
                <td><c:out value="${ newsOne.author }" /></td>
                <td><a href="<c:url value='/news/remove/${newsOne.id}' />" >Delete</a></td></td>
            </tr>
        </c:forEach>
    </table>
    ${operationMessage}
    <a href="/addNewsPage">Добавить новость</a>
    <a href="/main">Назад</a>
</body>
</html>
