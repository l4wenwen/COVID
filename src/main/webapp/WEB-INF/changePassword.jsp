<%--
  Created by IntelliJ IDEA.
  User: Fish_Brother
  Date: 2021-07-01
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/changePassword" method="post">
    <label>旧密码：<input type="password" name="oldPassword" /></label>
    <label>新密码：<input type="password" name="newPassword" /></label>
    <label>重复新密码：<input type="password" name="reNewPassword" /></label>
    <input type="submit" value="提交">
</form>
</body>
</html>
