<%--
  Created by IntelliJ IDEA.
  User: sunyin
  Date: 2021/6/30
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>teacherHome</title>
</head>
<body>
你好：${sessionScope.user.name}
<a href="${pageContext.request.contextPath}/vacation/list">查看请假请求</a>
</body>
</html>
