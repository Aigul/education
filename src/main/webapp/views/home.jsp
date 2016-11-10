<%--
  Created by IntelliJ IDEA.
  User: innopolis
  Date: 30.10.16
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Личный кабинет</title>
</head>
<body>
    <h3>Добро пожаловать, ${name}</h3>
    <p style="color:red;">${error}</p>
    <p style="color:green;">${sessionScope.message}</p>
    <br>
    <form action="home" method="post">
        Имя <input type="text" value="${name}" name="name"> <br>
        Возраст <input type="text" value="${age}" name="age"> <br>
        E-mail <input type="email" value="${email}" name="email"> <br>
        Пароль <input type="text" value="${password}" name="password"> <br>
        <button type="submit">Обновить</button><br>
    </form>
    <a href="/logout">Выйти</a>
</body>
</html>
