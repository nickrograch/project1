<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
    <h3>List of users</h3>

    <br>

    <table border="3">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Fathername</th>
        </tr>
        <c:forEach items="${requestScope.list}" var="user">
            <tr>
                <td> ${user.id} </td>
                <td> ${user.name} </td>
                <td> ${user.surname} </td>
                <td> ${user.fatherName} </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
