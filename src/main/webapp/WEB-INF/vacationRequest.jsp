<%--
  Created by IntelliJ IDEA.
  User: Fish_Brother
  Date: 2021-06-30
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生请假</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ include file="template/import.jsp" %>
</head>
<body>
<jsp:include page="template/navigate.jsp">
    <jsp:param name="type" value="${sessionScope.user.userType}"/>
</jsp:include>
<div class="container">
    <jsp:include page="template/header.jsp" >
        <jsp:param name="position" value="Ask For Leave"/>
        <jsp:param name="userName" value="${sessionScope.user.userName}"/>
    </jsp:include>
    <div class="content">
        <form action="${pageContext.request.contextPath}/vacation/request" method="post">
            <label>理由：<input type="text" name="reason" /></label> <br />
            <label>请假日期：<input type="datetime-local" name="startTime" /></label> <br />
            <label>返校日期：<input type="datetime-local" name="endTime" /></label> <br />
            <label>交通工具：<input type="text" name="transport"></label> <br />
            <input type="submit" value="提交" />
        </form>
    </div>
</div>
</body>
</html>
