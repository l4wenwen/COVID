<%--
  Created by IntelliJ IDEA.
  User: sunyin
  Date: 2021/6/30
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>adminHome</title>
</head>
<body>
你好：${sessionScope.user.userName}
<a href="${pageContext.request.contextPath}/vacation/list">查看请假请求</a>
<a href="${pageContext.request.contextPath}/state/list">查看请假请求</a>
</body>
</html>
