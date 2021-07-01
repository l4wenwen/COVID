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
</head>
<body>
<jsp:include page="template/import.jsp" />
<jsp:include page="template/navigate.jsp" />
<c:if test="${requestScope.statistic == null}">
    <c:redirect url="/statistic/all" />
</c:if>
${requestScope.statistic.totalNum} <br />
${requestScope.statistic.filledNum} <br />
${requestScope.statistic.highRiskNum} <br />
${requestScope.statistic.passRiskAreaNum} <br />
</body>
</html>
