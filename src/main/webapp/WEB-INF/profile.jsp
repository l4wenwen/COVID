<%--
  Created by IntelliJ IDEA.
  User: Fish_Brother
  Date: 2021-07-01
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息</title>
</head>
<body>
<jsp:include page="template/navigate.jsp" />
姓名：${sessionScope.user.userName} <br/>
性别：${sessionScope.user.sex ? "男":"女"} <br/>
</body>
</html>
