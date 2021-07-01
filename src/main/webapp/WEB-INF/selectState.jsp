<%--
  Created by IntelliJ IDEA.
  User: sunyin
  Date: 2021/7/1
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>stateList</title>
</head>
<body>
<form action="/state/selectState" method="post">
    开始日期：<input type="date" name="startTime" checked/><br />
    结束日期：<input type="date" name="endTime" checked/><br />
    所在学院：<input type="text" name="collegeName" checked/><br />
    <input type="submit" value="提交">
</form>
<p style="color: red">${requestScope.message}</p>
</body>
</html>
