<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
    <c:forEach items="${ex.messages}" var="message">
        <li> ${message} </li>
    </c:forEach>
    <a href="${ex.backLink}">${ex.backTitle}</a>
</body>
</html>
