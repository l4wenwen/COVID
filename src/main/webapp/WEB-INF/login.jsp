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
</head>
<body>
    <form action="user/login" method="post">
        <label>账号：<input type="text" name="account" /></label> <br />
        <label>密码：<input type="password" name="password" /></label> <br />
        <input type="submit" value="提交">
        <a href="user/register">注册</a>
    </form>
</body>
</html>
