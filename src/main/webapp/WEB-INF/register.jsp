<%--
  Created by IntelliJ IDEA.
  User: Fish_Brother
  Date: 2021-06-30
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form action="user/register" method="post">
    <label>用户名：<input type="text" name="account" /></label> <br />
    <label>密码：<input type="password" name="password" /></label> <br />
    <label>重复密码：<input type="password" name="repassword" /></label> <br />
    <input type="submit" value="提交">
    <a href="user/login">登陆</a>
</form>
</body>
</html>
