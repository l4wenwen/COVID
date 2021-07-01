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
<form action="/state/list" method="post">
    开始日期：<input type="date" name="startTime" checked/>
    结束日期：<input type="date" name="endTime" checked/>
    <input type="submit" value="提交">
</form>
</body>
</html>
