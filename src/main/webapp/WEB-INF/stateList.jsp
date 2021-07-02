<%--
  Created by IntelliJ IDEA.
  User: sunyin
  Date: 2021/7/1
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生打卡记录</title>
    <%@ include file="template/import.jsp" %>
</head>
<body>
    <jsp:include page="template/navigate.jsp">
        <jsp:param name="type" value="${sessionScope.user.userType}"/>
    </jsp:include>
    <div class="container">
        <jsp:include page="template/header.jsp" >
            <jsp:param name="position" value="State"/>
            <jsp:param name="userName" value="${sessionScope.user.userName}"/>
        </jsp:include>
        <div class="content">
            <div class="state">
                    <form action="${pageContext.request.contextPath}/state/list" method="post">
                        <label>开始日期：<input type="date" name="startTime" checked/></label>
                        <label>结束日期：<input type="date" name="endTime" checked/></label>
                        <input type="submit" value="查询">
                    </form>
                <br />
            </div>
            <table class="chart">
                <tr class="title">
                    <th>打卡编号</th>
                    <th>用户编号</th>
                    <th>打卡时间</th>
                    <th>体温异常</th>
                    <th>确诊新冠</th>
                    <th>疑似新冠</th>
                    <th>隔离状态</th>
                    <th>途经疫情中高风险地区</th>
                    <th>近14天国(境)外出行</th>
                    <th>近14天接触新冠病例</th>
                    <th>今日相关症状</th>
                    <th>今日健康异常</th>
                    <th>今日健康码颜色</th>
                    <th>今日出校</th>
                    <th>今日出城</th>
                </tr>

                <c:if test="${not empty requestScope.states}">
                    <c:forEach items="${requestScope.states}" varStatus="i" var="state">
                        <tr>
                            <td>${state.stateNum}</td>
                            <td>${state.userNum}</td>
                            <td>${state.stateTime}</td>
                            <td>${state.temperature}</td>
                            <td>${state.covid}</td>
                            <td>${state.likeCovid}</td>
                            <td>${state.quarantine}</td>
                            <td>${state.recentArea}</td>
                            <td>${state.recentCountry}</td>
                            <td>${state.recentPeople}</td>
                            <td>${state.symptom}</td>
                            <td>${state.abnormal}</td>
                            <td>${state.healthCodeType}</td>
                            <td>${state.outSchool}</td>
                            <td>${state.outCity}</td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </div>
    </div>
</body>
</html>
