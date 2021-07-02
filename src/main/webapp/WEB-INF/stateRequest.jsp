<%--
  Created by IntelliJ IDEA.
  User: sunyin
  Date: 2021/6/30
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>addState</title>
    <%@ include file="template/import.jsp" %>
</head>
<body>
<jsp:include page="template/navigate.jsp">
    <jsp:param name="type" value="${sessionScope.user.userType}"/>
</jsp:include>
<div class="container">
    <jsp:include page="template/header.jsp" >
        <jsp:param name="position" value="State Request"/>
        <jsp:param name="userName" value="${sessionScope.user.userName}"/>
    </jsp:include>
    <div class="content">
        <form action="${pageContext.request.contextPath}/state/request" method="post">
            今日体温是否正常？<br />
            否<input type="radio" name="isTemperature" value="0" checked>
            是<input type="radio" name="isTemperature" value="1">
            <br />
            是否确诊为新冠？<br />
            否<input type="radio" name="isCovid" value="0" checked>
            是<input type="radio" name="isCovid" value="1">
            <br />
            是否疑似为新冠？<br />
            否<input type="radio" name="isLikeCovid" value="0" checked>
            是<input type="radio" name="isLikeCovid" value="1">
            <br />
            当前的隔离状态？<br />
            未曾隔离<input type="radio" name="quarantine" value="0" checked>
            曾隔离，已解除<input type="radio" name="quarantine" value="1">
            正在居家隔离<input type="radio" name="quarantine" value="2">
            正在集中隔离<input type="radio" name="quarantine" value="3">
            <br />
            本人同住亲友近14天内是否途径或停留疫情中高风险地区？<br />
            否<input type="radio" name="isRecentArea" value="0" checked>
            是<input type="radio" name="isRecentArea" value="1">
            <br />
            近14天是否曾在国(境)外旅行、居住？<br />
            否<input type="radio" name="isRecentCountry" value="0" checked>
            是<input type="radio" name="isRecentCountry" value="1">
            <br />
            近14天是否曾接触过确诊或疑似新冠病例？<br />
            否<input type="radio" name="isRecentPeople" value="0" checked>
            是<input type="radio" name="isRecentPeople" value="1">
            <br />
            今日是否有以下相关症状(乏力、干咳 、鼻塞、流涕、咽痛、腹泻)？<br />
            无<input type="radio" name="symptom" value="0" checked>
            有<input type="radio" name="symptom" value="1">
            <br />
            今日是否存在其他个人健康异常情况？<br />
            否<input type="radio" name="isAbnormal" value="0" checked>
            是<input type="radio" name="isAbnormal" value="1">
            <br />
            今日健康码颜色？<br />
            绿色<input type="radio" name="healthCodeType" value="0" checked>
            黄色<input type="radio" name="healthCodeType" value="1">
            红色<input type="radio" name="healthCodeType" value="2">
            <br />
            今日是否出校？<br />
            否<input type="radio" name="isOutSchool" value="0" checked>
            是<input type="radio" name="isOutSchool" value="1">
            <br />
            今日是否出城？<br />
            否<input type="radio" name="isOutCity" value="0" checked>
            是<input type="radio" name="isOutCity" value="1">
            <br />
            <input type="submit" value="提交">
        </form>
    </div>
</div>
</body>
</html>
