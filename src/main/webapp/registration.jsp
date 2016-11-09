<%--
  Created by IntelliJ IDEA.
  User: innopolis
  Date: 30.10.16
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
    <h3>Регистрация</h3>
    <p style="color:red;">${error}</p>
    <form action="registration" method="post">
        E-mail: <input type="email" name="email"><br>
        Password: <input type="password" name="password"><br>
        Name: <input type="text" name="name"><br>
        Age: <input type="text" name="age"><br>
        <button type="submit">Регистрация</button>
    </form>
    <a href="/login">Войти</a>
</body>
</html>
