<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Solo Project</title>
</head>
<body>
<form action = "/userlist" method="post">
    Choose the database connection type
    <select name = "connectionType">
        <option value="hibernate">hibernate</option>
        <option value="JDBC">JDBC</option>
    </select>
    <br/><br/>
    <input type = "submit" value="Submit" />
</form>

</body>
</html>
