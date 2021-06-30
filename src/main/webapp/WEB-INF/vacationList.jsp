<%--
  Created by IntelliJ IDEA.
  User: Fish_Brother
  Date: 2021-06-30
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生请假记录</title>
</head>
<body>
    <c:if test="${sessionScope.user.userType == 2}">
        <a href="${pageContext.request.contextPath}/vacation/request">请假</a>
    </c:if>
    <a href="${pageContext.request.contextPath}/user/logout">登出</a>
    <p style="color: red">${requestScope.message}</p>
    <table>
        <th>姓名</th>
        <th>原因</th>
        <th>起始时间</th>
        <th>返校时间</th>
        <th>离校方法</th>
        <th>处理状态</th>
        <th>请求时间</th>
        <th>操作</th>
        <c:if test="${not empty requestScope.vacations}">
            <c:forEach var="vacation" items="${requestScope.vacations}" >
                <tr>
                    <td>${vacation.userName}</td>
                    <td>${vacation.reason}</td>
                    <td>${vacation.startTime}</td>
                    <td>${vacation.endTime}</td>
                    <td>${vacation.way}</td>
                    <td>${vacation.state}</td>
                    <td>${vacation.requestTime}</td>
                    <c:if test="${sessionScope.user.userType == 2 && vacation.state == 0}">
                        <td><a href="${pageContext.request.contextPath}/vacation/revoke?vid=${vacation.id}&user_id=${vacation.userId}">撤回</a></td>
                    </c:if>
                    <c:if test="${sessionScope.user.userType == 1 && vacation.state == 0}">
                        <td><a href="${pageContext.request.contextPath}/vacation/operate?vid=${vacation.id}&operation=2">同意</a></td>
                        <td><a href="${pageContext.request.contextPath}/vacation/operate?vid=${vacation.id}&operation=1">拒绝</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</body>
</html>
