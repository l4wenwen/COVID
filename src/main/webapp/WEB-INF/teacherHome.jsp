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
    <title>adminHome</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ include file="template/import.jsp" %>
</head>
<body>
<%request.setCharacterEncoding("UTF-8");%>
<c:if test="${requestScope.statistic == null}">
    <c:redirect url="${pageContext.request.contextPath}/statistic/all" />
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
            <div class="state">
                <jsp:include page="template/wrapper.jsp" >
                    <jsp:param name="bgcolor" value="bgcolor-blue"/>
                    <jsp:param name="bgcolor_dark" value="bgcolor-darkblue"/>
                    <jsp:param name="title" value="总检测人数"/>
                    <jsp:param name="number" value="${requestScope.statistic.totalNum}"/>
                    <jsp:param name="url" value="/user/userinfo"/>
                </jsp:include>

                <jsp:include page="template/wrapper.jsp" >
                    <jsp:param name="bgcolor" value="bgcolor-green"/>
                    <jsp:param name="bgcolor_dark" value="bgcolor-darkgreen"/>
                    <jsp:param name="title" value="已填报人数"/>
                    <jsp:param name="number" value="${requestScope.statistic.filledNum}"/>
                    <jsp:param name="url" value="/statistic/filled"/>
                </jsp:include>

                <jsp:include page="template/wrapper.jsp" >
                    <jsp:param name="bgcolor" value="bgcolor-red"/>
                    <jsp:param name="bgcolor_dark" value="bgcolor-darkred"/>
                    <jsp:param name="title" value="今日高危人数"/>
                    <jsp:param name="number" value="${requestScope.statistic.highRiskNum}"/>
                    <jsp:param name="url" value="/statistic/highrisk"/>
                </jsp:include>

                <jsp:include page="template/wrapper.jsp" >
                    <jsp:param name="bgcolor" value="bgcolor-yellow"/>
                    <jsp:param name="bgcolor_dark" value="bgcolor-darkyellow"/>
                    <jsp:param name="title" value="今日经过高危地区人数"/>
                    <jsp:param name="number" value="${requestScope.statistic.highRiskNum}"/>
                    <jsp:param name="url" value="/statistic/riskarea"/>
                </jsp:include>

            </div>
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
