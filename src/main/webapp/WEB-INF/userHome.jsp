<%--
  Created by IntelliJ IDEA.
  User: sunyin
  Date: 2021/6/30
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>studentHome</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:include page="template/import.jsp" />
</head>
<body>
<%request.setCharacterEncoding("UTF-8");%>
<c:if test="${requestScope.statistic == null}">
    <c:redirect url="/statistic/all" />
</c:if>
<jsp:include page="template/navigate.jsp">
    <jsp:param name="type" value="${sessionScope.user.userType}"/>
</jsp:include>
<div class="container">
    <jsp:include page="template/header.jsp" >
        <jsp:param name="position" value="HOME"/>
        <jsp:param name="userName" value="${sessionScope.user.userName}"/>
    </jsp:include>
    <div class="context">
        <div class="content">
            <c:if test="${sessionScope.user.userType != 2}">
                <jsp:include page="template/state.jsp" />
            </c:if>
            <table class="chart">
                <tr class="title">
                    <th>姓名</th>
                    <th>学号</th>
                    <th>性别</th>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>