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
    <%@ include file="template/import.jsp" %>
    <link rel="stylesheet" href="../css/input-submit-for-all.css" />
</head>
<body>
    <jsp:include page="template/navigate.jsp">
        <jsp:param name="type" value="${sessionScope.user.userType}"/>
    </jsp:include>
    <div class="container">
        <jsp:include page="template/header.jsp" >
            <jsp:param name="position" value="Vacation"/>
                <jsp:param name="userName" value="${sessionScope.user.userName}"/>
        </jsp:include>
        <div class="content">
            <c:if test="${sessionScope.user.userType == 2}">
                <form action="${pageContext.request.contextPath}/vacation/request" method="post">
                    <label>理由：<input type="text" name="reason" required="required"/></label>
                    <label>请假日期：<input type="datetime-local" name="startTime" required="required"/></label>
                    <label>返校日期：<input type="datetime-local" name="endTime" required="required"/></label>
                    <label>交通工具：<input type="text" name="transport" required="required"></label>
                    <input type="submit" value="提交" />
                </form>
            </c:if>
            <table class="chart">
                <tr class="title">
                    <th>姓名</th>
                    <th>原因</th>
                    <th>起始时间</th>
                    <th>返校时间</th>
                    <th>离校方法</th>
                    <th>处理状态</th>
                    <th>请求时间</th>
                    <th>操作</th>
                </tr>

                <c:if test="${not empty requestScope.vacations}">
                    <c:forEach var="vacation" items="${requestScope.vacations}" >
                        <tr class="item">
                            <td>${vacation.userName}</td>
                            <td>${vacation.reason}</td>
                            <td>${vacation.startTime}</td>
                            <td>${vacation.endTime}</td>
                            <td>${vacation.way}</td>
                            <td>${vacation.state == 0 ? "未处理" : (vacation.state == 1 ? "拒绝" : "同意")}</td>
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
