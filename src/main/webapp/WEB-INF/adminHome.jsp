<%--
  Created by IntelliJ IDEA.
  User: sunyin
  Date: 2021/6/30
  Time: 19:44
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
<%@ include file="template/navigate.jsp" %>
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
                </jsp:include>

                <jsp:include page="template/wrapper.jsp" >
                    <jsp:param name="bgcolor" value="bgcolor-green"/>
                    <jsp:param name="bgcolor_dark" value="bgcolor-darkgreen"/>
                    <jsp:param name="title" value="已填报人数"/>
                    <jsp:param name="number" value="${requestScope.statistic.filledNum}"/>
                </jsp:include>

                <jsp:include page="template/wrapper.jsp" >
                    <jsp:param name="bgcolor" value="bgcolor-red"/>
                    <jsp:param name="bgcolor_dark" value="bgcolor-darkred"/>
                    <jsp:param name="title" value="今日高危人数"/>
                    <jsp:param name="number" value="${requestScope.statistic.highRiskNum}"/>
                </jsp:include>

                <jsp:include page="template/wrapper.jsp" >
                    <jsp:param name="bgcolor" value="bgcolor-yellow"/>
                    <jsp:param name="bgcolor_dark" value="bgcolor-darkyellow"/>
                    <jsp:param name="title" value="今日经过高危地区人数"/>
                    <jsp:param name="number" value="${requestScope.statistic.highRiskNum}"/>
                </jsp:include>

            </div>
            <table class="chart">
                <tr class="title">
                    <th>姓名</th>
                    <th>班级</th>
                    <th>学号</th>
                    <th>状态</th>
                </tr>
                <tr class="item">
                    <td>余将虎</td>
                    <td>软工193</td>
                    <td>1190309159</td>
                    <td>正常</td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>