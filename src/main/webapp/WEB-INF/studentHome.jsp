<%--
  Created by IntelliJ IDEA.
  User: sunyin
  Date: 2021/6/30
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>studentHome</title>
</head>
<body>
你好：${sessionScope.user.userName}
<a href="${pageContext.request.contextPath}/vacation/request">请假</a>
<a href="${pageContext.request.contextPath}/vacation/list">查看请假请求</a>
<a href="${pageContext.request.contextPath}/user/profile">查看个人资料</a>
</body>
</html>
