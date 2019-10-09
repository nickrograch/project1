<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Solo Project</title>
</head>
<body>
<h1><strong>ACCESS DENIED!!!</strong></h1>
<form method="get" action="/login">
    <a href="<c:url value="/login"/>">Авторизоваться под другим пользователем</a>
</form>
</body>
</html>
