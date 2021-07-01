<%--
  Created by IntelliJ IDEA.
  User: Fish_Brother
  Date: 2021-06-30
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="../css/basic.css">
    <link rel="stylesheet" type="text/css" href="../css/form.css">
</head>
<body>
<form class="login-box" action="/user/login" method="post">
    <h1>Login</h1>
    <label><input type="text" name="account" placeholder="学号" /></label> <br />
    <label><input type="password" name="password" placeholder="密码"/></label> <br />
    <input type="submit" value="Login">
    <p style="color: red">${requestScope.message}</p>
<%--    <a href="/user/register">注册</a>--%>
</form>

</body>
</html>
