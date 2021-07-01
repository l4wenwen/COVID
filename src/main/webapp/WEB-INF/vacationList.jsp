<%--
  Created by IntelliJ IDEA.
  User: Fish_Brother
  Date: 2021-06-30
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生请假记录</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
    <jsp:include page="template/import.jsp" />
    <jsp:include page="template/navigate.jsp" />
    <p style="color: red">${requestScope.message}</p>
    <div class="container flex-row-center">
        <div class="panel panel-default">
            <table class="table">
                <div class="panel-heading">我的请求</div>
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
                            <td>${sessionScope.user.userName}</td>
                            <td>${vacation.reason}</td>
                            <td>${vacation.startTime}</td>
                            <td>${vacation.endTime}</td>
                            <td>${vacation.way}</td>
                            <td>${vacation.state}</td>
                            <td>${vacation.requestTime}</td>
                            <td>
                                <c:if test="${sessionScope.user.userType == 2 && vacation.state == 0}">
                                    <a href="${pageContext.request.contextPath}/vacation/revoke?vid=${vacation.vacationNum}&user_id=${vacation.userNum}">撤回</a>
                                </c:if>
                                <c:if test="${sessionScope.user.userType == 1 && vacation.state == 0}">
                                    <a href="${pageContext.request.contextPath}/vacation/operate?vid=${vacation.vacationNum}&operation=2">同意</a>
                                    <a href="${pageContext.request.contextPath}/vacation/operate?vid=${vacation.vacationNum}&operation=1">拒绝</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </div>
    </div>
</body>
</html>
