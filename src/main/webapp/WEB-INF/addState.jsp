<%--
  Created by IntelliJ IDEA.
  User: sunyin
  Date: 2021/6/30
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addState</title>
</head>
<body>
<form action="/state/addState" method="post">
    今日体温是否正常？<br />
    否<input type="radio" name="isTemperature" value="0">
    是<input type="radio" name="isTemperature" value="1">
    <br />
    是否确诊为新冠？<br />
    否<input type="radio" name="isCovid" value="0">
    是<input type="radio" name="isCovid" value="1">
    <br />
    是否疑似为新冠？<br />
    否<input type="radio" name="isLikeCovid" value="0">
    是<input type="radio" name="isLikeCovid" value="1">
    <br />
    当前的隔离状态？<br />
    未曾隔离<input type="radio" name="quarantine" value="0">
    曾隔离，已解除<input type="radio" name="quarantine" value="1">
    正在居家隔离<input type="radio" name="quarantine" value="2">
    正在集中隔离<input type="radio" name="quarantine" value="3">
    <br />
    本人同住亲友近14天内是否途径或停留疫情中高风险地区？<br />
    否<input type="radio" name="isRecentArea" value="0">
    是<input type="radio" name="isRecentArea" value="1">
    <br />
    近14天是否曾在国(境)外旅行、居住？<br />
    否<input type="radio" name="isRecentCountry" value="0">
    是<input type="radio" name="isRecentCountry" value="1">
    <br />
    近14天是否曾接触过确诊或疑似新冠病例？<br />
    否<input type="radio" name="isRecentPeople" value="0">
    是<input type="radio" name="isRecentPeople" value="1">
    <br />
    今日是否有以下相关症状？<br />
    无<input type="checkbox" name="symptom" value="0">
    乏力<input type="checkbox" name="symptom" value="1">
    干咳<input type="checkbox" name="symptom" value="2">
    鼻塞<input type="checkbox" name="symptom" value="3">
    流涕<input type="checkbox" name="symptom" value="4">
    咽痛<input type="checkbox" name="symptom" value="5">
    腹泻<input type="checkbox" name="symptom" value="6">
    <br />
    今日是否存在其他个人健康异常情况？<br />
    否<input type="radio" name="isAbnormal" value="0">
    是<input type="radio" name="isAbnormal" value="1">
    <br />
    今日健康码颜色？<br />
    绿色<input type="radio" name="healthCodeColor" value="0">
    是<input type="radio" name="healthCodeColor" value="1">
    <br />
    否<input type="radio" name="isOutSchool" value="0">
    是<input type="radio" name="isOutSchool" value="1">
    <br />
    否<input type="radio" name="isOutCity" value="0">
    是<input type="radio" name="isOutCity" value="1">
    <br />
    <input type="submit" value="提交">
</form>
</body>
</html>
