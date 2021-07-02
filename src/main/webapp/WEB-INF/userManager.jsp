<%--
  Created by IntelliJ IDEA.
  User: Fish_Brother
  Date: 2021-07-02
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:include page="template/import.jsp" />
</head>
<body>
<c:if test="${requestScope.studentNum == null || requestScope.teacherNum == null}">
    <c:redirect url="/user/userInfo" />
</c:if>
<jsp:include page="template/navigate.jsp">
    <jsp:param name="type" value="${sessionScope.user.userType}"/>
</jsp:include>
<div class="container">
    <jsp:include page="template/header.jsp" >
        <jsp:param name="position" value="用户管理"/>
        <jsp:param name="userName" value="${sessionScope.user.userName}"/>
    </jsp:include>
    <div class="context">
        <div class="content">
            <c:if test="${sessionScope.user.userType == 0}">
                <jsp:include page="template/userInfo.jsp" />
            </c:if>
            <jsp:include page="template/userChart.jsp" />
        </div>
    </div>
</div>
</body>
</html>