<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:out value="Hello: ${loginedUser.name}"/>

<h2><a href="<c:url value="/userlist"/>">Список пользователей</a></h2>
</body>
</html>
