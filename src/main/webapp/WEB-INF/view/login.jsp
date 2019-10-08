<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Solo Project</title>
</head>
<body bgcolor="##ffff00">
<form method="post" >
    <div style="text-align: center;">
        <h1>Добро пожаловать, авторизируйтесь</h1>
        <br>
        Логин:<label>
        <input type="text" name="name">
    </label>
        Пароль:<label>
        <input type="password" name="pass">
    </label>
        <input type="submit" value="Войти">
        <br>
        <h2><a href="<c:url value="/registration"/>">Регистрация</a></h2>
    </div>
</form>
</body>
</html>
