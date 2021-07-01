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
</head>
<body>
    <tr>
        <th>打卡编号</th>
        <th>用户编号</th>
        <th>打卡时间</th>
        <th>今日体温是否正常</th>
        <th>是否确诊为新冠</th>
        <th>是否疑似为新冠</th>
        <th>当前的隔离状态</th>
        <th>本人同住亲友近14天内是否途径或停留疫情中高风险地区</th>
        <th>近14天是否曾在国(境)外旅行、居住</th>
        <th>近14天是否曾接触过确诊或疑似新冠病例</th>
        <th>今日是否有以下相关症状(乏力、干咳 、鼻塞、流涕、咽痛、腹泻)</th>
        <th>今日是否存在其他个人健康异常情况</th>
        <th>今日健康码颜色</th>
        <th>今日是否出校</th>
        <th>今日是否出城</th>
    </tr>
    <c:forEach items="${states}" varStatus="i" var="state">
        <tr>
            <td>${state.stateNum}</td>
            <td>${state.userNum}</td>
            <td>${state.stateTime}</td>
            <td>${state.isTemperature}</td>
            <td>${state.isCovid}</td>
            <td>${state.isLikeCovid}</td>
            <td>${state.quarantine}</td>
            <td>${state.isRecentArea}</td>
            <td>${state.isRecentCountry}</td>
            <td>${state.isRecentPeople}</td>
            <td>${state.isSymptom}</td>
            <td>${state.isAbnormal}</td>
            <td>${state.healthCodeType}</td>
            <td>${state.isOutSchool}</td>
            <td>${state.isOutCity}</td>
        </tr>
    </c:forEach>
</body>
</html>