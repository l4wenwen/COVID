<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
    if (session.getAttribute("user") == null)
        response.sendRedirect("/user/login");
%>
你好
</body>
</html>
