<%--
  Created by IntelliJ IDEA.
  User: sunyin
  Date: 2021/6/30
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>studentHome</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:include page="template/import.jsp" />
</head>
<body>
<%request.setCharacterEncoding("UTF-8");%>
<c:if test="${sessionScope.user.userType != 2 && requestScope.statistic == null}">
    <c:redirect url="/statistic/all" />
</c:if>
<c:if test="${sessionScope.user.userType == 2 && requestScope.state == null}">
    <c:redirect url="/state/getstate" />
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
            <c:if test="${sessionScope.user.userType != 2}">
                <jsp:include page="template/state.jsp" />
                <jsp:include page="template/userChart.jsp" />
            </c:if>
            <c:if test="${sessionScope.user.userType == 2}">
                <div id="qrcode" style="width:150px; height:150px; margin-top:15px;"></div>
                <script type="text/javascript">
                    var qrcode = new QRCode(document.getElementById("qrcode"), {
                        width : 150,
                        height : 150,
                        colorDark : "${requestScope.state == null ? "#777777" : (requestScope.state == 1 ? "#FF4646" : "#5E8B7E")}",
                        colorLight : "#FFF",
                        correctLevel : QRCode.CorrectLevel.H
                    });
                    qrcode.makeCode(${sessionScope.user.userNum});
                </script>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>