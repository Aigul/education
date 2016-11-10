<%--
  Created by IntelliJ IDEA.
  User: innopolis
  Date: 28.10.16
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Вход</title>
  </head>
  <body>
    <h3>Вход</h3>
    <p style="color:red;">${error}</p>
    <form action="login" method="post">
      E-mail: <input type="email" name="email"><br>
      Password: <input type="password" name="password"><br>
      <button type="submit">Войти</button><br>
    </form>
    <a href="/registration">Регистрация</a>
  </body>
</html>
